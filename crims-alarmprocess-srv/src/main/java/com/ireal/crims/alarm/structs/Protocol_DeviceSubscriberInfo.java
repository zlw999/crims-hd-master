package com.ireal.crims.alarm.structs;

import com.ireal.crims.alarm.alarmprocessapi.structs.device.DeviceInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther shkstart
 * @create 2020-10-15-9:35
 */

@Data
@ApiModel("设备状态订阅信息")
public class Protocol_DeviceSubscriberInfo {

     // "subscriberid": "10101010101010", "devlist"

    private  String  subscriberid;

    private List<DeviceInfo> devlist = new ArrayList<>();

    public String getSubscriberid() {
        return subscriberid;
    }

    public void setSubscriberid(String subscriberid) {
        this.subscriberid = subscriberid;
    }

    public List<DeviceInfo> getDevlist() {
        return devlist;
    }

    public void setDevlist(List<DeviceInfo> devlist) {
        this.devlist = devlist;
    }


}
