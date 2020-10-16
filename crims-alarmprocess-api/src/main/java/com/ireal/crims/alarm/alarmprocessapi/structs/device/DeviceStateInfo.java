package com.ireal.crims.alarm.alarmprocessapi.structs.device;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ireal.crims.common.enums.NetConnectStateEnum;
import com.ireal.crims.common.utils.DateUtil;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("设备状态信息")
public class DeviceStateInfo {


    private String id = "";

    private NetConnectStateEnum connState = NetConnectStateEnum.Unknown;

    private Date stateTime = DateUtil.getDefaultTime();

    private String faultLevel = "00";

    private Date faultTime = DateUtil.getDefaultTime();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NetConnectStateEnum getConnState() {
        return connState;
    }

    public void setConnState(NetConnectStateEnum connState) {
        this.connState = connState;
    }

    public Date getStateTime() {
        return stateTime;
    }

    public void setStateTime(Date stateTime) {
        this.stateTime = stateTime;
    }

    public String getFaultLevel() {
        return faultLevel;
    }

    public void setFaultLevel(String faultLevel) {
        this.faultLevel = faultLevel;
    }

    public Date getFaultTime() {
        return faultTime;
    }

    public void setFaultTime(Date faultTime) {
        this.faultTime = faultTime;
    }
}
