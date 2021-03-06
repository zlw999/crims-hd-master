package com.ireal.crims.alarm.alarmprocessapi.structs.device;

import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.RecAlarmInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther shkstart
 * @create 2020-10-16-16:58
 */
@Data
@ApiModel("设备状态订阅对象")
public class DeviceSubscriber {

    private String nodeid;                                // 订阅应用节点编号 SgAppHeader.srcAddr.appNodeId
    private String domainid;                              // 订阅应用域编号  SgAppHeader.srcAddr.domainId
    private String subscribeid;                           //  订阅者(登录用户)编号

    private List<DeviceInfo> devlist = new ArrayList<>(); //设备列表

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

    public List<DeviceInfo> getDevlist() {
        return devlist;
    }

    public void setDevlist(List<DeviceInfo> devlist) {
        this.devlist = devlist;
    }




    public List<DeviceStateInfo> FilterNotifyDevState(List<DeviceStateInfo> datalist) {



      return null;
    }
}
