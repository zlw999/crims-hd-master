package com.ireal.crims.alarm.alarmprocessapi.manage;

import com.alibaba.fastjson.JSON;
import com.ireal.crims.alarm.alarmprocessapi.container.DataCache;
import com.ireal.crims.alarm.alarmprocessapi.structs.*;
import com.ireal.crims.record.model.alarminfo.Rec_alarminfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


//设备状态管理类
public class DeviceStatusManager {


    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static DeviceStatusManager instance = new DeviceStatusManager();
    }

    public static DeviceStatusManager getInstance() {
        return DeviceStatusManager.SingletonHolder.instance;
    }

    private DeviceStatusManager() {
    }

    private ConcurrentMap<String, DeviceStateInfo> stateMap = new ConcurrentHashMap<>();

    public ConcurrentMap<String, DeviceStateInfo> getStateMap() {
        return stateMap;
    }

    public void setStateMap(ConcurrentMap<String, DeviceStateInfo> stateMap) {
        this.stateMap = stateMap;
    }

    public boolean Init() {
        return LoadStatus();
    }

    private boolean LoadStatus() {
        //@TODO 从告警归纳出设备状态
        return true;
    }

    //更新设备状态
    public void UpdateDeviceStatus(List<RecAlarmInfo> alarmList) {
        //@TODO 根据通知的告警去更新设备状态（注意：当告警为结束时，要重新从告警表中去获取该设备的告警来更新该设备的状态）
        for (int i = 0; i < alarmList.size(); i++) {
            //获取每一条告警信息
            RecAlarmInfo recAlarmInfo = alarmList.get(i);
            String deviceid = recAlarmInfo.getDeviceid();
            String alarmLevel = recAlarmInfo.getAlarmLevel();
            Date alarmDisTime = recAlarmInfo.getAlarmDisTime();
            Date alarmTime = recAlarmInfo.getAlarmTime();
            //获取到数据库每个设备最高等级的未结束告警信息
            List<Rec_alarminfo> alarmInfoList = DataCache.getInstance().getAlarminfoMapper().getMaxOrNoEndAlarmInfoList();
            //加载到缓存stateMap当中
            for (int j = 0; j < alarmInfoList.size(); j++) {
                Rec_alarminfo rec_alarminfo = alarmInfoList.get(j);
                String deviceid1 = rec_alarminfo.getDeviceid();
                //   System.out.println("deviceid1 = " + deviceid);
                DeviceStateInfo deviceStateInfo = new DeviceStateInfo();
                deviceStateInfo.setId(deviceid1);
                deviceStateInfo.setFaultTime(rec_alarminfo.getAlarmdistime());
                deviceStateInfo.setFaultLevel(rec_alarminfo.getAlarmlevel());

                   //将数据库的告警信息同步到缓存中
                this.stateMap.put(deviceid1, deviceStateInfo);
            }
            //如果缓存中存在该设备 (传进来的告警通知信息与缓存staeMap对比)
            if (this.stateMap.containsKey(deviceid)) {
                //获取该设备状态对象
                DeviceStateInfo deviceStateInfo = this.stateMap.get(deviceid);
                //如果缓存中的 deviceStateInfo为null ，接下来判断是（结束告警或未结束告警）
                if (null == deviceStateInfo) {
                    //如果告警结束，就直接，重新循环
                    if (alarmDisTime.getTime() != deviceStateInfo.getFaultTime().getTime()) {
                        

                    } else {
                        //如果是未结束告警，创建一个对象
                        DeviceStateInfo stateInfo = new DeviceStateInfo();
                        stateInfo.setId(deviceid);
                        stateInfo.setFaultLevel(alarmLevel);
                        stateInfo.setFaultTime(recAlarmInfo.getAlarmTime());
                    }
                } else {
                    //不是null的时候
                    //如果告警结束，就直接，到数据库查询最高级别的一条进行更新
                    if (alarmDisTime.getTime() != deviceStateInfo.getFaultTime().getTime()) {
                        //到数据库查询最高级别的
                        Rec_alarminfo rec_alarminfo
                                = DataCache.getInstance().getAlarminfoMapper().getOnlyOneDeviceAlarmInfoByDeviceId(deviceid);
                        //将数据库等级高的值更新到stateMap对象中
                        deviceStateInfo.setFaultLevel(rec_alarminfo.getAlarmlevel());
                        deviceStateInfo.setFaultTime(rec_alarminfo.getAlarmtime());
                    } else {
                        //如果是未结束告警，告警级别比缓存中高时 ，
                        if (Integer.valueOf(deviceStateInfo.getFaultLevel()) < Integer.valueOf(alarmLevel)) {
                            //把等级状态更新，时间更新
                            deviceStateInfo.setFaultLevel(alarmLevel);
                            deviceStateInfo.setFaultTime(alarmTime);
                        }
                    }
                }
            } else {

                //新建一个对象
                DeviceStateInfo deviceStateInfo = new DeviceStateInfo();
                deviceStateInfo.setId(deviceid);
                deviceStateInfo.setFaultLevel(alarmLevel);
                stateMap.put(deviceid, deviceStateInfo);

            }
        }
    }
}