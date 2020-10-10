package com.ireal.crims.nivm.syncagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class )
@EnableTransactionManagement
public class CrimsNivmSyncAgentSrvApplication {
    public static void main(String[] args) {

        SpringApplication.run(CrimsNivmSyncAgentSrvApplication.class,args);
    }
}
