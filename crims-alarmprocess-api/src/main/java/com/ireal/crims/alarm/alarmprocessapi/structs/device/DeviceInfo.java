package com.ireal.crims.alarm.alarmprocessapi.structs.device;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @auther shkstart
 * @create 2020-10-15-9:37
 */
@Data
@ApiModel("设备信息")
public class DeviceInfo {

    private  String id;      //设备id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
