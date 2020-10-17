package com.ireal.crims.alarm.alarmprocessapi.structs;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
 @ApiModel("告警通知信息")
public class AlarmNotifyInfo {
    private String srcNodeId;   //告警源编号(告警分析服务应用编号)
    private List<RecAlarmInfo> alarmList = new ArrayList<RecAlarmInfo>();


    public String getSrcNodeId() {
        return srcNodeId;
    }

    public void setSrcNodeId(String srcNodeId) {
        this.srcNodeId = srcNodeId;
    }

    public List<RecAlarmInfo> getAlarmList() {
        return alarmList;
    }

    public void setAlarmList(List<RecAlarmInfo> alarmList) {
        this.alarmList = alarmList;
    }
}
