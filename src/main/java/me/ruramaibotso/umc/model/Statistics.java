package me.ruramaibotso.umc.model;

import lombok.*;

@Data
@ToString
public class Statistics {
    private String description;
    private int paid;
    private int allocated;
    private int remaining;
    private String percentage;
    private String percentageRemainder;
}
