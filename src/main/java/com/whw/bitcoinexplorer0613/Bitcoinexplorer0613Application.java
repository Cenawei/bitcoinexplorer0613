package com.whw.bitcoinexplorer0613;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.whw.bitcoinexplorer0613.dao")
@EnableFeignClients
public class Bitcoinexplorer0613Application {

    public static void main(String[] args) {
        SpringApplication.run(Bitcoinexplorer0613Application.class, args);
    }

}
