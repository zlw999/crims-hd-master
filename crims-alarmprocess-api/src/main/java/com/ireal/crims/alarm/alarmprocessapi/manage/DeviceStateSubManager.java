package com.ireal.crims.alarm.alarmprocessapi.manage;

import com.ireal.crims.alarm.alarmprocessapi.container.DataCache;
import com.ireal.crims.alarm.alarmprocessapi.main.AlarmProcessApiMain;
import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.AlarmSubscriber;
import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.RecAlarmInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.device.*;
import com.ireal.crims.common.enums.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @auther shkstart
 * @create 2020-10-15-15:00
 */
public class DeviceStateSubManager extends Thread {

    public Logger logger = LoggerFactory.getLogger(getClass());


    private static class SingletonHolder {
        public static DeviceStateSubManager instance = new DeviceStateSubManager();
    }

    public static DeviceStateSubManager getInstance() {
        return DeviceStateSubManager.SingletonHolder.instance;
    }

    private DeviceStateSubManager() {
    }

    private boolean isRunning = true;

    private LinkedBlockingDeque<DeviceStateSubReqAllInfo> devSubscribeReqDeque = new LinkedBlockingDeque<>();

    //缓存告警订阅者
    private ConcurrentHashMap<DevSubscriberKey, DeviceSubscriber> mapDevSubscribe = new ConcurrentHashMap<>();

    @Override
    public void run() {
        while (isRunning) {
            try {
                if (devSubscribeReqDeque.size() <= 0) {
                    Thread.sleep(1000);
                    continue;

                }
                DeviceStateSubReqAllInfo subReqAllInfo = devSubscribeReqDeque.poll();
                this.AddDeviceSubscriber(subReqAllInfo);

               /* DeviceStateSubReqInfo deviceStateSubReqInfo = subReqAllInfo.getDeviceStateSubReqInfo();
                List<DeviceInfo> devlist = deviceStateSubReqInfo.getDevlist();

                if (subReqAllInfo == null || deviceStateSubReqInfo == null || devlist.isEmpty()) {
                    Thread.sleep(10);
                    continue;
                }
                List<DeviceStateInfo> alarmList = new ArrayList<>();
                ConcurrentMap<String, DeviceStateInfo> stateMap = DataCache.getInstance().getStateMap();
                if (stateMap != null && !stateMap.isEmpty()) {
                    for (Map.Entry<String, DeviceStateInfo> entry : stateMap.entrySet()) {
                        if (devlist != null && !devlist.isEmpty()) {
                            for (int i = 0; i < devlist.size(); i++) {
                                if (entry.getKey().equals(devlist.get(i).getId())) {
                                    alarmList.add(entry.getValue());
                                }
                            }
                        }
                    }
                    AlarmProcessApiMain.getInstance().getCtrlCB().OnDevStatusNotify(deviceStateSubReqInfo.getNodeid(), deviceStateSubReqInfo.getDomainid(),alarmList);
                }*/
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }

    //设备状态订阅
    public int OnDeviceStateSubscriber(int sequenceNo, int appType, ErrorCodeEnum result, DeviceStateSubReqInfo deviceStateSubReqInfo) {

        DeviceStateSubReqAllInfo devStateSubReqAllInfo = new DeviceStateSubReqAllInfo();
        devStateSubReqAllInfo.setAppType(appType);
        devStateSubReqAllInfo.setSequenceNo(sequenceNo);
        devStateSubReqAllInfo.setResult(result);
        devStateSubReqAllInfo.setDeviceStateSubReqInfo(deviceStateSubReqInfo);

        this.devSubscribeReqDeque.add(devStateSubReqAllInfo);
        return 0;
    }

    private int AddDeviceSubscriber(DeviceStateSubReqAllInfo subReqAllInfo) {

        DeviceStateSubReqInfo deviceStateSubReqInfo = subReqAllInfo.getDeviceStateSubReqInfo();
        List<DeviceInfo> devlist = deviceStateSubReqInfo.getDevlist();
        DevSubscriberKey devSubscriberKey = new DevSubscriberKey();
        devSubscriberKey.setDomainid(deviceStateSubReqInfo.getDomainid());
        devSubscriberKey.setNodeid(deviceStateSubReqInfo.getNodeid());
        if (this.mapDevSubscribe.containsKey(devSubscriberKey)) {
            //1.@TODO 如果缓存中存在该订阅者，直接返回
            // 2.//有订阅者，但是订阅者订阅的内容不一致的处理
            return 0;
        }
        //获取到订阅者对象
        DeviceSubscriber deviceSubscriber = this.mapDevSubscribe.get(devSubscriberKey);

        if (null != deviceSubscriber) {
            //@TODO 判断订阅的对象是否存在，存在则返回
            return 0;
        }

        //@TODO 不存在的话则新建订阅者对象，并给其赋值
        deviceSubscriber = new DeviceSubscriber();

        deviceSubscriber.setDomainid(deviceStateSubReqInfo.getDomainid());
        deviceSubscriber.setNodeid(deviceStateSubReqInfo.getNodeid());
        deviceSubscriber.setSubscribeid(deviceStateSubReqInfo.getSubscribeid());
        deviceSubscriber.setDevlist(deviceStateSubReqInfo.getDevlist());

        String sSubscribeId = deviceStateSubReqInfo.getSubscribeid();
        List<DeviceInfo> infoDevlist = deviceStateSubReqInfo.getDevlist();
        this.mapDevSubscribe.put(devSubscriberKey, deviceSubscriber);

        List<DeviceStateInfo> alarmList = new ArrayList<>();
        ConcurrentMap<String, DeviceStateInfo> stateMap = DataCache.getInstance().getStateMap();
        if (stateMap != null && !stateMap.isEmpty()) {
            for (Map.Entry<String, DeviceStateInfo> entry : stateMap.entrySet()) {
                if (devlist != null && !devlist.isEmpty()) {
                    for (int i = 0; i < devlist.size(); i++) {
                        if (entry.getKey().equals(devlist.get(i).getId())) {
                            alarmList.add(entry.getValue());
                        }
                    }
                }
            }

            AlarmProcessApiMain.getInstance().getCtrlCB().OnDevStatusNotify(deviceStateSubReqInfo.getNodeid(), deviceStateSubReqInfo.getDomainid(), alarmList);
        }
        return 0;
    }


    public void OnDeviceNotify(DeviceStateNotifyInfo devStateNotifyInfo) {

        Iterator<Map.Entry<DevSubscriberKey, DeviceSubscriber>> iterator = this.mapDevSubscribe.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<DevSubscriberKey, DeviceSubscriber> entry = iterator.next();
            DevSubscriberKey subscriberKey = entry.getKey();
            String domainid = subscriberKey.getDomainid();
            String destNodeid = subscriberKey.getNodeid();

            DeviceSubscriber deviceSubscriber = entry.getValue();

            //获取到用户订阅的告警信息
            List<DeviceStateInfo> deviceStateInfoList = deviceSubscriber.FilterNotifyDevState(devStateNotifyInfo.getDatalist());
            //如果有订阅的告警信息，则发送通知给订阅者
            if (deviceStateInfoList.size() > 0) {

                //@TODO 发送通知
                AlarmProcessApiMain.getInstance().getCtrlCB().OnDevStatusNotify(domainid, destNodeid, deviceStateInfoList);


            }


        }

    }
}

    @ApiModel("设备状态订阅者//主键key")
    class DevSubscriberKey {


        private String nodeid;      // 订阅应用节点编号 SgAppHeader.srcAddr.appNodeId
        private String domainid;    // 订阅应用域编号  SgAppHeader.srcAddr.domainId

        public DevSubscriberKey() {
        }

        public DevSubscriberKey(String nodeid, String domainid) {
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
