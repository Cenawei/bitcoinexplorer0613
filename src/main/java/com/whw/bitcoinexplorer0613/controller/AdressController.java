package com.whw.bitcoinexplorer0613.controller;

import com.whw.bitcoinexplorer0613.dao.TransactionDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AdressController {

    @Autowired
    private TransactionDetailMapper transactionDetailMapper;
    @GetMapping("/getBlance")
    public Double getBlance(@RequestParam String address){
        Double balance = transactionDetailMapper.getBalance(address);
        return balance;
    }
}
