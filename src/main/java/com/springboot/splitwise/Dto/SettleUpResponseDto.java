package com.springboot.splitwise.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SettleUpResponseDto {
    private List<TransactionDto> transactionList;

}
