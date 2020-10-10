package com.ireal.crims.record.dao.alarminfo;

import com.ireal.crims.record.model.alarminfo.Rec_alarminfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @auther shkstart
 * @create 2020-09-05-9:37
 */
@Mapper
public interface Rec_alarminfoMapper {


    List<Rec_alarminfo> getAlarmList(@Param("updatetime") Date updatetime, String nodeName, String deviceName, String ipAddress, String alarmType,
                                     String alarmLevel, Date alarmtime, Date alarmdistime, String alarmaffirmtime,
                                     String isendtime, String mpNameOr, String AlarmAffirmUseridOr, String nodeNameOr, String deviceNameOr,
                                     String ipAddressOr, String alarmTypeOr, String alarmLevelOr, String alarmtimeOr, String alarmdistimeOr,
                                     String alarmaffirmtimeOr, String orderRule, String updatetimeOr);


    Date getAlarmByUpdateTime(@Param("updatetime") Date updateTime,String userid);





   //获取每个设备所属的所有未结束告警信息,取出每个设备告警等级最高级别的信息-->
    List<Rec_alarminfo>   getMaxOrNoEndAlarmInfoList();




    //获取某个设备所属的所有未结束告警信息,取出某个设备告警等级最高级别的信息
   // List<Rec_alarminfo>   getOnlyDeviceAlarmInfoByDeviceId(String deviceId);


    //获取某个设备所属的所有未结束告警信息,取出某个设备告警等级最高级别的一条信息
    Rec_alarminfo  getOnlyOneDeviceAlarmInfoByDeviceId(String deviceId);



    //根据设备id 查询告警链表信息
    List<Rec_alarminfo>  getAlarmListByDeviceId(String deviceId);

    


}
