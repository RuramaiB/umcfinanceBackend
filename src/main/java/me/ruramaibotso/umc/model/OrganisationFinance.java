package me.ruramaibotso.umc.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.ruramaibotso.umc.user.Organisation;
import me.ruramaibotso.umc.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationFinance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer financeID;
    private String Description;
    private Double amount;
    private LocalDateTime dateOfPayment;
    private String paymentMethod;
    private String membershipNumber;
    private String currency;
    private String phoneNumber;
    @ManyToOne
    private User user;
    @ManyToOne
    private Locals locals;
    @ManyToOne
    private Organisations organisations;
}
