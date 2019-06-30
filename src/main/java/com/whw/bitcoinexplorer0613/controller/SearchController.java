package com.whw.bitcoinexplorer0613.controller;

import com.whw.bitcoinexplorer0613.dao.BlockMapper;
import com.whw.bitcoinexplorer0613.dao.TransactionDetailMapper;
import com.whw.bitcoinexplorer0613.dao.TransactionMapper;
import com.whw.bitcoinexplorer0613.po.Block;
import com.whw.bitcoinexplorer0613.po.Transaction;
import com.whw.bitcoinexplorer0613.po.TransactionDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class SearchController {

    @Autowired
    private BlockMapper blockMapper;
    @Autowired
    private TransactionDetailMapper transactionDetailMapper;
    @Autowired
    private TransactionMapper transactionMapper;
    @GetMapping("/search")
    public Object search(@RequestParam(required = false) String searchFactor){
         if(searchFactor.length()<20){
             List<Block> blocks = blockMapper.selectBlockByHeight(searchFactor);
             return blocks;
         }else if(searchFactor.length()<64&&searchFactor.length()>20){
             List<TransactionDetail> transactionDetails = transactionDetailMapper.selectTransactionDetailByAddress(searchFactor);
             return transactionDetails;
         }else if(searchFactor.length()==64){
             List<Transaction> transactions = transactionMapper.selectTransactions();
             return transactions;
         }
        return null;
    }
}
