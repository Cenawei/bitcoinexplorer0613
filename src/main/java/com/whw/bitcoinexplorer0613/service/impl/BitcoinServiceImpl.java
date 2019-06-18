package com.whw.bitcoinexplorer0613.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.whw.bitcoinexplorer0613.api.BitcoinRestApi;
import com.whw.bitcoinexplorer0613.dao.BlockMapper;
import com.whw.bitcoinexplorer0613.po.Block;
import com.whw.bitcoinexplorer0613.service.BitcoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BitcoinServiceImpl implements BitcoinService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BitcoinRestApi bitcoinRestApi;
    @Autowired
    private BlockMapper blockMapper;
    @Override
    @Async
    public void syncBlock(String blockHash) {
        String tempBlockHash = blockHash;
        logger.info("hash为："+blockHash);
        while (tempBlockHash != null && !tempBlockHash.isEmpty()) {
            JSONObject blockJson = bitcoinRestApi.getBlock(tempBlockHash);
            Block block = new Block();
            block.setBlockHash(blockJson.getString("hash"));
            block.setHeight(blockJson.getInteger("height"));
            Long time = blockJson.getLong("time");
            block.setTime(new Date(time * 1000));
            block.setTxsize(blockJson.getShort("nTx"));
            block.setSize(blockJson.getInteger("size"));
            block.setWeight(blockJson.getFloat("weight"));
            block.setDifficulty(blockJson.getDouble("difficulty"));
            block.setPrevBlock(blockJson.getString("previousblockhash"));
            block.setNextBlock(blockJson.getString("nextblockhash"));
            block.setMerkleRoot(blockJson.getString("merkleroot"));
            blockMapper.insert(block);
            tempBlockHash = block.getNextBlock();


        }
        logger.info("同步完成");

    }
}
