package com.ireal.crims.alarm.main;


import com.ireal.crims.alarm.alarmprocessapi.container.DataCache;
import com.ireal.crims.alarm.alarmprocessapi.main.AlarmProcessApiMain;
import com.ireal.crims.record.model.alarminfo.Rec_alarminfo;
import com.ireal.crims.sgws.main.SgwsClientMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.List;


public class MainApp extends Thread {
    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static MainApp instance = new MainApp();
    }

    public static MainApp getInstance() {
        return SingletonHolder.instance;
    }

    private MainApp() {
        this.setName("CCSP_MainApp");
    }

    public boolean Init() {
        return true;
    }

    @Override
    public void run() {

        do {
            // 1.客户端模块初始化
            try {
                if (this.InitSgws()) {
                    logger.info("初始化和链接服务端模块成功。。。。");

                }
            } catch (URISyntaxException e) {

                logger.error("初始化和链接服务端模块失败。。。");
                break;
            }

            //获取到数据库每个设备最高等级的未结束告警信息
            List<Rec_alarminfo> alarmInfoList = DataCache.getInstance().getAlarminfoMapper().getMaxOrNoEndAlarmInfoList();
            //加载到缓存中
            DataCache.getInstance().cacheRecAlarmInfo(alarmInfoList);

            if (!this.StartSgws()) {

                logger.error("客户端模块启动失败。。。。。");
                break;
            }

            logger.info("客户端模块启动成功。。。。");


            // 2.集中告警模块初始化

            if (!this.InitAlarmProcess()) {

                logger.error("集中告警服务模块初始化失败。。。。");
                break;
            }
            logger.info("集中告警服务模块初始化成功。。。。");

            if (!this.StartAlarmProcess()) {

                logger.error("集中告警服务模块启动失败。。。。");
                break;
            } else {
                logger.info("集中告警服务模块启动成功。。。。");
            }


        } while (false);
    }

    // 初始化消息中间件
    private boolean InitSgws() throws URISyntaxException {
        if (!SgwsClientMain.getInstance().Init(ParamManager.sgServerEndPointsIp, ParamManager.nConnPort, ParamManager.wsClientNodeId, ParamManager.wsClientDomainId)) {
            return false;
        }
        //调用 SgwsClientMain 实例
        SgwsClientMain instance = SgwsClientMain.getInstance();

        //获取链接
        //instance.getConnect();

        //客户端回调函数 SgwsCallbackInterface初始化
        instance.setCtrlCB(SystemBus.getInstance());
        return true;
    }


    // 启动消息中间件

    private boolean StartSgws() {

        if (!SgwsClientMain.getInstance().OnStart()) {
            return false;
        }

        SgwsClientMain.getInstance().getConnect();
        return true;
    }

    // 初始化集中告警处理模块

    private boolean InitAlarmProcess() {
        if (!AlarmProcessApiMain.getInstance().Init()) {
            return false;
        }
        //集中告警回调函数 AlarmProcessCallbackInterface 初始化
        AlarmProcessApiMain.getInstance().setCtrlCB(SystemBus.getInstance());
        return true;
    }


    // 启动集中告警处理模块
    private boolean StartAlarmProcess() {

        if (!AlarmProcessApiMain.getInstance().OnStart()) {
            return false;
        }
        return true;
    }

}
