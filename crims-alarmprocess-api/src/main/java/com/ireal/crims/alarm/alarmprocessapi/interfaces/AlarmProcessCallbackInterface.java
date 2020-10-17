package com.ireal.crims.alarm.alarmprocessapi.interfaces;

import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.AlarmProcessInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.device.DeviceStateInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.RecAlarmInfo;

import java.util.List;

public interface AlarmProcessCallbackInterface {

    //告警请求信息处理
    int OnAlarmProcessRequest(AlarmProcessInfo alarmProcessInfo);

    /**
     * @param targetNodeId      目标NodeId
     * @param targetDomainId    目标DomainId
     * @param alarmList         告警列表
     * @return                  成功发送的协议顺序号sequenceNo
     */
    int OnAlarmNotify(String targetNodeId, String targetDomainId, List<RecAlarmInfo> alarmList);

    /**
     * @param targetNodeId      目标NodeId
     * @param targetDomainId    目标DomainId
     * @param devList         设备状态列表
     * @return                  成功发送的协议顺序号sequenceNo
     */
    int OnDevStatusNotify(String targetNodeId, String targetDomainId, List<DeviceStateInfo> devList);

}
