package com.ireal.crims.alarm.alarmprocessapi.structs;

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


}
