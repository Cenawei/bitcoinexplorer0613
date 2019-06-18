package com.whw.bitcoinexplorer0613.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whw.bitcoinexplorer0613.api.BitcoinJsonRpcApi;
import com.whw.bitcoinexplorer0613.api.BitcoinRestApi;
import com.whw.bitcoinexplorer0613.dao.BlockMapper;
import com.whw.bitcoinexplorer0613.po.Block;
import com.whw.bitcoinexplorer0613.service.BitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/temp")
@EnableAutoConfiguration
public class TempController {

    @Autowired
    private BitcoinRestApi bitcoinRestApi;
    @Autowired
    private BitcoinJsonRpcApi bitcoinJsonRpcApi;
    @Autowired
    private BlockMapper blockMapper;
    @Autowired
    private BitcoinService bitcoinService;
    @GetMapping("/getBlockChainInfo")
    public String getBlockChainInfo(){
       return bitcoinRestApi.getBlockChainInfo().toJSONString();
    }
    @GetMapping("/getBlockNoTxDetails")
    public String getBlockNoTxDetails(){
        JSONObject blockNoTxDetails = bitcoinRestApi.getBlockNoTxDetails("0000000000002ac8b72da1b3246168d71dcbaf94742fd37581f11138596d28ae");
        return blockNoTxDetails.toJSONString();
    }
    @GetMapping("/getBlockByHash")
    public String getBlockByHash(){
        JSONObject blockNoTxDetails = bitcoinRestApi.getBlockByHash("0000000000002ac8b72da1b3246168d71dcbaf94742fd37581f11138596d28ae");
        return blockNoTxDetails.toJSONString();
    }
   /* @GetMapping("/getBlockHeader")
    public List<JSONArray> getBlockHeader(){
        List<JSONObject> blockNoTxDetails = bitcoinRestApi.getBlockHeader(3,"00000000dd99d4c4e65f4fffdc2c01caa1316d1744f65ee8b094458d4cda3710");
        return blockNoTxDetails;
    }*/
    @GetMapping("/gettx")
    public String gettx(){
        JSONObject blockNoTxDetails = bitcoinRestApi.gettx("2c319bbe81502b5e5aa2f169006c0e5ad81d1a3d0f4a65a81eef0a7fbce7617e");
        return blockNoTxDetails.toJSONString();
    }

    /*@GetMapping("/getBlockhashByHeight")
    public String getBlockhashByHeight(Integer height){
        String blockNoTxDetails = bitcoinRestApi.getBlockhashByHeight(height);
        return blockNoTxDetails;
    }*/

    @GetMapping("/getMempool")
    public String getMempool(){
        return bitcoinRestApi.getMempool().toJSONString();
    }

    @GetMapping("/getMempoolContents")
    public String getMempoolContents(){
        return bitcoinRestApi.getMempoolContents().toJSONString();
    }

    @GetMapping("/getBlockChainInfoRPC")
    public String getBlockChainInfoRPC() throws Throwable {
        JSONObject blockChainInfo = bitcoinJsonRpcApi.getBlockChainInfo();
        return blockChainInfo.toJSONString();
    }
    @GetMapping("/getBlock")
    public String getBlock() throws Throwable {
        String tempBlockHash = "000000000000072be43763a9617cd7f18c55ce04eb0d2fbd55afa1970e7dd7de";
        bitcoinService.syncBlock(tempBlockHash);
        return null;
    }
}
