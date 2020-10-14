package com.ireal.crims.alarm.alarmprocessapi.apps.controller;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.ireal.crims.alarm.alarmprocessapi.apps.service.Rec_alarminfoService;
import com.ireal.crims.alarm.alarmprocessapi.manage.AlarmProcessManager;
import com.ireal.crims.alarm.alarmprocessapi.manage.AlarmSubscribeManager;
import com.ireal.crims.alarm.alarmprocessapi.manage.AlarmNotifyManager;
import com.ireal.crims.alarm.alarmprocessapi.structs.*;
import com.ireal.crims.alarm.alarmprocessapi.vo.AlarmProcessInfoVO;
import com.ireal.crims.common.enums.AlarmProcessCmdEnum;


import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;


@RestController
@RequestMapping("/alarmInfo")
public class AlarmInfoController {

    public static Logger logger = LoggerFactory.getLogger(AlarmInfoController.class);

    private static long OverTime = 30;  //  十秒

    @Autowired
    private Rec_alarminfoService recalarminfoService;

  /*  //告警处理的接口
    @GetMapping("/alarm")
    public AlarmProcessInfoVO alarm(String userid, String username, List<AlarmInfo> id) {
        return null;
    }*/

    //告警的确认
    @PostMapping("/alarmConfirm")
    @ApiOperation(value = "告警确认")
    public AlarmProcessInfoVO alarmConfirm(@RequestBody AlarmProcessInfoVO alarmProcessInfoVo) throws UnsupportedEncodingException {
        //  发送成功
        int alarmProcess = AlarmProcessManager.getInstance().OnAlarmProcess(alarmProcessInfoVo, AlarmProcessCmdEnum.Affirm.getCmd());

        long beginRequestTime = System.currentTimeMillis();
        //判断小于0，
        if (alarmProcess <= 0) {
            logger.error("发送不成功");
        }
        while (true) {
            //判断服务器是否有响应
            if (AlarmProcessManager.getInstance().isResponse(alarmProcess)) {
                //获取告警响应数据
                AlarmProcessInfo processInfo = AlarmProcessManager.getInstance().getAlarmProcessInfo(alarmProcess);
                AlarmProcessResponseInfo alarmProcessResponseInfo = AlarmProcessManager.getInstance().getResultByAlarmProcess(alarmProcess);
                //  AlarmProcessInfo 将转化为 AlarmProcessInfoVO
                alarmProcessInfoVo.setUserid(processInfo.getUserid());
                alarmProcessInfoVo.setUsername(processInfo.getUsername());
                alarmProcessInfoVo.setCmd(processInfo.getCmd());
                alarmProcessInfoVo.setAlarmList(processInfo.getAlarmlist());
                alarmProcessInfoVo.setResult(alarmProcessResponseInfo.getErrorCode().getCode());
                return alarmProcessInfoVo;
            }
            long endResponseTime = System.currentTimeMillis();
            //记录请求到响应的时间
            logger.info("recordTime = " + (endResponseTime - beginRequestTime) / 1000 + "s");
            if ((endResponseTime - beginRequestTime) / 1000 > OverTime) {
                logger.error("处理超时");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //告警的清除
    @PostMapping("/alarmClear")
    @ApiOperation(value = "告警消除")
    public AlarmProcessInfoVO alarmClear(@RequestBody AlarmProcessInfoVO alarmProcessInfoVo) throws UnsupportedEncodingException {

        //  发送成功
        int alarmProcess = AlarmProcessManager.getInstance().OnAlarmProcess(alarmProcessInfoVo, AlarmProcessCmdEnum.Clear.getCmd());

        //判断小于0，
        if (alarmProcess <= 0) {
            logger.error("发送不成功");
        }
        long beginRequestTime = System.currentTimeMillis();

        while (true) {
            if (AlarmProcessManager.getInstance().isResponse(alarmProcess)) {
                AlarmProcessInfo processInfo = AlarmProcessManager.getInstance().getAlarmProcessInfo(alarmProcess);
                AlarmProcessResponseInfo alarmProcessResponseInfo = AlarmProcessManager.getInstance().getResultByAlarmProcess(alarmProcess);
                //  AlarmProcessInfo  将转化为 AlarmProcessInfoVO
                alarmProcessInfoVo.setUserid(processInfo.getUserid());
                alarmProcessInfoVo.setUsername(processInfo.getUsername());
                alarmProcessInfoVo.setCmd(processInfo.getCmd());
                alarmProcessInfoVo.setAlarmList(processInfo.getAlarmlist());
                alarmProcessInfoVo.setResult(alarmProcessResponseInfo.getErrorCode().getCode());

                return alarmProcessInfoVo;

            }
            long endResponseTime = System.currentTimeMillis();
            //记录请求到响应的时间
            logger.info("recordTime = " + (endResponseTime - beginRequestTime) / 1000 + "s");

            if ((endResponseTime - beginRequestTime) / 1000 > OverTime) {

                logger.error("处理超时");
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    //分页查询告警开始时间到当前时间的所有数据并携带返回最新的时间
    @GetMapping("/getAllAlramByPage")
    @ApiOperation(value = "分页查询告警开始时间到当前时间的所有数据并携带返回最新的时间")
    public CurrAlarmQueryResponseInfo getAllAlramByPage(@RequestParam(required = false, defaultValue = "0") int currentPage,
                                                        @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                        @JsonFormat(
                                                                pattern = "yyyy-MM-dd HH:mm:ss",
                                                                timezone = "GMT+8"
                                                        )
                                                        @RequestParam(required = false) Date updatetime,   String userid,

                                                        String nodeNameStr, String deviceName, String ipAddress, String alarmType, String alarmLevel,
                                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date alarmtime,
                                                        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date alarmdistime,
                                                        String alarmaffirmtime, String isendtime, String mpNameOr, String AlarmAffirmUseridOr, String nodeNameOr, String deviceNameOr,
                                                        String ipAddressOr, String alarmTypeOr, String alarmLevelOr, String alarmtimeOr, String alarmdistimeOr, String alarmaffirmtimeOr, String updatetimeOr, String orderRule

    ) {
        return recalarminfoService.getAllByPage(currentPage, pageSize, updatetime,userid, nodeNameStr, deviceName, ipAddress, alarmType, alarmLevel, alarmtime, alarmdistime, alarmaffirmtime, isendtime,
                mpNameOr, AlarmAffirmUseridOr, nodeNameOr, deviceNameOr, ipAddressOr, alarmTypeOr, alarmLevelOr, alarmtimeOr, alarmdistimeOr, alarmaffirmtimeOr, orderRule, updatetimeOr);

    }



  /*  //获取所有未结束的告警信息,取出告警等级最高级别的信息
   @GetMapping("/getMaxOrNoEndAlarmInfoList")
   @ApiOperation("获取所有未结束的告警信息,取出告警等级最高级别的信息")
   public  List<Rec_alarminfo> getMaxOrNoEndAlarmInfoList(){
        return  recalarminfoService.getMaxOrNoEndAlarmInfoList();
   }



    //根据设备id 查询告警链表信息
    @GetMapping("/getAlarmListByDeviceId")
    @ApiOperation("根据设备id 查询告警链表信息")
    public   List<Rec_alarminfo>  getAlarmListByDeviceId(String deviceId){
        return recalarminfoService.getAlarmListByDeviceId(deviceId);
    }
*/

   //订阅
  @PostMapping("/alarmSubscriber")
    public String alarmSubscriber(  AlarmSubscriber alarmSubscriber ,@RequestBody AlarmNotifyInfo notifyInfo){

      AlarmSubscribeRequestInfo alarmSubscribeRequestInfo = new AlarmSubscribeRequestInfo();

      alarmSubscribeRequestInfo.setDomainid(alarmSubscriber.getDomainid());
      alarmSubscribeRequestInfo.setNodeid(alarmSubscriber.getNodeid());
      alarmSubscribeRequestInfo.setSubscribeid(alarmSubscriber.getSubscribeid());

       AlarmSubscribeManager.getInstance().OnAlarmSubscribe(0, 0, null, alarmSubscribeRequestInfo);


      AlarmNotifyManager.getInstance().OnAlarmNotify(0, 0, null, notifyInfo);


      return null;
  }


/*  //通知
  @PostMapping("/alarmNotify")
    public  String alarmNotify(@RequestBody AlarmNotifyInfo notifyInfo) {

      //AlarmNotifyInfo notifyInfo = new AlarmNotifyInfo();
      //notifyInfo.setSrcNodeId();
      //notifyInfo.setAlarmList(alarmInfoList);

       NotifyManager.getInstance().OnAlarmNotify(0, 0, null, notifyInfo);



      return  null;
  }*/
}
