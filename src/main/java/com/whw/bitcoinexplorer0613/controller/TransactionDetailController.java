package com.whw.bitcoinexplorer0613.controller;

import com.whw.bitcoinexplorer0613.po.TransactionDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactionDetail")
public class TransactionDetailController {

    @GetMapping("/getTransactionDetailByTxHash")
    public List<TransactionDetail> getTransactionDetail(@RequestParam String txHash){
        ArrayList<TransactionDetail> transactionDetailControllers = new ArrayList<>();
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setTxDetailId((long) 1);
        transactionDetail.setTxHash("d28d9ca8b170fbdece854e74dc1dd9360ef97431609520ae696a125b703daf63");
        transactionDetail.setAdress("3KnwSrjGmR8U9HGnRqnDGgsVA3NRtCKhdZ");
        transactionDetail.setAmount(1.0967);
        transactionDetail.setInputTotal(0.11628326);
        transactionDetail.setOutputTotal(	0.11534299);
        transactionDetail.setSize(2098);
        transactionDetail.setTransactionFees(	0.00094027 );
        transactionDetail.setType((byte) 1);
        transactionDetail.setWeight((float) 4534);
        transactionDetailControllers.add(transactionDetail);

        TransactionDetail transactionDetail2 = new TransactionDetail();
        transactionDetail2.setTxDetailId((long) 2);
        transactionDetail2.setTxHash("d28d9ca8b170fbdece854e74dc1dd9360ef97431609520ae696a125b703daf63");
        transactionDetail2.setAdress("f73c1ce2416af67c0ac64201a8dcad1420adebb4d06672c7bfbb1b7d40bffa7a");
        transactionDetail2.setAmount(0.00494655);
        transactionDetail2.setInputTotal(0.0049759);
        transactionDetail2.setOutputTotal(	0.00494655 );
        transactionDetail2.setSize(283);
        transactionDetail2.setTransactionFees(	0.00002935  );
        transactionDetail2.setType((byte) 1);
        transactionDetail2.setWeight((float) 1132);
        transactionDetailControllers.add(transactionDetail2);
        return transactionDetailControllers;

    }
}
