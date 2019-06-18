package com.whw.bitcoinexplorer0613.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BlockScheduler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Scheduled(fixedRate = 60*10*1000)
    public void syncData(){
        logger.info("开始同步块信息");
    }
}
