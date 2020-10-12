package com.ireal.crims.alarm.alarmprocessapi.main;

import com.ireal.crims.alarm.alarmprocessapi.container.DataCache;
import com.ireal.crims.alarm.alarmprocessapi.interfaces.AlarmProcessCallbackInterface;
import com.ireal.crims.alarm.alarmprocessapi.interfaces.AlarmProcessInterface;
import com.ireal.crims.alarm.alarmprocessapi.manage.AlarmProcessManager;
import com.ireal.crims.alarm.alarmprocessapi.manage.AlarmSubscribeManager;
import com.ireal.crims.alarm.alarmprocessapi.manage.DeviceStatusManager;
import com.ireal.crims.alarm.alarmprocessapi.manage.NotifyManager;
import com.ireal.crims.alarm.alarmprocessapi.structs.AlarmNotifyInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.AlarmProcessInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.AlarmSubscribeRequestInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.RecAlarmInfo;
import com.ireal.crims.common.enums.ErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AlarmProcessApiMain implements AlarmProcessInterface {
    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static AlarmProcessApiMain instance = new AlarmProcessApiMain();
    }

    public static AlarmProcessApiMain getInstance() {
        return SingletonHolder.instance;
    }

    private AlarmProcessCallbackInterface ctrlCB = null;

    @Override
    public boolean Init() {

        DeviceStatusManager.getInstance().Init();

        return true;
    }

    @Override
    public void UnInit() {

    }

    @Override
    public boolean OnStart() {

       NotifyManager.getInstance().start();

        return true;
    }

    @Override
    public void OnStop() {

    }



    //告警的响应解析处理
    @Override
    public int OnAlarmProcessResponse(int sequenceNo, int appType, ErrorCodeEnum result, AlarmProcessInfo alarmProcessInfo) {


        //调用 AlarmProcessManager实例的 OnAlarmProcessResponse方法在其中做处理告警响应
       return   AlarmProcessManager.getInstance().OnAlarmProcessResponse(sequenceNo, appType, result, alarmProcessInfo);



    }

    @Override
    public int OnAlarmSubscribe(int sequenceNo, int appType, ErrorCodeEnum result, AlarmSubscribeRequestInfo alarmSubscribeRequestInfo) {

        return AlarmSubscribeManager.getInstance().OnAlarmSubscribe(sequenceNo,appType,result,alarmSubscribeRequestInfo);


    }

    @Override
    public int OnAlarmNotifyResponse(int sequenceNo, ErrorCodeEnum result) {
        return 0;
    }

    @Override
    public int OnAlarmNotify(int sequenceNo, int appType, ErrorCodeEnum result, AlarmNotifyInfo alarmNotifyInfo) {


        return NotifyManager.getInstance().OnAlarmNotify(sequenceNo,appType,result,alarmNotifyInfo);
    }



    @Override
    public boolean setCtrlCB(AlarmProcessCallbackInterface ctrlCB) {
        this.ctrlCB = ctrlCB;
        return true;
    }
    public AlarmProcessCallbackInterface getCtrlCB() {
        return this.ctrlCB;
    }



    @Override
    public boolean InputAlarmInfo(List<RecAlarmInfo> alarmInfoList) {
        return false;
    }
}
