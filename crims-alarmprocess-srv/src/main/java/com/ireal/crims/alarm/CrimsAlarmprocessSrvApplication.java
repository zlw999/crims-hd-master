package com.ireal.crims.alarm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.ireal.crims.record.dao")
public class CrimsAlarmprocessSrvApplication {
    public static void main(String[] args) {

        SpringApplication.run(CrimsAlarmprocessSrvApplication.class,args);
    }
}
