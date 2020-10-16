package com.ireal.crims.alarm.alarmprocessapi.structs.device;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther shkstart
 * @create 2020-10-13-10:46
 */
@Data
@ApiModel("设备状态通知信息")
public class DeviceStateNotifyInfo implements Serializable {


    private String srcNodeId;   //设备状态源编号(设备状态分析服务应用编号)
    
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
