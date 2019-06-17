package com.whw.bitcoinexplorer0613.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.whw.bitcoinexplorer0613.api.BitcoinJsonRpcApi;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
@Component
public class BitcoinJsonRpcApiImpl implements BitcoinJsonRpcApi {
    private JsonRpcHttpClient jsonRpcHttpClient;

    public BitcoinJsonRpcApiImpl() throws MalformedURLException {
        HashMap<String, String> headers = new HashMap<>();
        String authStrOrig = String.format("%s:%s","whw","123456");
        String authStr = Base64.getEncoder().encodeToString(authStrOrig.getBytes());
        String authStrResult = String.format("Basic %s",authStr);
        headers.put("Authorization",authStrResult);
        jsonRpcHttpClient  = new JsonRpcHttpClient(
                new URL("http://localhost:18332/"),headers);
    }

    @Override
    public JSONObject getBlockChainInfo() throws Throwable {
        JSONObject jsonObject = jsonRpcHttpClient.invoke("getblockchaininfo", new Object[]{}, JSONObject.class);
        return jsonObject;
    }
}
