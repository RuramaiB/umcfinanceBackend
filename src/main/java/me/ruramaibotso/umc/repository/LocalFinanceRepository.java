package me.ruramaibotso.umc.repository;

import me.ruramaibotso.umc.model.LocalFinance;
import me.ruramaibotso.umc.model.Locals;
import me.ruramaibotso.umc.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface LocalFinanceRepository extends JpaRepository<LocalFinance, Integer> {
    Page<LocalFinance> findAllByLocals(Pageable pageable, Locals Local);
    Optional<List<LocalFinance>> findLocalFinanceByLocals(Locals local);
    Optional<List<LocalFinance>> findLocalFinanceByMembershipNumber(String membershipNumber);
    Optional<LocalFinance> findLocalFinanceByFinanceID(Integer financeID);
}
