package com.whw.bitcoinexplorer0613.dao;

import com.whw.bitcoinexplorer0613.po.Block;

public interface BlockMapper {
    int deleteByPrimaryKey(String blockHash);

    int insert(Block record);

    int insertSelective(Block record);

    Block selectByPrimaryKey(String blockHash);

    int updateByPrimaryKeySelective(Block record);

    int updateByPrimaryKey(Block record);
}