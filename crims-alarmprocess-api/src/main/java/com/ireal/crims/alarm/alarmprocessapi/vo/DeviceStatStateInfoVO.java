package com.ireal.crims.alarm.alarmprocessapi.vo;

public class DeviceStatStateInfoVO {
    private String lineId		= "";		// 线路编号
    private String lineName	= "";		// 线路名称
    private String stationId	= "";		// 车站编号
    private String stationName	= "";		// 车站名称

    private int devCount		= 0;		// 设备数量
    private int offlineCount	= 0;		// 离线的设备数量
    private int faultCount		= 0;		// 存在故障的设备数量

    private String onlineRatio	= "";		// 设备在线率(保留1位小数，单位%)
    private String faultRatio	= "";		// 设备故障率(保留1位小数，单位%)

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getDevCount() {
        return devCount;
    }

    public void setDevCount(int devCount) {
        this.devCount = devCount;
    }

    public int getOfflineCount() {
        return offlineCount;
    }

    public void setOfflineCount(int offlineCount) {
        this.offlineCount = offlineCount;
    }

    public int getFaultCount() {
        return faultCount;
    }

    public void setFaultCount(int faultCount) {
        this.faultCount = faultCount;
    }

    public String getOnlineRatio() {
        return onlineRatio;
    }

    public void setOnlineRatio(String onlineRatio) {
        this.onlineRatio = onlineRatio;
    }

    public String getFaultRatio() {
        return faultRatio;
    }

    public void setFaultRatio(String faultRatio) {
        this.faultRatio = faultRatio;
    }
}
