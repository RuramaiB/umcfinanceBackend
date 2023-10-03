package me.ruramaibotso.umc.repository;

import me.ruramaibotso.umc.model.LocalFinance;
import me.ruramaibotso.umc.model.Locals;
import me.ruramaibotso.umc.model.Section;
import me.ruramaibotso.umc.model.SectionFinance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SectionFinanceRepository extends JpaRepository<SectionFinance, Integer> {
    List<SectionFinance> findSectionRecordByMembershipNumber(String membershipNumber);
    List<SectionFinance> findBySectionName(String sectionName);
    Page<SectionFinance> findAllByLocals(Pageable pageable, Locals Local);
    Optional<List<SectionFinance>> findSectionRecordByLocals(Locals local);
    Optional<List<SectionFinance>> findTopByDateOfPaymentBetweenAndSection(LocalDateTime startingDate, LocalDateTime endingDate, Section section);

    Optional<SectionFinance> findSectionFinanceByFinanceID(Integer financeID);
}
