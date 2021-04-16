package com.mbigger.sigar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.mbigger.sigar.mapper")
@SpringBootApplication
public class MbiggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbiggerApplication.class, args);
    }

}
