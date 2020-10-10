package com.ireal.crims.alarm.alarmprocessapi.interfaces;

import com.ireal.crims.alarm.alarmprocessapi.structs.AlarmProcessInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.RecAlarmInfo;

import java.util.List;

public interface AlarmProcessCallbackInterface {

    int OnAlarmProcessRequest(AlarmProcessInfo alarmProcessInfo);

    /**
     * @param targetNodeId      目标NodeId
     * @param targetDomainId    目标DomainId
     * @param alarmList         告警列表
     * @return                  成功发送的协议顺序号sequenceNo
     */
    int OnAlarmNotify(String targetNodeId, String targetDomainId, List<RecAlarmInfo> alarmList);

}
