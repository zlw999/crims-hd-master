package com.ireal.crims.alarm.alarmprocessapi.interfaces;

import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.AlarmNotifyInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.AlarmProcessInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.AlarmSubscribeRequestInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.RecAlarmInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.device.DeviceStateNotifyInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.device.DeviceStateSubReqInfo;
import com.ireal.crims.common.enums.ErrorCodeEnum;

import java.util.List;

public interface AlarmProcessInterface {
    //初始化

    boolean Init();

    //反初始化
    void UnInit();

    //启动
    boolean OnStart();

    //停止
    void OnStop();

    //设置回调函数
    boolean setCtrlCB(AlarmProcessCallbackInterface ctrlCB);


    //响应告警处理的数据(告警采集分析服务->集中告警处理服务)
    int OnAlarmProcessResponse(int sequenceNo, int appType, ErrorCodeEnum result, AlarmProcessInfo alarmProcessInfo);

    //告警订阅请求信息(显示端->集中告警处理服务)
    int OnAlarmSubscribe(int sequenceNo, int appType, ErrorCodeEnum result, AlarmSubscribeRequestInfo alarmSubscribeRequestInfo);

    //告警通知(告警采集分析服务->集中告警处理服务)
    int OnAlarmNotify(int sequenceNo, int appType, ErrorCodeEnum result, AlarmNotifyInfo alarmNotifyInfo);

   //设备状态订阅
    int OnDeviceStateSubscriber(int sequenceNo, int appType, ErrorCodeEnum result, DeviceStateSubReqInfo deviceStateSubReqInfo);


    //设备状态通知
    int OnDeviceStateNotify(int sequenceNo, int appType, ErrorCodeEnum result, DeviceStateNotifyInfo deviceStateNotifyInfo);

    /**告警通知响应(显示端->集中告警处理服务):后续对失败和超时响应的通知再做处理
     * @param sequenceNo
     * @param result
     * @return
     */
    int OnAlarmNotifyResponse(int sequenceNo, ErrorCodeEnum result);



    boolean InputAlarmInfo(List<RecAlarmInfo> alarmInfoList);

}
