package com.whw.bitcoinexplorer0613.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whw.bitcoinexplorer0613.api.BitcoinJsonRpcApi;
import com.whw.bitcoinexplorer0613.api.BitcoinRestApi;
import com.whw.bitcoinexplorer0613.dto.BlockGetDTO;
import com.whw.bitcoinexplorer0613.dto.BlockListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/block")
public class BlockController {

    @Autowired
    private BitcoinRestApi bitcoinRestApi;
    @Autowired
    private BitcoinJsonRpcApi bitcoinJsonRpcApi;


    @GetMapping("/getBlocks")
    public List<BlockListDTO> getRecentBlocks() throws Throwable {
       /* ArrayList<BlockListDTO> blockListDTOS = new ArrayList<>();
        BlockListDTO blockListDTO = new BlockListDTO();
        blockListDTO.setBlockHash("00000000000000000024b3d4793dcbba032d3fc28a0d77a37d466b956fb68aa6");
        blockListDTO.setHeight(580644);
        blockListDTO.setMiner("AntPool");
        blockListDTO.setSize(1257767);
        blockListDTO.setTime(new Date());
        blockListDTO.setTxsize((short) 2390);
        blockListDTOS.add(blockListDTO);
        BlockListDTO blockListDTO2 = new BlockListDTO();
        blockListDTO2.setBlockHash("00000000000000000024b3d4793dcbba032d3fc28a0d77a37d466b956fb68aa8");
        blockListDTO2.setHeight(580643);
        blockListDTO2.setMiner("F2Pool");
        blockListDTO2.setSize(1267767);
        blockListDTO2.setTime(new Date());
        blockListDTO2.setTxsize((short) 2389);
        blockListDTOS.add(blockListDTO2);*/
        JSONObject blockChainInfo = bitcoinRestApi.getBlockChainInfo();
        Integer blocksHeight = blockChainInfo.getInteger("blocks");
        Integer blockFromHeight = blocksHeight - 5;
        String blockHash = bitcoinJsonRpcApi.getBlockhashByHeight(blockFromHeight);
        List<JSONObject> blockHeaders = bitcoinRestApi.getBlockHeader(5, blockHash);
        ArrayList<BlockListDTO> blockListDTOS = new ArrayList<>();
        for (Object blockHeader : blockHeaders) {
            JSONObject jsonObject = (JSONObject) blockHeader;
            BlockListDTO blockListDTO = new BlockListDTO();
            blockListDTO.setBlockHash(jsonObject.getString("hash"));
            blockListDTO.setHeight(jsonObject.getInteger("height"));
            Long time = jsonObject.getLong("time");
            //todo
            blockListDTO.setMiner(null);
            //todo
            blockListDTO.setSize(null);

            blockListDTO.setTxsize(jsonObject.getShort("nTx"));
            blockListDTOS.add(blockListDTO);
        }
        return blockListDTOS;
    }


    @GetMapping("/getByBlockhash")
    public BlockGetDTO getByBlockhash(@RequestParam String blockHash) {
        BlockGetDTO blockGetDTO = new BlockGetDTO();
        blockGetDTO.setBlockHash("00000000000000000001ce5f88601a311f1c73c0073a15fe4e5956da7fbcd78b");
        blockGetDTO.setHeight(580643);
        blockGetDTO.setPrevBlock("00000000000000000005ac7036789bfec28d230dff491f3382f6daf6523f5c44");
        blockGetDTO.setNextBlock("00000000000000000024b3d4793dcbba032d3fc28a0d77a37d466b956fb68aa5");
        blockGetDTO.setMerkleRoot("07ac3d1c827b5c3ef69a7341bbdb2bf72339139b5f9e7e782d1bc82265b17798");
        blockGetDTO.setTime(new Date().getTime());
        blockGetDTO.setTransactionFees(8766.38);
        blockGetDTO.setTxsize((short) 2702);
        blockGetDTO.setSize(1322496);
        blockGetDTO.setDifficulty(7409399249090.25);
        return blockGetDTO;
    }


    @RequestMapping("/getByHeight")
    public BlockGetDTO getByHeight(@RequestParam Integer height) {
        BlockGetDTO blockGetDTO = new BlockGetDTO();
        blockGetDTO.setBlockHash("00000000000000000001ce5f88601a311f1c73c0073a15fe4e5956da7fbcd79b");
        blockGetDTO.setHeight(580643);
        blockGetDTO.setPrevBlock("00000000000000000005ac7036789bfec28d230dff491f3382f6daf6523f5c44");
        blockGetDTO.setNextBlock("00000000000000000024b3d4793dcbba032d3fc28a0d77a37d466b956fb68aa5");
        blockGetDTO.setMerkleRoot("07ac3d1c827b5c3ef69a7341bbdb2bf72339139b5f9e7e782d1bc82265b17798");
        blockGetDTO.setTime(new Date().getTime());
        blockGetDTO.setTransactionFees(8766.38);
        blockGetDTO.setTxsize((short) 2702);
        blockGetDTO.setSize(1322496);
        blockGetDTO.setDifficulty(7409399249090.25);

        return blockGetDTO;
    }

}
