package com.ireal.crims.alarm.alarmprocessapi.manage;

import com.ireal.crims.alarm.alarmprocessapi.container.DeviceStateCache;
import com.ireal.crims.alarm.alarmprocessapi.structs.device.DeviceStateNotifyInfo;
import com.ireal.crims.common.enums.ErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingDeque;

public class DeviceNotifyManager extends  Thread {

    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static DeviceNotifyManager instance = new DeviceNotifyManager();
    }

    public static DeviceNotifyManager getInstance() {
        return DeviceNotifyManager.SingletonHolder.instance;
    }

    private DeviceNotifyManager() {
    }

    private boolean isRunning = true;

    private LinkedBlockingDeque<DeviceStateNotifyInfo>  devNotifyReqDeque = new LinkedBlockingDeque<>();

    @Override
    public void run() {
        while (isRunning) {
            try {
                if (devNotifyReqDeque.size() <= 0) {
                    Thread.sleep(1000);
                    continue;
                }

                DeviceStateNotifyInfo devStateNotifyInfo = devNotifyReqDeque.poll();
                 //更新设备状态信息
                DeviceStateCache.getInstance().updateDeviceStateInfo(devStateNotifyInfo.getDatalist());

                //获取到设备状态的订阅者，发送通知

                DeviceStateSubManager.getInstance().OnDeviceNotify(devStateNotifyInfo);

            }catch (Exception e){

                 e.printStackTrace();
            }
        }

    }


    public int OnDeviceStateNotify(int sequenceNo, int appType, ErrorCodeEnum result, DeviceStateNotifyInfo deviceStateNotifyInfo) {

        devNotifyReqDeque.add(deviceStateNotifyInfo);
        return 0 ;
    }
}
