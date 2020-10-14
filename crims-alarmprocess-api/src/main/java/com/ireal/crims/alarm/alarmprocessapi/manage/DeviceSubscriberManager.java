package com.ireal.crims.alarm.alarmprocessapi.manage;

import com.ireal.crims.alarm.alarmprocessapi.structs.DeviceStateSubscriber;
import com.ireal.crims.alarm.alarmprocessapi.structs.DeviceSubscriberRequestInfo;
import com.ireal.crims.common.enums.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @auther shkstart
 * @create 2020-10-13-11:33
 */
public class DeviceSubscriberManager {

    public Logger logger = LoggerFactory.getLogger(getClass());

    
    private static class SingletonHolder {
        public static DeviceSubscriberManager instance = new DeviceSubscriberManager();
    }

    public static DeviceSubscriberManager getInstance() {
        return DeviceSubscriberManager.SingletonHolder.instance;
    }

    private DeviceSubscriberManager() {
    }

  //  private ConcurrentMap<DeviceSubscriberKey, DeviceStateSubscriber> devSubMap = new ConcurrentHashMap<>();

    public int onDeviceStateSubscriber(int sequenceNo, int appType, ErrorCodeEnum result, DeviceSubscriberRequestInfo deviceSubscriberRequestInfo) {




        return  0;
    }
}





@ApiModel("订阅者//主键key")
class DeviceSubscriberKey {


    private String nodeid;      // 订阅应用节点编号 SgAppHeader.srcAddr.appNodeId
    private String domainid;    // 订阅应用域编号  SgAppHeader.srcAddr.domainId

    public DeviceSubscriberKey() {
    }

    public DeviceSubscriberKey(String nodeid, String domainid) {
        this.nodeid = nodeid;
        this.domainid = domainid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubscriberKey)) return false;
        SubscriberKey that = (SubscriberKey) o;
        return getNodeid().equals(that.getNodeid()) &&
                getDomainid().equals(that.getDomainid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNodeid(), getDomainid());
    }
}

