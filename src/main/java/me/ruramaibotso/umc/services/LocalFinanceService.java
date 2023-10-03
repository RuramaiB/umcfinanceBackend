package me.ruramaibotso.umc.services;

import lombok.RequiredArgsConstructor;
import me.ruramaibotso.umc.exception.ResourceNotFoundException;
import me.ruramaibotso.umc.model.LocalFinance;
import me.ruramaibotso.umc.model.Locals;
import me.ruramaibotso.umc.repository.LocalFinanceRepository;
import me.ruramaibotso.umc.repository.LocalsRepository;
import me.ruramaibotso.umc.requests.LocalFinanceRequest;
import me.ruramaibotso.umc.user.User;
import me.ruramaibotso.umc.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LocalFinanceService {
    private final LocalFinanceRepository localFinanceRepository;
    private final LocalsRepository localsRepository;
    private final UserRepository userRepository;

    public Page<LocalFinance> getAllCircuitFinancesPaged(int offset){
        return localFinanceRepository.findAll(PageRequest.of(offset, 10));
    }
    public List<LocalFinance> getAllLocalsFinances(){
        return localFinanceRepository.findAll();
    }

    public Page<LocalFinance> getAllLocalFinanceByLocal(int offset, String local){
        Locals locals = localsRepository.findByName(local)
                .orElseThrow(() -> new ResourceNotFoundException("Local was not found"));
       return localFinanceRepository.findAllByLocals(PageRequest.of(offset, 10), locals);
    }
    public LocalFinance addFinanceByLocal(LocalFinanceRequest localFinanceRequest){

        LocalFinance localFinance = new LocalFinance();
        Locals locals = localsRepository.findByName(localFinanceRequest.getLocals())
                .orElseThrow(() -> new ResourceNotFoundException("Local point not found"));
            User user = userRepository.findUserByMembershipNumber(localFinanceRequest.getMembershipNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            localFinance.setFinanceID(localFinance.getFinanceID());
            localFinance.setCurrency(localFinanceRequest.getCurrency());
            localFinance.setAmount(localFinanceRequest.getAmount());
            localFinance.setDescription(localFinanceRequest.getDescription());
            localFinance.setPhoneNumber(localFinanceRequest.getPhoneNumber());
            localFinance.setDateOfPayment(LocalDateTime.now());
            localFinance.setPaymentMethod(localFinanceRequest.getPaymentMethod());
            localFinance.setMembershipNumber(user.getMembershipNumber());
            localFinance.setUser(user);
            localFinance.setLocals(locals);
            localFinanceRepository.save(localFinance);
       return localFinance;
    }

    public List<LocalFinance> getLocFinanceByMembership(String membershipNumber){
        return localFinanceRepository.findLocalFinanceByMembershipNumber(membershipNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Local was not found"));
    }


}
