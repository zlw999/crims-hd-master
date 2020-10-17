package com.ireal.crims.alarm.alarmprocessapi.interfaces;

import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.AlarmProcessInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.device.DeviceStateInfo;

import java.util.List;

public interface DevStatucProcessCallbackInterface {

    int OnAlarmProcessRequest(AlarmProcessInfo alarmProcessInfo);

    /**
     * @param targetNodeId      目标NodeId
     * @param targetDomainId    目标DomainId
     * @param alarmList         设备状态列表
     * @return                  成功发送的协议顺序号sequenceNo
     */
    int OnDevStatusNotify(String targetNodeId, String targetDomainId, List<DeviceStateInfo> alarmList);

}
