package com.ireal.crims.sgws.main;


import com.ireal.crims.sgws.enums.SgAppType;
import com.ireal.crims.sgws.enums.SgDataFormat;
import com.ireal.crims.sgws.interfaces.SgwsCallbackInterface;
import com.ireal.crims.sgws.interfaces.SgwsInterface;
import com.ireal.crims.sgws.process.MsgProcesserTest;
import com.ireal.crims.sgws.process.SgAppWebSocket;
import com.ireal.crims.sgws.structs.SgAppHeader;
import com.ireal.crims.sgws.structs.SgBody;
import com.ireal.crims.sgws.structs.SgHeader;
import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

public class SgwsClientMain implements SgwsInterface {

    public Logger logger = LoggerFactory.getLogger(getClass());


    private static SgAppWebSocket sgAppWebSocket;


    private static class SingletonHolder {
        public static SgwsClientMain instance = new SgwsClientMain();
    }

    public static SgwsClientMain getInstance() {
        return SingletonHolder.instance;
    }

    private SgwsCallbackInterface ctrlCB = null;


    private String clientAppDomainId;    // 本域Id
    private String clientAppNodeId;    // 本节点Id



    //回调接口
    public SgwsCallbackInterface getCtrlCB() {
        return this.ctrlCB;
    }

    public boolean setCtrlCB(SgwsCallbackInterface ctrlCB) {
        this.ctrlCB = ctrlCB;
        return true;
    }


    //初始化
    @Override
    public boolean Init(String sgServerEndPointsIp, int nConnPort, String wsClientDomainId, String wsClientNodeId) throws URISyntaxException {
        this.clientAppDomainId = wsClientDomainId;
        this.clientAppNodeId = wsClientNodeId;

        URI uri = new URI("ws://" + sgServerEndPointsIp + ":" + nConnPort + "/"  + wsClientNodeId + "//");
        sgAppWebSocket = new SgAppWebSocket(uri);
        logger.info("初始化ip和端口成功---url:" + uri);

        return true;

    }

    public void getConnect() {

        if (!sgAppWebSocket.isOpen()) {

            if (sgAppWebSocket.getReadyState().equals(WebSocket.READYSTATE.NOT_YET_CONNECTED)) {

                sgAppWebSocket.connect();
            }
        }
    }

    //反初始化
    public void UnInit() {
        // TODO Auto-generated method stub


    }

    // 启动
    public boolean OnStart() {
        
        //启动消息模块异步消息线程
        MsgProcesserTest.getInstance().start();
        return true;

    }

    //停止连接
    public void OnStop() {
        // TODO Auto-generated method stub


    }

    @Override
    public boolean SendData(SgAppType appType, int sequenceNo, int reqNo, SgDataFormat dataFormat, String destDomainId, String destAppNodeId, String appData) throws UnsupportedEncodingException {
        if (this.clientAppNodeId.isEmpty()) {
            return false;
        }

        if (this.clientAppDomainId.isEmpty()) {
            return false;
        }

        if (destAppNodeId.isEmpty()) {
            return false;
        }

        if (destDomainId.isEmpty()) {
            return false;
        }
        SgAppHeader sgAppHeader = new SgAppHeader();
        sgAppHeader.setAppType((byte) appType.getType());
        sgAppHeader.setSequenceNo(sequenceNo);
        sgAppHeader.setReqNo(reqNo);
        sgAppHeader.setDataFormat((byte) dataFormat.getDataType());
        sgAppHeader.getDestAddr().setDomainId(destDomainId);
        sgAppHeader.getDestAddr().setAppNodeId(destAppNodeId);

        sgAppHeader.getSrcAddr().setDomainId(this.clientAppDomainId);
        sgAppHeader.getSrcAddr().setAppNodeId(this.clientAppNodeId);

        //获取体的长度
        int length = appData.getBytes(SgAppHeader.Protocol_CharsetName_UTF8).length;
        //转换为字节数组

        byte[] appDataBytes = appData.getBytes(SgAppHeader.Protocol_CharsetName_UTF8);

        SgHeader sgHeader = new SgHeader();

        SgBody sgBody = new SgBody(sgHeader, sgAppHeader, appDataBytes);

        if (!sgBody.Init()) {
            return false;
        }

        byte[] msg = sgBody.encode();
        boolean bTest = false;

        if (bTest) {
            MsgProcesserTest.getInstance().addMsg(msg);
        } else {
            if (null != msg) {
                SgHeader tmpHeader = new SgHeader();
                SgAppHeader tmpAppHeader = new SgAppHeader();
                SgBody.decodeHeader(msg, tmpHeader, tmpAppHeader);
               // String decodeBody = SgBody.decodeBody(msg, sgHeader, sgAppHeader);

                try {

                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //@TODO 模拟测试
                //this.ctrlCB.OnReceiveData(tmpAppHeader,decodeBody);

                //发送数据
                sgAppWebSocket.send(msg);
            }
        }
        return true;
    }

    @Override
    public boolean SendData(SgAppHeader sgAppHeader, String appData) throws UnsupportedEncodingException {
        // TODO Auto-generated method stub
        //获取体的长度
        int length = appData.getBytes(SgAppHeader.Protocol_CharsetName_UTF8).length;
        //转换为字节数组

        byte[] appDataBytes = appData.getBytes(SgAppHeader.Protocol_CharsetName_UTF8);

        SgHeader sgHeader = new SgHeader();

        SgBody sgBody = new SgBody(sgHeader, sgAppHeader, appDataBytes);

        if (!sgBody.Init()) {
            return false;
        }

        byte[] msg = sgBody.encode();

        if (null != msg) {
            SgHeader tmpHeader = new SgHeader();
            SgAppHeader tmpAppHeader = new SgAppHeader();
            SgBody.decodeHeader(msg, tmpHeader, tmpAppHeader);

            //发送数据
            sgAppWebSocket.send(msg);
        }
        return true;
    }



    private int nProtocolRequenceNo = 0;

    public int getProtocolRequenceNo() {
        this.nProtocolRequenceNo++;
        return this.nProtocolRequenceNo;
    }
}