package com.ireal.crims.alarm.alarmprocessapi.container;

import com.ireal.crims.alarm.alarmprocessapi.structs.DeviceStateInfo;
import com.ireal.crims.common.enums.NetConnectStateEnum;
import com.ireal.crims.common.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DeviceStateCache {
    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static DeviceStateCache instance = new DeviceStateCache();
    }

    public static DeviceStateCache getInstance() {
        return DeviceStateCache.SingletonHolder.instance;
    }

    private DeviceStateCache() {

    }

    private ConcurrentMap<String, DeviceStateInfo> devStateMap = new ConcurrentHashMap<String, DeviceStateInfo>();

    public void updateConnState(String deviceid, NetConnectStateEnum connState) {
        if (this.devStateMap.containsKey(deviceid)) {
            DeviceStateInfo devState = this.devStateMap.get(deviceid);
            if (null == devState) {
                devState = new DeviceStateInfo();
                devState.setConnState(connState);
                devState.setStateTime(new Date());
                this.devStateMap.replace(deviceid, devState);
            } else {
                devState.setConnState(connState);
                devState.setStateTime(new Date());
            }
        } else {
            DeviceStateInfo devState = new DeviceStateInfo();
            devState.setConnState(connState);
            devState.setStateTime(new Date());
            this.devStateMap.put(deviceid, devState);
        }
    }

    public void updateFaultState(String deviceid, String faultLevel) {
        if (this.devStateMap.containsKey(deviceid)) {
            DeviceStateInfo devState = this.devStateMap.get(deviceid);
            if (null == devState) {
                devState = new DeviceStateInfo();
                devState.setFaultLevel(faultLevel);
                devState.setStateTime(new Date());
                this.devStateMap.replace(deviceid, devState);
            } else {
                devState.setFaultLevel(faultLevel);
                devState.setStateTime(new Date());
            }
        } else {
            DeviceStateInfo devState = new DeviceStateInfo();
            devState.setFaultLevel(faultLevel);
            devState.setStateTime(new Date());
            this.devStateMap.put(deviceid, devState);
        }
    }

    public void clearFaultState(String deviceid) {
        if (this.devStateMap.containsKey(deviceid)){
            DeviceStateInfo devState = this.devStateMap.get(deviceid);
            if (null == devState) {
                return;
            }
            devState.setStateTime(DateUtil.getDefaultTime());
        }
    }
}
