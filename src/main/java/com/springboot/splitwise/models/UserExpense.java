package com.springboot.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class UserExpense extends BaseModel{

    private double amount;
    @ManyToOne
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private UserExpenseType userExpenseType;

}
