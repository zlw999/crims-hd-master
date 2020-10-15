package com.ireal.crims.alarm.structs;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @auther shkstart
 * @create 2020-10-15-9:33
 */

@Data
@ApiModel("设备状态订阅请求信息")
public class Protocol_DeviceSubscriberRequestInfo {

    @JSONField(ordinal=1)
    private String id;

    @JSONField(ordinal=2)
    private String name;

    @JSONField(ordinal=3)
    private String type;

    @JSONField(ordinal=4)

    private String result;

    @JSONField(ordinal=5)
    private Protocol_DeviceSubscriberInfo params = null;

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

    public Protocol_DeviceSubscriberInfo getParams() {
        return params;
    }

    public void setParams(Protocol_DeviceSubscriberInfo params) {
        this.params = params;
    }
}
