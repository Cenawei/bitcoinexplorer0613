package com.whw.bitcoinexplorer0613.service.impl;

import com.whw.bitcoinexplorer0613.dao.TransactionMapper;
import com.whw.bitcoinexplorer0613.dto.BlockListDTO;
import com.whw.bitcoinexplorer0613.dto.TransactionListDTO;
import com.whw.bitcoinexplorer0613.po.Transaction;
import com.whw.bitcoinexplorer0613.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionMapper transactionMapper;
    @Override
    public List<TransactionListDTO> getTransaction() {
        List<Transaction> transactions = transactionMapper.selectTransactions();
        ArrayList<TransactionListDTO> blockListDTOS = new ArrayList<>();
        for (Transaction transaction:transactions) {
            TransactionListDTO transactionListDTO = new TransactionListDTO();
            transactionListDTO.setTxHash(transaction.getTxHash());
            transactionListDTO.setBlockHash(transaction.getBlockHash());
            transactionListDTO.setTime(transaction.getTime().getTime());
            transactionListDTO.setAmount(transaction.getAmount());
            blockListDTOS.add(transactionListDTO);
        }
        return blockListDTOS;
    }
}
