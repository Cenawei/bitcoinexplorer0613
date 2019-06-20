package com.whw.bitcoinexplorer0613.service;

import com.whw.bitcoinexplorer0613.dto.BlockListDTO;
import com.whw.bitcoinexplorer0613.dto.TransactionListDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionListDTO> getTransaction();
}
