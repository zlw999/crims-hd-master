package com.ireal.crims.alarm.alarmprocessapi.container;

import com.ireal.crims.alarm.alarmprocessapi.structs.device.DeviceStateInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.device.DeviceStateNotifyInfo;
import com.ireal.crims.common.constants.IMSConstant;
import com.ireal.crims.common.enums.NetConnectStateEnum;
import com.ireal.crims.common.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

    public ConcurrentMap<String, DeviceStateInfo> getDevStateMap() {
        return devStateMap;
    }


    //获取到 变化的设备状态信息，进行更新缓存
    public void updateDeviceStateInfo(List<DeviceStateInfo> devStateNotifyInfoList) {



        for (int i = 0; i < devStateNotifyInfoList.size(); i++) {
            DeviceStateInfo deviceStateInfo = devStateNotifyInfoList.get(i);
            String deviceid = deviceStateInfo.getId();
            String faultLevel = deviceStateInfo.getFaultLevel();
            Date faultTime = deviceStateInfo.getFaultTime();
            NetConnectStateEnum connState = deviceStateInfo.getConnState();
            Date stateTime = deviceStateInfo.getStateTime();

                if(this.devStateMap.containsKey(deviceid)){
                    DeviceStateInfo stateInfo = this.devStateMap.get(deviceid);
                    if(stateInfo == null){
                        stateInfo = new DeviceStateInfo();
                        stateInfo.setFaultLevel("00");
                        stateInfo.setStateTime(new Date());
                        stateInfo.setConnState(NetConnectStateEnum.Online);
                        stateInfo.setFaultTime(new Date());
                        this.devStateMap.put(deviceid,stateInfo);

                    } else {
                        stateInfo.setFaultLevel(faultLevel);
                        stateInfo.setStateTime(stateTime);
                        stateInfo.setConnState(connState);
                        stateInfo.setFaultTime(faultTime);
                    }
                }else{
                    DeviceStateInfo devState = new DeviceStateInfo();
                       devState.setId(deviceid);
                       devState.setFaultLevel(faultLevel);
                       devState.setFaultTime(faultTime);
                       devState.setConnState(connState);
                       devState.setStateTime(stateTime);
                 this.devStateMap.put(deviceid,devState);
            }
        }


    }

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
