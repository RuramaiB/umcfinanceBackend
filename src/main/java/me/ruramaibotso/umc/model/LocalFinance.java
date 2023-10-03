package me.ruramaibotso.umc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.ruramaibotso.umc.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LocalFinance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer financeID;
    private String Description;
    private Double amount;
    private String paymentMethod;
    private String membershipNumber;
    private LocalDateTime dateOfPayment;
    private String phoneNumber;
    @ManyToOne
    private User user;
    private String currency;
    @ManyToOne
    private Locals locals;
}
