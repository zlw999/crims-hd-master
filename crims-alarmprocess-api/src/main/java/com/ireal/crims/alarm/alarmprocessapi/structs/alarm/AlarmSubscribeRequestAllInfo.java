package com.ireal.crims.alarm.alarmprocessapi.structs.alarm;

import com.ireal.crims.common.enums.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("告警订阅请求信息")
public class AlarmSubscribeRequestAllInfo {


    private int sequenceNo;
    private int appType;
    private ErrorCodeEnum result;
    private AlarmSubscribeRequestInfo alarmSubscribeRequestInfo = new AlarmSubscribeRequestInfo();

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public ErrorCodeEnum getResult() {
        return result;
    }

    public void setResult(ErrorCodeEnum result) {
        this.result = result;
    }

    public AlarmSubscribeRequestInfo getAlarmSubscribeRequestInfo() {
        return alarmSubscribeRequestInfo;
    }

    public void setAlarmSubscribeRequestInfo(AlarmSubscribeRequestInfo alarmSubscribeRequestInfo) {
        this.alarmSubscribeRequestInfo = alarmSubscribeRequestInfo;
    }
}
