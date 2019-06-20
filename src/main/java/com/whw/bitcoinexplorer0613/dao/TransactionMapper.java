package com.whw.bitcoinexplorer0613.dao;

import com.whw.bitcoinexplorer0613.po.Transaction;

import java.util.List;

public interface TransactionMapper {
    int deleteByPrimaryKey(String txHash);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(String txHash);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);

    List<Transaction> selectTransactions();
}