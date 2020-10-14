package com.ireal.crims.sgws.process;

import com.ireal.crims.sgws.interfaces.SgwsCallbackInterface;
import com.ireal.crims.sgws.main.SgwsClientMain;
import com.ireal.crims.sgws.structs.SgAppHeader;
import com.ireal.crims.sgws.structs.SgBody;
import com.ireal.crims.sgws.structs.SgHeader;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.ByteBuffer;


//  。。。。。。。。。自己加的继承类，实现webSocketClient
public class SgAppWebSocket extends WebSocketClient {

    public Logger logger = LoggerFactory.getLogger(getClass());


    public SgAppWebSocket(URI serverUri) {
        super(serverUri);
    }

    //触发连接事件
    @Override
    public void onOpen(ServerHandshake handshakedata) {

            logger.info("连接成功。。。");

    }
    //服务器发送消息到客户端时触发的事件

    public void onMessage(String message) {

        logger.info("检测到服务器请求:"+ message);

    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        //获取收到数据数组
        byte[] byteMsg = bytes.array();


        if( null == byteMsg ) {
            return;
        }

        SgHeader tmpHeader= new SgHeader();
        SgAppHeader tmpAppHeader = new SgAppHeader();
        //如果头的格式类型不相符合，则返回
        if( !SgBody.decodeHeader(byteMsg, tmpHeader, tmpAppHeader) )
        {
            return;
        }
          //解析
        String sMsgBody = SgBody.decodeBody(byteMsg, tmpHeader, tmpAppHeader);

        SgwsCallbackInterface cb = SgwsClientMain.getInstance().getCtrlCB();
        if(null != cb )
        {
            try {

                //将头和体放入回调方法中
                cb.OnReceiveData(tmpAppHeader, sMsgBody);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

            logger.info("!!!");

        //super.onMessage(bytes);
    }

    //触发关闭事件
    @Override

    public void onClose(int code, String reason, boolean remote) {

            logger.info("客户端已关闭");

    }
   //触发异常事件
    @Override
    public void onError(Exception ex) {


        logger.info("客户端发生错误,即将关闭",ex.getMessage());
    }

}
