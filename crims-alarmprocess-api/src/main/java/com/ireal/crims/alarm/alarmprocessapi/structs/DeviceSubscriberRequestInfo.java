package com.ireal.crims.alarm.alarmprocessapi.structs;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther shkstart
 * @create 2020-10-13-11:25
 */
@Data
@ApiModel("设备状态订阅请求信息")
public class DeviceSubscriberRequestInfo implements Serializable {

    private String nodeid;      // 订阅应用节点编号 SgAppHeader.srcAddr.appNodeId
    private String domainid;    // 订阅应用域编号  SgAppHeader.srcAddr.domainId
    private String subscribeid; //  订阅者(登录用户)编号
    private List<DeviceInfo> devList = new ArrayList<>();

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

    public List<DeviceInfo> getDevList() {
        return devList;
    }

    public void setDevList(List<DeviceInfo> devList) {
        this.devList = devList;
    }
}