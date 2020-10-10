
package com.ireal.crims.alarm.apps.controller;


import com.alibaba.fastjson.JSON;
import com.ireal.crims.sgws.enums.SgAppType;
import com.ireal.crims.sgws.enums.SgDataFormat;
import com.ireal.crims.sgws.main.SgwsClientMain;
import com.ireal.crims.sgws.structs.SgAppHeader;
import com.ireal.crims.sgws.test.MonitorItemRequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;


@RestController
public class SendMessageController {

    @GetMapping("/send")
    public String sendMessage(String message) throws UnsupportedEncodingException {
        SgwsClientMain instance = SgwsClientMain.getInstance();
        //获取连接
        instance.getConnect();


        //创建对象赋值
        MonitorItemRequestBody reqBody = new MonitorItemRequestBody();
        reqBody.setId("0x00A00101");
        reqBody.setName("获取SNMP点信息");
        reqBody.setType("01");
        reqBody.setResult("00");
        reqBody.setTempletefile("交换机\\SNMP监控点模板文件-交换机(H3C_S5500EI).xml");
        reqBody.setNetparams("ip=192.168.4.233|port=161|community=public|protocoltype=0x00060301");
        reqBody.setSleepTime(4);

        SgAppHeader appHeader = new SgAppHeader();
        int i = 1;
        appHeader.setAppType((byte) SgAppType.APP_REQUEST.getType());
        appHeader.setSequenceNo(i++);
        String body = JSON.toJSONString(reqBody); // "{\"id\":\"0x00A00101\",\"name\":\"获取SNMP点信息\",\"type\":\"01\",\"result\":\"00\",\"templetefile\":\"交换机\\\\SNMP监控点模板文件-交换机(H3C_S5500EI).xml\",\"netparams\":\"ip=192.168.4.233|port=161|community=public|protocoltype=0x00060301\",\"sleepTime\":4,\"a\":34,\"b\":185}";

        appHeader.setTotalPacketCount((short) 1);
        appHeader.setCurrentPacketNo((short) 1);

        appHeader.getSrcAddr().setAppNodeId("ws_client_java_192.168.25.98");
        appHeader.getSrcAddr().setDomainId("domain1");

        appHeader.getDestAddr().setAppNodeId("340201011170309000100000000");
        appHeader.getDestAddr().setDomainId("domain1");

        appHeader.setReqNo(1);
        appHeader.setReqName("cmd1");

        appHeader.setDataFormat((byte) SgDataFormat.DATA_JSON_ANSI.getDataType());
        appHeader.setResult(0);
        appHeader.setAdditionalDestCount((short)0);
        appHeader.setReserved("");
        //调用发送数据的方法
        instance.SendData(appHeader,body);

        return message;
    }
}

