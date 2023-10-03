package me.ruramaibotso.umc.services;

import jakarta.persistence.ElementCollection;
import lombok.RequiredArgsConstructor;
import me.ruramaibotso.umc.exception.ResourceNotFoundException;
import me.ruramaibotso.umc.model.*;
import me.ruramaibotso.umc.repository.*;
import org.hibernate.mapping.Array;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StatisticsServices {
    private final FinancialTargetsRepository financialTargetsRepository;
    private final FinanceDescriptionRepository financeDescriptionRepository;
    private final LocalsRepository localsRepository;
    private final SectionsRepository sectionsRepository;
    private final SectionFinanceRepository sectionFinanceRepository;
    private final OrganisationsRepository organisationsRepository;
    public Statistics forSectionTargetAgainstFinancialDescription(String name, String financialDescription){
        Statistics statistics = new Statistics();
        Section section = sectionsRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found."));
        FinanceDescription financeDescription = financeDescriptionRepository.findByDescription(financialDescription);
        FinancialTargets financialTargets = financialTargetsRepository.findByTargetAndFinanceDescription(section.getName(), financeDescription);
        int g = (int) financeDescription.getGrandTarget();
        int allocated = (int) financialTargets.getAmount();
        int paid = 20;
        int remainder = allocated - paid;

        var completed = (paid * 100)/allocated;
        System.out.println("This is " + completed);
        statistics.setDescription(section.getName());
        statistics.setAllocated(allocated);
        statistics.setPaid(paid);
        statistics.setRemaining(remainder);
        statistics.setPercentage(String.valueOf(completed + "%"));
        statistics.setPercentageRemainder(String.valueOf((100-completed) + "%"));
        return statistics;
    }
    public List<Statistics> getAllSectionsStatistics(String financialDescription, String local){

        Locals locals = localsRepository.findByName(local)
                .orElseThrow(() -> new ResourceNotFoundException("Local not found."));
        List<Section> sections = sectionsRepository.findAllByLocals(locals);
        FinanceDescription financeDescription = financeDescriptionRepository.findByDescription(financialDescription);
        List<Statistics> sectionsStatistics = new ArrayList<>();

        for (int i = 0; i < sections.size(); i++) {
            FinancialTargets financialTargets = financialTargetsRepository.findByTargetAndFinanceDescription(sections.get(i).getName(), financeDescription);
            Statistics statistics = new Statistics();
            int allocated = (int) financialTargets.getAmount();
            int paid = 20 + i;
            int remainder = allocated - paid;
            var completed = (paid * 100)/allocated;
            statistics.setDescription(financialTargets.getTarget());
            statistics.setAllocated(allocated);
            statistics.setPaid(paid);
            statistics.setRemaining(remainder);
            statistics.setPercentage(String.valueOf(completed + "%"));
            statistics.setPercentageRemainder(String.valueOf((100-completed) + "%"));
            System.out.println(statistics.getDescription() + " " + statistics.getPercentage() + " " + statistics.getPercentageRemainder());
            sectionsStatistics.add(statistics);
        }
        return sectionsStatistics;

    }
    public String calculateSectionAmountsByFinancialDescription(String local, String section, String financeDescription){
        Locals locals = localsRepository.findByName(local)
                .orElseThrow(() -> new ResourceNotFoundException("Local not found."));
        Section sections = sectionsRepository.findByName(section)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found."));
        FinanceDescription fD = financeDescriptionRepository.findByDescriptionAndLocals(financeDescription, locals)
                .orElseThrow(() -> new ResourceNotFoundException("Finance Description not found"));
        List<SectionFinance> sectionFinances = sectionFinanceRepository.findTopByDateOfPaymentBetweenAndSection(LocalDateTime.from(fD.getKickOff()), LocalDateTime.now(), sections)
                .orElseThrow(() -> new ResourceNotFoundException("Finance Description not found"));
        double USD = 0;
        double RTGS = 0;
        for (SectionFinance scf: sectionFinances) {
            if(Objects.equals(scf.getCurrency(), "USD")) {
                USD += scf.getAmount();
            } else if (Objects.equals(scf.getCurrency(), "ZWL") || Objects.equals(scf.getCurrency(), "RTGS")) {
                RTGS += scf.getAmount();
            }
        }
        return "USD: " + USD + ".\n RTGS: " + RTGS ;
    }
}
