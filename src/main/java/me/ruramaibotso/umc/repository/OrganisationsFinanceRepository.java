package me.ruramaibotso.umc.repository;

import me.ruramaibotso.umc.model.LocalFinance;
import me.ruramaibotso.umc.model.Locals;
import me.ruramaibotso.umc.model.OrganisationFinance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrganisationsFinanceRepository extends JpaRepository<OrganisationFinance, Integer> {
    Optional<List<OrganisationFinance>> findByMembershipNumber(String membership);

    Optional<List<OrganisationFinance>> findByOrganisationsLocals(Locals local);
    Page<OrganisationFinance> findAllByLocals(Pageable pageable, Locals Local);

    Optional<OrganisationFinance> findOrganisationFinanceByFinanceID(Integer financeID);
}
