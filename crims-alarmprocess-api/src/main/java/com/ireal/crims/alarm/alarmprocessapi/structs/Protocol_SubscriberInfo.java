package com.ireal.crims.alarm.alarmprocessapi.structs;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("告警订阅请求信息")
public class Protocol_SubscriberInfo {

    private String subscriberid; //  订阅者(登录用户)编号

    public String getSubscriberid() {
        return subscriberid;
    }

    public void setSubscriberid(String subscriberid) {
        this.subscriberid = subscriberid;
    }
}
