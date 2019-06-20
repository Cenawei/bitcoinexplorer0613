package com.whw.bitcoinexplorer0613.controller;

import com.whw.bitcoinexplorer0613.dao.TransactionMapper;
import com.whw.bitcoinexplorer0613.dto.BlockListDTO;
import com.whw.bitcoinexplorer0613.dto.TransactionListDTO;
import com.whw.bitcoinexplorer0613.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @GetMapping("/getTransactions")
    public List<TransactionListDTO> getTransactions(){
        List<TransactionListDTO> transaction = transactionService.getTransaction();
        return transaction;
    }


}
