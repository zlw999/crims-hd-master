package com.ireal.crims.alarm.alarmprocessapi.interfaces;

import com.ireal.crims.alarm.alarmprocessapi.structs.AlarmNotifyInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.AlarmProcessInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.AlarmSubscribeRequestInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.RecAlarmInfo;
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

    boolean InputAlarmInfo(List<RecAlarmInfo> alarmInfoList);
}
