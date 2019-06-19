package com.whw.bitcoinexplorer0613.scheduler;

import com.whw.bitcoinexplorer0613.service.BitcoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BlockScheduler {
    @Autowired
    private BitcoinService bitcoinService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Scheduled(fixedRate = 3000)
    public void syncData(){
        bitcoinService.syncBlock("000000000000038c79111230af144f5dffb8be3b1e1686c09346f213fac00ef0");
        logger.info("开始同步块信息");
    }
}
