package com.ireal.crims.alarm.alarmprocessapi.structs;

import com.ireal.crims.common.enums.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "告警处理的响应信息实体类")
public class AlarmProcessResponseInfo implements Serializable {
    private ErrorCodeEnum errorCode = null;     // 处理错误号

    private Date receiveTime = null;            // 接收响应的时间


    private AlarmProcessInfo alarmProcessInfo = null;   // 响应的告警信息


    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public AlarmProcessInfo getAlarmProcessInfo() {
        return alarmProcessInfo;
    }

    public void setAlarmProcessInfo(AlarmProcessInfo alarmProcessInfo) {
        this.alarmProcessInfo = alarmProcessInfo;
    }
}
