package com.whw.bitcoinexplorer0613.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whw.bitcoinexplorer0613.api.BitcoinJsonRpcApi;
import com.whw.bitcoinexplorer0613.api.BitcoinRestApi;
import com.whw.bitcoinexplorer0613.dto.BlockGetDTO;
import com.whw.bitcoinexplorer0613.dto.BlockListDTO;
import com.whw.bitcoinexplorer0613.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/block")
@CrossOrigin
public class BlockController {

    @Autowired
    private BlockService blockService;
    @GetMapping("/getBlocks")
    public List<BlockListDTO> getBlocks() throws Throwable {
        List<BlockListDTO> blocks = blockService.getBlocks();
        return blocks;
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
