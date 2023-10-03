package me.ruramaibotso.umc.Controller;

import lombok.RequiredArgsConstructor;
import me.ruramaibotso.umc.model.Statistics;
import me.ruramaibotso.umc.services.StatisticsServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/statistics")
@RestController
public class StatisticsController {
    private final StatisticsServices statisticsServices;

    @GetMapping("/getSectionStatsBy/{name}/{financialDescription}")
    public Statistics getSectionStatsByNameAndFinancialDescription(@PathVariable String name, @PathVariable String financialDescription){
        return statisticsServices.forSectionTargetAgainstFinancialDescription(name, financialDescription);
    }
    @GetMapping("/getSectionsStatistics/{financialDescription}/{local}")
    public List<Statistics> getSectionsStatistics(@PathVariable String local, @PathVariable String financialDescription){
        return statisticsServices.getAllSectionsStatistics(financialDescription,local);
    }
    @GetMapping("/getSectionPaidAmounts/{section}/{local}/{financeDescription}")
    public String getSectionPaidAmounts(@PathVariable String section, @PathVariable String local, @PathVariable String financeDescription){
        return statisticsServices.calculateSectionAmountsByFinancialDescription( local,section, financeDescription );
    }
}
