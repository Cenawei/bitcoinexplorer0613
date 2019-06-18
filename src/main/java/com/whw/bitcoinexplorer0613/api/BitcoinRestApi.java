package com.whw.bitcoinexplorer0613.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "BitcoinRestApi",url="http://localhost:18332")
public interface BitcoinRestApi {
    @GetMapping("/rest/chaininfo.json")
    JSONObject getBlockChainInfo();

    @GetMapping("/rest/block/notxdetails/{blockHash}.json")
    JSONObject getBlockNoTxDetails(@PathVariable(value = "blockHash") String blockHash);

    @GetMapping("/rest/block/{blockHash}.json")
    JSONObject getBlockByHash(@PathVariable(value = "blockHash") String blockHash);

    @GetMapping("/rest/headers/{count}/{blockHash}.json")
    List<JSONObject> getBlockHeader(@PathVariable("count") Integer count, @PathVariable("blockHash") String blockHash);

    @GetMapping("/rest/tx/{blockHash}.json")
    JSONObject gettx(@PathVariable(value = "blockHash") String blockHash);

    @GetMapping("/rest/blockhashbyheight/{height}.json")
    String getBlockhashByHeight(@PathVariable(value = "height") Integer height);

    @GetMapping("/rest/mempool/info.json")
    JSONObject getMempool();

    @GetMapping("/rest/mempool/contents.json")
    JSONObject getMempoolContents();
}
