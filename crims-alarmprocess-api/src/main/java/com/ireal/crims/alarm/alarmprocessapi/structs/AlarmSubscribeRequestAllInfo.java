package com.ireal.crims.alarm.alarmprocessapi.structs;

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

}
