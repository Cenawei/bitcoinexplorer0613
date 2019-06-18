package com.whw.bitcoinexplorer0613.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.whw.bitcoinexplorer0613.dao.BlockMapper;
import com.whw.bitcoinexplorer0613.dto.BlockListDTO;
import com.whw.bitcoinexplorer0613.po.Block;
import com.whw.bitcoinexplorer0613.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BlockServiceImpl implements BlockService {
    @Autowired
    private BlockMapper blockMapper;
    @Override
    public List<BlockListDTO> getBlocks() {
        List<Block> blocks = blockMapper.selectBlocks();
        ArrayList<BlockListDTO> blockListDTOS = new ArrayList<>();
        for (Block block : blocks) {
            BlockListDTO blockListDTO = new BlockListDTO();
            blockListDTO.setBlockHash(block.getBlockHash());
            blockListDTO.setHeight(block.getHeight());
            blockListDTO.setTime(block.getTime().getTime());
            blockListDTO.setSize(block.getSize());
            blockListDTO.setTxsize(block.getTxsize());
            blockListDTO.setMiner(block.getMiner());
            blockListDTOS.add(blockListDTO);
        }
        return blockListDTOS;

    }
}
