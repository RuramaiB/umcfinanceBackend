package me.ruramaibotso.umc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.ruramaibotso.umc.user.Organisation;
import me.ruramaibotso.umc.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SectionFinance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer financeID;
    private String Description;
    private Double amount;
    private LocalDateTime dateOfPayment;
    private String currency;
    private String paymentMethod;
    private String membershipNumber;
    private String phoneNumber;
    @ManyToOne
    private User user;
    @ManyToOne
    private Section section;
    @ManyToOne
    private Locals locals;
}
