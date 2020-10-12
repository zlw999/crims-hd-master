package com.ireal.crims.alarm.alarmprocessapi.structs;

import com.ireal.crims.common.constants.IMSConstant;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Data
@ApiModel("告警订阅")
public class AlarmSubscriber {

    private String nodeid;      // 订阅应用节点编号 SgAppHeader.srcAddr.appNodeId
    private String domainid;    // 订阅应用域编号  SgAppHeader.srcAddr.domainId
    private String subscribeid; //  订阅者(登录用户)编号

    private ConcurrentHashMap<AlarmKey, SubscribeInfo> mapSubscribe = new ConcurrentHashMap<>();

    //添加订阅者
    public void AddSubscriber(AlarmKey alarmKey, SubscribeInfo subscribeInfo) {
        if (!this.mapSubscribe.containsKey(alarmKey)) {
            this.mapSubscribe.put(alarmKey, subscribeInfo);
        }
    }

    //判断是否订阅
    public boolean isSubscribed(AlarmKey alarmKey) {
        //表示全部订阅
        AlarmKey allAlarmKey = new AlarmKey(IMSConstant.DEF_ALL_NODE, IMSConstant.DEF_ALL_ALARMTYPE);
        //根据设备订阅
        AlarmKey mpAllAlarmKey = new AlarmKey(alarmKey.getMpid(), IMSConstant.DEF_ALL_ALARMTYPE);

       /* Iterator<Map.Entry<AlarmKey, SubscribeInfo>> iterator = mapSubscribe.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<AlarmKey, SubscribeInfo> entry = iterator.next();

            AlarmKey key = entry.getKey();

            if (key.getMpid() .equals(allAlarmKey.getMpid())
                    && key.getAlarmtype() .equals(allAlarmKey.getAlarmtype())) {

                return true;

            } else if (key.getMpid() .equals(mpAllAlarmKey.getMpid())
                    && key.getAlarmtype() .equals(mpAllAlarmKey.getAlarmtype()) ) {

                return true;

            } else if (key.getMpid() .equals(alarmKey.getMpid())
                    && key.getAlarmtype() .equals(alarmKey.getAlarmtype()) ) {
                
                return true;
            }
        }
*/
        if (this.mapSubscribe.containsKey(allAlarmKey)) {

            return true;

        } else if (this.mapSubscribe.containsKey(mpAllAlarmKey)) {

            return true;

        } else if (this.mapSubscribe.containsKey(alarmKey)) {

            return true;
        }
        return false;
    }


    //判断是否订阅
    public boolean isSubscribed(String mpid, String alarmtype) {
        //表示全部订阅
        AlarmKey alarmKey = new AlarmKey(mpid, alarmtype);

        return this.isSubscribed(alarmKey);

    }

    //判断订阅的信息是否存在，存在则返回，否则新加
    public void addSubscribe(String mpid, String alarmtype) {
        AlarmKey alarmKey = new AlarmKey(mpid, alarmtype);
        if (this.mapSubscribe.containsKey(alarmKey)) {
            return;
        }

        SubscribeInfo subscribeInfo = new SubscribeInfo();
        this.mapSubscribe.put(alarmKey, subscribeInfo);
    }


    //过滤出用户订阅的告警信息
    public List<RecAlarmInfo> FilterNotifyAlarm(List<RecAlarmInfo> alarmList) {

        List<RecAlarmInfo> notifyAlarmList = new ArrayList<>();

        //遍历循环告警信息，获取到每一个
        for (int i = 0; i < alarmList.size(); i++) {
            
            RecAlarmInfo alarmInfo = alarmList.get(i);
            AlarmKey alarmKey = new AlarmKey(alarmInfo.getDeviceid(), alarmInfo.getAlarmType());
            //判断是否是订阅者订阅的告警，如果是 则添加集合中，并返回
            if (this.isSubscribed(alarmKey)) {

                notifyAlarmList.add(alarmInfo);
            }

        }
        return notifyAlarmList;
    }

}

@ApiModel("主键")
class AlarmKey {
    private String mpid;  //设备id
    private String alarmtype;    //告警类型

    public AlarmKey() {

    }

    public AlarmKey(String mpid, String alarmtype) {
        this.mpid = mpid;
        this.alarmtype = alarmtype;
    }

    public String getMpid() {
        return mpid;
    }

    public void setMpid(String mpid) {
        this.mpid = mpid;
    }

    public String getAlarmtype() {
        return alarmtype;
    }

    public void setAlarmtype(String alarmtype) {
        this.alarmtype = alarmtype;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlarmKey)) return false;
        AlarmKey alarmKey = (AlarmKey) o;
        return getMpid().equals(alarmKey.getMpid()) &&
                getAlarmtype().equals(alarmKey.getAlarmtype());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMpid(), getAlarmtype());
    }
}




class SubscribeInfo {


}

