package com.whw.bitcoinexplorer0613.dao;

import com.whw.bitcoinexplorer0613.dto.BlockListDTO;
import com.whw.bitcoinexplorer0613.po.Block;

import java.util.List;

public interface BlockMapper {
    int deleteByPrimaryKey(String blockHash);

    int insert(Block record);

    int insertSelective(Block record);

    Block selectByPrimaryKey(String blockHash);

    int updateByPrimaryKeySelective(Block record);

    int updateByPrimaryKey(Block record);
    //add
    List<Block> selectBlocks();


}