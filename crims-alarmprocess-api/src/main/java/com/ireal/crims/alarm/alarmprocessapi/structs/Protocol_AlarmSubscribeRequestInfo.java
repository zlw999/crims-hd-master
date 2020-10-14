package com.ireal.crims.alarm.alarmprocessapi.structs;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("告警订阅请求信息")
public class Protocol_AlarmSubscribeRequestInfo {
    private String id;

    @JSONField(ordinal=2)
    private String name;

    @JSONField(ordinal=3)
    private String type;

    @JSONField(ordinal=4)

    private String result;

    @JSONField(ordinal=5)
    private Protocol_SubscriberInfo params = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Protocol_SubscriberInfo getParams() {
        return params;
    }

    public void setParams(Protocol_SubscriberInfo params) {
        this.params = params;
    }
}
