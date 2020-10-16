package com.ireal.crims.alarm.alarmprocessapi.structs.device;

import com.ireal.crims.common.enums.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @auther shkstart
 * @create 2020-10-15-9:50
 */

@Data
@ApiModel("设备状态订阅请求的全部信息")
public class DeviceStateSubReqAllInfo {

    private int sequenceNo;

    private int appType;

    private ErrorCodeEnum result;

    private DeviceStateSubReqInfo deviceStateSubReqInfo = new DeviceStateSubReqInfo();

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

    public DeviceStateSubReqInfo getDeviceStateSubReqInfo() {
        return deviceStateSubReqInfo;
    }

    public void setDeviceStateSubReqInfo(DeviceStateSubReqInfo deviceStateSubReqInfo) {
        this.deviceStateSubReqInfo = deviceStateSubReqInfo;
    }
}
