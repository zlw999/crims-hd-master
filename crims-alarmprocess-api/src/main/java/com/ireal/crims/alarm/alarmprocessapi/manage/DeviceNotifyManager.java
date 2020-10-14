package com.ireal.crims.alarm.alarmprocessapi.manage;

import com.ireal.crims.alarm.alarmprocessapi.structs.AlarmNotifyInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.DeviceStateNotifyInfo;
import com.ireal.crims.common.enums.ErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @auther shkstart
 * @create 2020-10-13-16:25
 */
public class DeviceNotifyManager {

    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static DeviceNotifyManager instance = new DeviceNotifyManager();
    }

    public static DeviceNotifyManager getInstance() {
        return DeviceNotifyManager.SingletonHolder.instance;
    }

    private DeviceNotifyManager() {

    }


    private LinkedBlockingDeque<DeviceStateNotifyInfo> deviceNotifyDeque = new LinkedBlockingDeque<>();



    public int onDeviceStateNotify(int sequenceNo, int appType, ErrorCodeEnum result, DeviceStateNotifyInfo deviceStateNotifyInfo) {

        deviceNotifyDeque.add(deviceStateNotifyInfo);

        return 0 ;
    }

}
