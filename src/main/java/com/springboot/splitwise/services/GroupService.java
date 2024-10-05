package com.springboot.splitwise.services;

import com.springboot.splitwise.Dto.TransactionDto;

import java.util.List;

public interface GroupService {
    List<TransactionDto> settleUp();

}
