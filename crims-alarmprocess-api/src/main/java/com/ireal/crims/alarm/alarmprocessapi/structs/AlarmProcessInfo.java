package com.ireal.crims.alarm.alarmprocessapi.structs;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
 @Data
 @ApiModel(value = "和服务端传送数据的告警信息处理实体类")
public class AlarmProcessInfo {
    @JSONField(ordinal=1)
    private String userid;

    @JSONField(ordinal=2)
    private String username;

    @JSONField(ordinal=3)
    private String cmd;

    @JSONField(ordinal=4)
    private List<AlarmInfo> alarmlist = new ArrayList<AlarmInfo>();

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public List<AlarmInfo> getAlarmlist() {
        return alarmlist;
    }

    public void setAlarmlist(List<AlarmInfo> alarmlist) {
        this.alarmlist = alarmlist;
    }
}
