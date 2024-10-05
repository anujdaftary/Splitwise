package com.springboot.splitwise.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Expense extends BaseModel{
    private String description;
    private double amount;

    @OneToMany
    private List<UserExpense> userExpenses;

    @Enumerated(EnumType.ORDINAL)
    private Currency currency;



}
