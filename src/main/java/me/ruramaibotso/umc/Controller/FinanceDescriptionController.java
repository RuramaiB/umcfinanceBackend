package me.ruramaibotso.umc.Controller;

import lombok.RequiredArgsConstructor;
import me.ruramaibotso.umc.exception.ResourceNotFoundException;
import me.ruramaibotso.umc.model.FinanceDescription;
import me.ruramaibotso.umc.repository.FinanceDescriptionRepository;
import me.ruramaibotso.umc.requests.FinanceDescriptionRequest;
import me.ruramaibotso.umc.services.FinanceDescriptionServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/financeDescription/")
public class FinanceDescriptionController {
    private final FinanceDescriptionServices financeDescriptionServices;
    private final FinanceDescriptionRepository financeDescriptionRepository;

    @GetMapping("/getAllFinanceDescriptions/{local}")
    public List<FinanceDescription> getAllFinanceDescriptions(@PathVariable String local){
        return financeDescriptionServices.getFinanceDescriptionByLocal(local);
    }
    @PostMapping("/addNewFinanceDescriptions")
    public FinanceDescription addNewFinanceDescriptions(@RequestBody FinanceDescriptionRequest financeDescriptionRequest){
        return financeDescriptionServices.addFinanceDescription(financeDescriptionRequest);
    }
    @DeleteMapping("/deleteFinanceDescriptionByID/{ID}")
    public String deleteFinanceDescriptionByID(@PathVariable Integer ID){
        FinanceDescription financeDescription = financeDescriptionRepository.findById(ID)
                .orElseThrow(()-> new ResourceNotFoundException("Finance Description not found"));
        financeDescriptionRepository.delete(financeDescription);
        return "Finance Description deleted successfully";
    }
}
