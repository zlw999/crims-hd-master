package com.ireal.crims.alarm.alarmprocessapi.structs;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther shkstart
 * @create 2020-10-13-15:40
 */

@Data
@ApiModel("设备状态订阅对象")
public class DeviceStateSubscriber implements Serializable {

    private String nodeid;      // 订阅应用节点编号 SgAppHeader.srcAddr.appNodeId
    private String domainid;    // 订阅应用域编号  SgAppHeader.srcAddr.domainId
    private String subscribeid; //  订阅者(登录用户)编号
    private List<DeviceInfo> devList = new ArrayList<>();

    public List<DeviceInfo> getDevList() {
        return devList;
    }

    public void setDevList(List<DeviceInfo> devList) {
        this.devList = devList;
    }

    public String getNodeid() {
        return nodeid;
    }

    public void setNodeid(String nodeid) {
        this.nodeid = nodeid;
    }

    public String getDomainid() {
        return domainid;
    }

    public void setDomainid(String domainid) {
        this.domainid = domainid;
    }

    public String getSubscribeid() {
        return subscribeid;
    }

    public void setSubscribeid(String subscribeid) {
        this.subscribeid = subscribeid;
    }
}


