package com.ireal.crims.alarm.structs;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("告警订阅请求信息")
public class Protocol_SubscriberInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String subscriberid; //  订阅者(登录用户)编号

    public String getSubscriberid() {
        return subscriberid;
    }

    public void setSubscriberid(String subscriberid) {
        this.subscriberid = subscriberid;
    }
}
