package com.whw.bitcoinexplorer0613.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whw.bitcoinexplorer0613.api.BitcoinRestApi;
import com.whw.bitcoinexplorer0613.dao.BlockMapper;
import com.whw.bitcoinexplorer0613.dao.TransactionDetailMapper;
import com.whw.bitcoinexplorer0613.dao.TransactionMapper;
import com.whw.bitcoinexplorer0613.po.Block;
import com.whw.bitcoinexplorer0613.po.Transaction;
import com.whw.bitcoinexplorer0613.po.TransactionDetail;
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
    @Autowired
    private TransactionDetailMapper transactionDetailMapper;
    @Override
    @Async
    @Transactional
    public void syncBlock(String blockHash) {
        String tempBlockHash = "";
        JSONObject blockChainInfo = bitcoinRestApi.getBlockChainInfo();
        Integer bestHeight = blockChainInfo.getInteger("blocks");
        Block block1 = blockMapper.selectByPrimaryKey(blockHash);
        if(block1!=null){
            Integer bestHeightDb = blockMapper.selectByHeight();
            if(!bestHeight.equals(bestHeightDb)){
                JSONObject blockhashByHeight = bitcoinRestApi.getBlockhashByHeight(bestHeightDb+1);
                tempBlockHash=blockhashByHeight.getString("blockhash");
            }
        }else{
            tempBlockHash=blockHash;
        }
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
        String txHash = transaction.getTxHash();
        syncTxDetail(txJson,txHash);
        //todo set tx amount
    }

    @Override
    @Transactional
    public void syncTxDetail(JSONObject txJson,String txHash) {
        JSONArray vins = txJson.getJSONArray("vin");
        syncTxDetailVin(vins,txHash);
        JSONArray vouts = txJson.getJSONArray("vout");
        syncTxDetailVout(vouts,txHash);

    }

    @Override
    @Transactional
    public void syncTxDetailVout(JSONArray vouts,String txHash) {
        TransactionDetail transactionDetail = new TransactionDetail();
        for (Object vout:vouts) {
            JSONObject jsonObject = new JSONObject((LinkedHashMap) vout);
            transactionDetail.setAdress(jsonObject.getJSONObject("scriptPubKey").getString("addresses"));
            transactionDetail.setTxHash(txHash);
            //transactionDetail.setType(jsonObject.getJSONObject("scriptPubKey").getByte("type"));
        }
        transactionDetailMapper.insert(transactionDetail);
    }

    @Override
    public void syncTxDetailVin(JSONArray vins,String txHash) {
        for (Object vin:vins) {
            JSONObject jsonObject = new JSONObject((LinkedHashMap) vin);

        }
    }
}
