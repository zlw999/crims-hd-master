package com.ireal.crims.alarm.alarmprocessapi.apps.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ireal.crims.alarm.alarmprocessapi.apps.service.Rec_alarminfoService;
import com.ireal.crims.alarm.alarmprocessapi.structs.CurrAlarmQueryResponseInfo;
import com.ireal.crims.record.dao.alarminfo.Rec_alarminfoMapper;
import com.ireal.crims.record.model.alarminfo.Rec_alarminfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @auther shkstart
 * @create 2020-09-05-9:55
 */

@Service
public class RecalarmInfoServiceImpl implements Rec_alarminfoService {

    @Resource
    private Rec_alarminfoMapper recalarminfoMapper;

    //分页查询告警开始时间到当前时间的所有数据并返回最新的时间
    @Override
    public CurrAlarmQueryResponseInfo getAllByPage(int currentPage, int pageSize, Date updatetime, String userid, String nodeName, String deviceName,
                                                   String ipAddress, String alarmType, String alarmLevel, Date alarmtime, Date alarmdistime,
                                                   String alarmaffirmtime, String isendtime, String mpNameOr, String AlarmAffirmUseridOr, String nodeNameOr,
                                                   String deviceNameOr, String ipAddressOr, String alarmTypeOr, String alarmLevelOr, String alarmtimeOr, String alarmdistimeOr, String alarmaffirmtimeOr, String orderRule, String updatetimeOr) {

        PageHelper.startPage(currentPage, pageSize);

        List<Rec_alarminfo> list = recalarminfoMapper.getAlarmList(updatetime, nodeName, deviceName, ipAddress, alarmType, alarmLevel, alarmtime, alarmdistime, alarmaffirmtime, isendtime,
                mpNameOr, AlarmAffirmUseridOr, nodeNameOr, deviceNameOr, ipAddressOr, alarmTypeOr, alarmLevelOr,
                alarmtimeOr, alarmdistimeOr, alarmaffirmtimeOr, orderRule, updatetimeOr);
        // System.out.println("list = " + list);
        PageInfo<Rec_alarminfo> pageInfo = new PageInfo<>(list);
        Date alarmByUpdateTime = recalarminfoMapper.getAlarmByUpdateTime(updatetime, userid);
        CurrAlarmQueryResponseInfo currAlarmQueryResponseInfo = new CurrAlarmQueryResponseInfo();
        if (list.size() > 0) {

            //System.out.println("updateTime = " + updateTime);

            currAlarmQueryResponseInfo.setPageInfo(pageInfo);
            currAlarmQueryResponseInfo.setUpdatetime(alarmByUpdateTime);

            return currAlarmQueryResponseInfo;

        } else {

            currAlarmQueryResponseInfo.setPageInfo(pageInfo);
            currAlarmQueryResponseInfo.setUpdatetime(updatetime);
            return currAlarmQueryResponseInfo;

        }

    }





/*    @Override
    public List<Rec_alarminfo> getMaxOrNoEndAlarmInfoList() {
        List<Rec_alarminfo> maxOrNoEndAlarmInfoList = recalarminfoMapper.getMaxOrNoEndAlarmInfoList();
      *//*  //将告警信息，以设备分组
        HashMap<String, List<Rec_alarminfo>> map = new HashMap<>();
        //遍历告警链表
        for (int i = 0; i < maxOrNoEndAlarmInfoList.size(); i++) {
            //取出每条告警数据
            Rec_alarminfo rec_alarminfo = maxOrNoEndAlarmInfoList.get(i);
            ArrayList<Rec_alarminfo> list = null;
            //获取到设备id
            String deviceid = rec_alarminfo.getDeviceid();
            //获取集合中的设备id，此时为空；
            List<Rec_alarminfo> rec_alarminfos = map.get(deviceid);
            //
            if (null != rec_alarminfos) {
                rec_alarminfos.add(rec_alarminfo);
                map.put(deviceid, rec_alarminfos);
            } else {
                list = new ArrayList<>();
                list.add(rec_alarminfo);
                map.put(deviceid, list);
            }

        }
        System.out.println(map);
        for (Map.Entry<String, List<Rec_alarminfo>> entry : map.entrySet()) {

        }*//*

        return maxOrNoEndAlarmInfoList;

    }

    @Override
    public List<Rec_alarminfo> getAlarmListByDeviceId(String deviceId) {

        return recalarminfoMapper.getAlarmListByDeviceId(deviceId);
    }*/
}
