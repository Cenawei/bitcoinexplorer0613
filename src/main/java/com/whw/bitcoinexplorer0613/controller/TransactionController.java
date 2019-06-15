package com.whw.bitcoinexplorer0613.controller;

import com.whw.bitcoinexplorer0613.dto.BlockListDTO;
import com.whw.bitcoinexplorer0613.dto.TransactionListDTO;
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

    @GetMapping("/getTransactions")
    public List<TransactionListDTO> getTransactions(){
        ArrayList<TransactionListDTO> blockListDTOS = new ArrayList<>();
        TransactionListDTO transactionListDTO = new TransactionListDTO();
        transactionListDTO.setTxHash("d28d9ca8b170fbdece854e74dc1dd9360ef97431609520ae696a125b703daf63");
        transactionListDTO.setBlockHash("00000000000000000024b3d4793dcbba032d3fc28a0d77a37d466b956fb68aa6");
        transactionListDTO.setTime(new Date());
        transactionListDTO.setAmount(0.79024877 );
        transactionListDTO.setAmount(10783.99);
        blockListDTOS.add(transactionListDTO);
        TransactionListDTO transactionListDTO2 = new TransactionListDTO();
        transactionListDTO2.setTxHash("f16822a3a35c1cd49a3a27c952cff12b1a4a4497d962a4c5fbc1bf3aa60945c2");
        transactionListDTO2.setBlockHash("00000000000000000024b3d4793dcbba032d3fc28a0d77a37d466b956fb68aa6");
        transactionListDTO2.setTime(new Date());
        transactionListDTO2.setAmount(0.0037717 );
        transactionListDTO2.setAmount(106.07);
        blockListDTOS.add(transactionListDTO2);
        return blockListDTOS;
    }


}
