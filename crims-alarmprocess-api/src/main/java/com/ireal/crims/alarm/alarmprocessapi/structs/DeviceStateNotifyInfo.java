package com.ireal.crims.alarm.alarmprocessapi.structs;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther shkstart
 * @create 2020-10-13-10:46
 */

@ApiModel("设备状态通知信息")
public class DeviceStateNotifyInfo implements Serializable {

    private String srcNodeId;   //告警源编号(告警分析服务应用编号)
    
    private List<DeviceStateInfo> datalist = new ArrayList<>();

    public String getSrcNodeId() {
        return srcNodeId;
    }

    public void setSrcNodeId(String srcNodeId) {
        this.srcNodeId = srcNodeId;
    }

    public List<DeviceStateInfo> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<DeviceStateInfo> datalist) {
        this.datalist = datalist;
    }
}
