package com.ireal.crims.alarm.alarmprocessapi.manage;

import com.ireal.crims.alarm.alarmprocessapi.structs.AlarmNotifyInfo;
import com.ireal.crims.common.enums.ErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @auther shkstart
 * @create 2020-09-19-10:01
 * <p>
 * 告警通知管理类线程
 */
public class NotifyManager extends Thread {
    public Logger logger = LoggerFactory.getLogger(getClass());



    private static class SingletonHolder {
        public static NotifyManager instance = new NotifyManager();
    }

    public static NotifyManager getInstance() {
        return NotifyManager.SingletonHolder.instance;
    }

    private NotifyManager() {
    }


    private boolean isRunning = true;


    private LinkedBlockingDeque<AlarmNotifyInfo> alarmNotifyDeque = new LinkedBlockingDeque<>();


    @Override
    public void run() {

        while (isRunning) {
            try {
                if (alarmNotifyDeque.size() <= 0) {
                    Thread.sleep(1000);
                    continue;
                }
                //获取到队列中的所有数据
                AlarmNotifyInfo alarmNotifyInfo = alarmNotifyDeque.poll();
                //获取更新设备的状态
                DeviceStatusManager.getInstance().UpdateDeviceStatus(alarmNotifyInfo.getAlarmList());
                //获取到订阅者要发送的通知
                AlarmSubscribeManager.getInstance().AlarmNotify(alarmNotifyInfo);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    //  //告警通知(告警采集分析服务->集中告警处理服务)
    public int OnAlarmNotify(int sequenceNo, int appType, ErrorCodeEnum result, AlarmNotifyInfo alarmNotifyInfo) {

         alarmNotifyDeque.add(alarmNotifyInfo);
         
        return 0;

    }


    public int OnAlarmNotifyResponse(int sequenceNo, ErrorCodeEnum result) {


        return 0;

    }

}
