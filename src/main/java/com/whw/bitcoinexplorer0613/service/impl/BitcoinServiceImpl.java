package com.whw.bitcoinexplorer0613.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whw.bitcoinexplorer0613.api.BitcoinRestApi;
import com.whw.bitcoinexplorer0613.dao.BlockMapper;
import com.whw.bitcoinexplorer0613.dao.TransactionMapper;
import com.whw.bitcoinexplorer0613.po.Block;
import com.whw.bitcoinexplorer0613.po.Transaction;
import com.whw.bitcoinexplorer0613.service.BitcoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class BitcoinServiceImpl implements BitcoinService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BitcoinRestApi bitcoinRestApi;
    @Autowired
    private BlockMapper blockMapper;
    @Autowired
    private TransactionMapper transactionMapper;
    @Override
    @Async
    @Transactional
    public void syncBlock(String blockHash) {
        String tempBlockHash = blockHash;
        logger.info("hash为："+blockHash);
        while (tempBlockHash != null && !tempBlockHash.isEmpty()) {
            JSONObject blockJson = bitcoinRestApi.getBlock(tempBlockHash);
            Block block = new Block();
            block.setBlockHash(blockJson.getString("hash"));
            block.setHeight(blockJson.getInteger("height"));
            Long times = blockJson.getLong("time");
            Date time = new Date(times*1000);
            block.setTime(time);
            block.setTxsize(blockJson.getShort("nTx"));
            block.setSize(blockJson.getInteger("size"));
            block.setWeight(blockJson.getFloat("weight"));
            block.setDifficulty(blockJson.getDouble("difficulty"));
            block.setPrevBlock(blockJson.getString("previousblockhash"));
            block.setNextBlock(blockJson.getString("nextblockhash"));
            block.setMerkleRoot(blockJson.getString("merkleroot"));
            Integer confirmations = blockJson.getInteger("blockJson");
            blockMapper.insert(block);


            JSONArray txArray = blockJson.getJSONArray("tx");
            for (Object tx:txArray) {
                JSONObject jsonObject = new JSONObject((LinkedHashMap) tx);
                syncTx(jsonObject, tempBlockHash, time, confirmations);
            }
            tempBlockHash = block.getNextBlock();

        }
        logger.info("同步完成");

    }

    @Override
    @Transactional
    public void syncTx(JSONObject txJson, String blockHash, Date time, Integer confirmations) {
        Transaction transaction = new Transaction();
        transaction.setBlockHash(blockHash);
        transaction.setConfirmations(confirmations);
        transaction.setTime(time);
        transaction.setTxHash(txJson.getString("txid"));
        transaction.setSize(txJson.getInteger("size"));
        transaction.setWeight(txJson.getFloat("weight"));
        transactionMapper.insert(transaction);
        //todo set tx amount
    }
}
