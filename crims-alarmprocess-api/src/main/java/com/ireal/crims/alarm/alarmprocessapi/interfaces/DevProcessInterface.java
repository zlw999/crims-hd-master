package com.ireal.crims.alarm.alarmprocessapi.interfaces;

import com.ireal.crims.alarm.alarmprocessapi.structs.device.DeviceStateNotifyInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.device.DeviceStateSubReqInfo;
import com.ireal.crims.common.enums.ErrorCodeEnum;

/**
 * @auther shkstart
 * @create 2020-10-16-9:50
 */
public interface DevProcessInterface {

    //设备状态订阅
    int OnDeviceStateSubscriber(int sequenceNo, int appType, ErrorCodeEnum result, DeviceStateSubReqInfo deviceStateSubReqInfo);


    //设备状态通知
    int OnDeviceStateNotify(int sequenceNo, int appType, ErrorCodeEnum result, DeviceStateNotifyInfo deviceStateNotifyInfo);

}
