package com.ireal.crims.sgws.test;

import com.alibaba.fastjson.annotation.JSONField;

public class MonitorItemRequestBody {
    @JSONField(ordinal=1)
    private String id;

    @JSONField(ordinal=2)
    private String name;

    @JSONField(ordinal=3)
    private String type;

    @JSONField(ordinal=4)
    private String result;

    @JSONField(ordinal=5)
    private String templetefile;

    @JSONField(ordinal=6)
    private String netparams;

    @JSONField(ordinal=7)
    private int sleepTime;

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

    public String getTempletefile() {
        return templetefile;
    }

    public void setTempletefile(String templetefile) {
        this.templetefile = templetefile;
    }

    public String getNetparams() {
        return netparams;
    }

    public void setNetparams(String netparams) {
        this.netparams = netparams;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }
}
