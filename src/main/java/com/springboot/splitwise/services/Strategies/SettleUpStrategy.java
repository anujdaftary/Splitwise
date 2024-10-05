package com.springboot.splitwise.services.Strategies;

import com.springboot.splitwise.Dto.TransactionDto;
import com.springboot.splitwise.models.Expense;

import java.util.List;

public interface SettleUpStrategy {
    List<TransactionDto> settleUp(List<Expense> expenses);

}
