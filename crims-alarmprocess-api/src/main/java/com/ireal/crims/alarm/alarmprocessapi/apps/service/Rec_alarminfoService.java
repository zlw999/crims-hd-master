package com.ireal.crims.alarm.alarmprocessapi.apps.service;

import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.CurrAlarmQueryResponseInfo;

import java.util.Date;

/**
 * @auther zhanglongwei
 * @create 2020-09-05-9:55
 */
public interface Rec_alarminfoService {

    //分页查询告警开始时间到当前时间的所有数据并返回最新的时间
    CurrAlarmQueryResponseInfo getAllByPage(int currentPage, int pageSize, Date updatetime,String userid,
                                            String nodeNameStr, String deviceName, String ipAddress, String alarmType, String alarmLevel,
                                            Date alarmtime, Date alarmdistime, String alarmaffirmtime, String isendtime, String mpNameOr, String AlarmAffirmUseridOr, String nodeNameOr, String deviceNameOr,
                                            String ipAddressOr, String alarmTypeOr, String alarmLevelOr, String alarmtimeOr, String alarmdistimeOr, String alarmaffirmtimeOr, String orderRule, String updatetimeOr);


  /*  //获取所有未结束的告警信息,取出告警等级最高级别的信息
    List<Rec_alarminfo>   getMaxOrNoEndAlarmInfoList();


    //根据设备id 查询告警链表信息
    List<Rec_alarminfo>  getAlarmListByDeviceId(String deviceId);
*/


}
