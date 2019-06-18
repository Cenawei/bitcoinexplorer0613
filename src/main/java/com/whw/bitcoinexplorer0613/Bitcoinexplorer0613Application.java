package com.whw.bitcoinexplorer0613;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.whw.bitcoinexplorer0613.dao")
@EnableFeignClients
@EnableAsync
@EnableScheduling
public class Bitcoinexplorer0613Application {

    public static void main(String[] args) {
        SpringApplication.run(Bitcoinexplorer0613Application.class, args);
    }

}
