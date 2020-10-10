package com.ireal.crims.alarm.main;


import com.ireal.crims.alarm.alarmprocessapi.container.DataCache;
import com.ireal.crims.record.dao.alarminfo.Rec_alarminfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component // 被spring容器管理
@Order(1) // 如果多个自定义ApplicationRunner，用来标明执行顺序
public class MainAppRunner implements ApplicationRunner {

      // TODO 注入配置文件中的参数
    @Value("${Websocket.server.ip}")
    private  String server_Ip;

    @Value("${Websocket.server.port}")
    private  int server_Port;

    @Value("${Websocket.wsClientDomainId}")
    private  String wc_DomainId;

    @Value("${Websocket.wsClientAppNodeId}")
    private  String wc_AppNodeId;

    @Value("${Websocket.wsDestDomainId}")
    private  String ws_DestDomainId;

    @Value("${Websocket.wsDestAppNodeId}")
    private  String ws_DestAppNodeId;

    @Resource
    private Rec_alarminfoMapper rec_alarminfoMapper;



    @Override
    public void run(ApplicationArguments args) throws Exception {

         ParamManager.sgServerEndPointsIp = this.server_Ip;

        ParamManager.nConnPort = this.server_Port;
        ParamManager.wsClientDomainId = this.wc_DomainId;
        ParamManager.wsClientNodeId = this.wc_AppNodeId;


        ParamManager.wsDestDomainId = this.ws_DestDomainId;
        ParamManager.wsDestAppNodeId = this.ws_DestAppNodeId;




        DataCache.getInstance().setAlarminfoMapper(rec_alarminfoMapper);


        if (MainApp.getInstance().Init()) {
            //启动线程
            MainApp.getInstance().start();
        }
    }
}
