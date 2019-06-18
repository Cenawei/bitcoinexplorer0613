package com.whw.bitcoinexplorer0613.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public interface BitcoinService {
    void syncBlock(String blockHash);
    void syncTx(JSONObject txJson, String blockHash, Date time,Integer confirmations);

}
