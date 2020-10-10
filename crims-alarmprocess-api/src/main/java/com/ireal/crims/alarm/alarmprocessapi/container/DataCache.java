package com.ireal.crims.alarm.alarmprocessapi.container;

import com.ireal.crims.alarm.alarmprocessapi.structs.DeviceStateInfo;
import com.ireal.crims.record.dao.alarminfo.Rec_alarminfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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



}
