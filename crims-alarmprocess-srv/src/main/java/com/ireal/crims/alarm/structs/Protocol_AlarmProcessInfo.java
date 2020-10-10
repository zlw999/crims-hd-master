package com.ireal.crims.alarm.structs;

import com.alibaba.fastjson.annotation.JSONField;
import com.ireal.crims.alarm.alarmprocessapi.structs.AlarmProcessInfo;

public class Protocol_AlarmProcessInfo {
    @JSONField(ordinal=1)
    private String id;

    @JSONField(ordinal=2)
    private String name;

    @JSONField(ordinal=3)
    private String type;

    @JSONField(ordinal=4)
    private String result;
    @JSONField(ordinal=5)
    private AlarmProcessInfo params = null;

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

    public AlarmProcessInfo getParams() {
        return params;
    }

    public void setParams(AlarmProcessInfo params) {
        this.params = params;
    }
}
