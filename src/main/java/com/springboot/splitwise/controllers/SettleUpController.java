package com.springboot.splitwise.controllers;

import com.springboot.splitwise.Dto.TransactionDto;
import com.springboot.splitwise.services.GroupService;
import com.springboot.splitwise.services.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SettleUpController {
    @Autowired
    private InitService initService;
    @Autowired
    private GroupService groupService;

    @GetMapping("/init")
    public ResponseEntity initialise(){
        initService.initialise();
        return ResponseEntity.ok("DONE");
    }

    @GetMapping("/settleUp")
    public ResponseEntity settleUp(){
        List<TransactionDto> transactionDTOS = groupService.settleUp();
        return ResponseEntity.ok(transactionDTOS);
    }
}
