package com.whw.bitcoinexplorer0613.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public interface BitcoinService {
    void syncBlock(String blockHash);
    void syncTx(JSONObject txJson, String blockHash, Date time,Integer confirmations);
    void syncTxDetail(JSONObject txJson,String txHash);
    void syncTxDetailVout(JSONArray vouts,String txHash);
    void syncTxDetailVin(JSONArray vins,String txHash);

}
