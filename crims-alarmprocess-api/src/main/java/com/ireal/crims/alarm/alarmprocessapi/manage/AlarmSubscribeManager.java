package com.ireal.crims.alarm.alarmprocessapi.manage;

import com.ireal.crims.alarm.alarmprocessapi.main.AlarmProcessApiMain;
import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.*;
import com.ireal.crims.common.constants.IMSConstant;
import com.ireal.crims.common.enums.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;


//告警订阅管理类
public class AlarmSubscribeManager extends Thread  {
    public Logger logger = LoggerFactory.getLogger(getClass());

    private AlarmSubscribeManager() {
    }

    private static class SingletonHolder {
        public static AlarmSubscribeManager instance = new AlarmSubscribeManager();
    }

    public static AlarmSubscribeManager getInstance() {
        return AlarmSubscribeManager.SingletonHolder.instance;
    }


    //缓存告警订阅者
    private ConcurrentHashMap<SubscriberKey, AlarmSubscriber> mapSubscribe = new ConcurrentHashMap<>();

    private boolean isRunning = true;

    private LinkedBlockingDeque<AlarmSubscribeRequestAllInfo>  subscribeReqDeque = new LinkedBlockingDeque<>();

    @Override
    public void run() {

        while (isRunning) {
            try {
                if (subscribeReqDeque.size() <= 0) {
                    Thread.sleep(1000);
                    continue;
                }
                //获取到队列中的所有数据
                AlarmSubscribeRequestAllInfo subscribeReqInfo = subscribeReqDeque.poll();

                this.AddAlarmSubscribe(subscribeReqInfo);

            }
            catch (Exception ex)
            {

            }
        }
    }

    ///告警订阅请求信息(显示端->集中告警处理服务)
    public int OnAlarmSubscribe(int sequenceNo, int appType, ErrorCodeEnum result, AlarmSubscribeRequestInfo alarmSubscribeRequestInfo) {

        AlarmSubscribeRequestAllInfo requestInfo = new AlarmSubscribeRequestAllInfo();
        requestInfo.setSequenceNo(sequenceNo);
        requestInfo.setAppType(appType);
        requestInfo.setResult(result);
        requestInfo.setAlarmSubscribeRequestInfo(alarmSubscribeRequestInfo);

        this.subscribeReqDeque.add(requestInfo);

        return 0;
    }

    private int AddAlarmSubscribe(AlarmSubscribeRequestAllInfo requestInfo)
    {
        AlarmSubscribeRequestInfo alarmSubscribeRequestInfo = requestInfo.getAlarmSubscribeRequestInfo();
        //从订阅请求信息中，获取到订阅者
        SubscriberKey subscriber = new SubscriberKey();
        subscriber.setDomainid(alarmSubscribeRequestInfo.getDomainid());
        subscriber.setNodeid(alarmSubscribeRequestInfo.getNodeid());

        if (this.mapSubscribe.containsKey(subscriber)) {
            //1.@TODO 如果缓存中存在该订阅者，直接返回
            // 2.//有订阅者，但是订阅者订阅的内容不一致的处理
            return 0;
        }

        //获取到订阅者
        AlarmSubscriber alarmSubscriber = this.mapSubscribe.get(subscriber);

        if (null != alarmSubscriber) {
            //@TODO 判断订阅的对象是否存在，存在则返回
            return 0;
        }

        //@TODO 不存在的话则新建订阅者对象，并给其赋值
        alarmSubscriber = new AlarmSubscriber();

        alarmSubscriber.setDomainid(alarmSubscribeRequestInfo.getDomainid());
        alarmSubscriber.setNodeid(alarmSubscribeRequestInfo.getNodeid());
        alarmSubscriber.setSubscribeid(alarmSubscribeRequestInfo.getSubscribeid());
        
        String sSubscribeId = alarmSubscribeRequestInfo.getSubscribeid();
        
        this.mapSubscribe.put(subscriber,alarmSubscriber);

        boolean bcfg = false;

        if (bcfg) {
            //@TODO 如果数据库配置有告警订阅信息，这里将从数据库提取

        } else {

            //@TODO 目前订阅所有
            alarmSubscriber.addSubscribe(IMSConstant.DEF_ALL_NODE, IMSConstant.DEF_ALL_ALARMTYPE);
        }

         //TODO:从设备状态中过滤并组织告警信息，然后发送给订阅者
        List<RecAlarmInfo> alarmList = DeviceStatusManager.getInstance().getSubscriberAlarmInfo(alarmSubscriber);

        AlarmProcessApiMain.getInstance().getCtrlCB().OnAlarmNotify(alarmSubscriber.getNodeid(),alarmSubscriber.getDomainid() , alarmList);

        return 0;
    }


    //迭代找出是某个订阅者    获取到订阅用户的告警并通知订阅者
    public void AlarmNotify(AlarmNotifyInfo alarmNotifyInfo) {

        // 循环迭代缓存中的数据，
        Iterator<Map.Entry<SubscriberKey, AlarmSubscriber>> entries = this.mapSubscribe.entrySet().iterator();

        while (entries.hasNext()) {

            // 获取到key，value
            Map.Entry<SubscriberKey, AlarmSubscriber> entry = entries.next();
            SubscriberKey subscriber = entry.getKey();
            String destDomainid = subscriber.getDomainid();
            String destNodeid = subscriber.getNodeid();

            AlarmSubscriber alarmSubscriber = entry.getValue();

            if( alarmSubscriber.getFirstSend() ) {
                continue;
            }

            //获取到用户订阅的告警信息
            List<RecAlarmInfo> alarmList = alarmSubscriber.FilterNotifyAlarm(alarmNotifyInfo.getAlarmList());
            //如果有订阅的告警信息，则发送通知给订阅者
            if (alarmList.size() > 0) {

                //@TODO 发送通知
                AlarmProcessApiMain.getInstance().getCtrlCB().OnAlarmNotify(destDomainid, destNodeid, alarmList);

                alarmSubscriber.setFirstSend(false);
            }
        }
    }
}


@ApiModel("订阅者//主键key")
class SubscriberKey {


    private String nodeid;      // 订阅应用节点编号 SgAppHeader.srcAddr.appNodeId
    private String domainid;    // 订阅应用域编号  SgAppHeader.srcAddr.domainId

    public SubscriberKey() {
    }

    public SubscriberKey(String nodeid, String domainid) {
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
