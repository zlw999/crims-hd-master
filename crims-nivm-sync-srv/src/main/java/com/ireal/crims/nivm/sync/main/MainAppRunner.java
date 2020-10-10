package com.ireal.crims.nivm.sync.main;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component // 被spring容器管理
@Order(1) // 如果多个自定义ApplicationRunner，用来标明执行顺序

public class MainAppRunner implements ApplicationRunner {



    public Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {



        if (MainApp.getInstance().Init()) {

            MainApp.getInstance().start();
        }
    }
}
