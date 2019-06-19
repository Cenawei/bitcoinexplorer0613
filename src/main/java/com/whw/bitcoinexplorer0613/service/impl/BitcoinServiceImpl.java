package com.whw.bitcoinexplorer0613.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whw.bitcoinexplorer0613.api.BitcoinRestApi;
import com.whw.bitcoinexplorer0613.dao.BlockMapper;
import com.whw.bitcoinexplorer0613.dao.TransactionDetailMapper;
import com.whw.bitcoinexplorer0613.dao.TransactionMapper;
import com.whw.bitcoinexplorer0613.enumeration.DetailType;
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
    public void syncBlockchainHash(String blockhash) throws Throwable {
        logger.info("开始同步");
        String tempBlockhash = blockhash;
        while (tempBlockhash != null && !tempBlockhash.isEmpty()){
            String nextBlock = syncBlock(tempBlockhash);
            tempBlockhash = nextBlock;
        }
        logger.info("同步结束");
    }

    @Override
    @Transactional
    public String syncBlock(String blockHash) {

            JSONObject blockJson = bitcoinRestApi.getBlock(blockHash);
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
                syncTx(jsonObject, blockHash, time, confirmations);
            }
            return block.getNextBlock();




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
        syncTxDetailVin(vins, txHash);
        JSONArray vouts = txJson.getJSONArray("vout");
        syncTxDetailVout(vouts,txHash);

    }

    @Override
    @Transactional
    public void syncTxDetailVout(JSONArray vouts,String txHash) {
        TransactionDetail transactionDetail = new TransactionDetail();
        for (Object vout:vouts) {
            JSONObject jsonObject = new JSONObject((LinkedHashMap) vout);
            JSONObject scriptPubKey = jsonObject.getJSONObject("scriptPubKey");
            JSONArray addresses = scriptPubKey.getJSONArray("addresses");
            if(addresses!=null){
                String address = addresses.getString(0);
                transactionDetail.setAdress(address);
            }
            transactionDetail.setType((byte) DetailType.Receive.ordinal());
            transactionDetail.setTxHash(txHash);
            transactionDetail.setAmount(jsonObject.getDouble("value"));
            //transactionDetail.setType(jsonObject.getJSONObject("scriptPubKey").getByte("type"));
        }
        transactionDetailMapper.insert(transactionDetail);
    }

    @Override
    @Transactional
    public void syncTxDetailVin(JSONArray vins,String txHash) {
        for (Object vin:vins) {
            JSONObject jsonObject = new JSONObject((LinkedHashMap) vin);
            String txid = jsonObject.getString("txid");
            Integer vont = jsonObject.getInteger("vout");
            if(txid!=null){
                JSONObject getVinTx = bitcoinRestApi.gettx(txid);
                JSONArray vouts = getVinTx.getJSONArray("vout");
                JSONObject n = vouts.getJSONObject(vont);
                TransactionDetail transactionDetail = new TransactionDetail();
                transactionDetail.setAmount(-n.getDouble("value"));
                transactionDetail.setTxHash(txHash);
                transactionDetail.setType((byte) DetailType.Send.ordinal());
                JSONObject scriptPubKey = n.getJSONObject("scriptPubKey");
                JSONArray addresses = scriptPubKey.getJSONArray("addresses");
                if(addresses!=null){
                    String address = addresses.getString(0);
                    transactionDetail.setAdress(address);
                }
                transactionDetailMapper.insert(transactionDetail);
            }
        }

    }
}
