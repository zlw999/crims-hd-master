package com.ireal.crims.alarm.alarmprocessapi.structs;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @auther shkstart
 * @create 2020-10-13-11:29
 */

@ApiModel("设备信息")
public class DeviceInfo implements Serializable {

    private  String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
