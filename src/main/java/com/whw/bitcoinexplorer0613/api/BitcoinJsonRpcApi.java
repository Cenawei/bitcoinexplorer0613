package com.whw.bitcoinexplorer0613.api;

import com.alibaba.fastjson.JSONObject;

public interface BitcoinJsonRpcApi {
    JSONObject getBlockChainInfo() throws Throwable;
}
