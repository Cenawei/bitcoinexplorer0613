package com.whw.bitcoinexplorer0613.service;

import com.whw.bitcoinexplorer0613.dto.BlockListDTO;
import com.whw.bitcoinexplorer0613.po.Block;

import java.util.List;

public interface BlockService {
    List<BlockListDTO> getBlocks();
}
