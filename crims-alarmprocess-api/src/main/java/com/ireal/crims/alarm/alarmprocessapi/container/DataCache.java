package com.ireal.crims.alarm.alarmprocessapi.container;

import com.ireal.crims.alarm.alarmprocessapi.structs.device.DeviceStateInfo;
import com.ireal.crims.record.dao.alarminfo.Rec_alarminfoMapper;
import com.ireal.crims.record.model.alarminfo.Rec_alarminfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @auther shkstart
 * @create 2020-09-17-14:59
 * 告警订阅缓存，存放dao层数据
 */


public class DataCache {
    public Logger logger = LoggerFactory.getLogger(getClass());

    private DataCache() {
    }

    private ConcurrentMap<String, DeviceStateInfo> stateMap = new ConcurrentHashMap<>();

    private static class SingletonHolder {
        public static DataCache instance = new DataCache();
    }

    public static DataCache getInstance() {
        return DataCache.SingletonHolder.instance;
    }


    private Rec_alarminfoMapper  alarminfoMapper;

    public Rec_alarminfoMapper getAlarminfoMapper() {
        return alarminfoMapper;
    }

    public void setAlarminfoMapper(Rec_alarminfoMapper alarminfoMapper) {
        this.alarminfoMapper = alarminfoMapper;
    }


    public boolean cacheRecAlarmInfo(List<Rec_alarminfo> alarmInfoList){
        if (alarmInfoList != null && !alarmInfoList.isEmpty()){
            //加载到缓存stateMap当中
            for (int j = 0; j < alarmInfoList.size(); j++) {
                Rec_alarminfo rec_alarminfo = alarmInfoList.get(j);
                String deviceid1 = rec_alarminfo.getDeviceid();
                DeviceStateInfo deviceStateInfo = new DeviceStateInfo();
                deviceStateInfo.setId(deviceid1);
                deviceStateInfo.setFaultTime(rec_alarminfo.getAlarmdistime());
                deviceStateInfo.setFaultLevel(rec_alarminfo.getAlarmlevel());
                //将数据库的告警信息同步到缓存中
                this.stateMap.put(deviceid1, deviceStateInfo);
            }
        }

        return true;
    }

    public ConcurrentMap<String, DeviceStateInfo> getStateMap() {
        return stateMap;
    }
}
