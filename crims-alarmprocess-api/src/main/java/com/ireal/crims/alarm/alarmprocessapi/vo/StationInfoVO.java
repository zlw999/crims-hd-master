package com.ireal.crims.alarm.alarmprocessapi.vo;

public class StationInfoVO {
    private String lineId       = "";		// 线路编号
    private String lineName     = "";		// 线路名称
    private String stationId    = "";	    // 车站编号
    private String stationName  = "";       // 车站名称
    private float mileage       = 0.0f;     // 与起点站相距的里程
    private float longitude     = 0.0f;     // 经度
    private float latitude      = 0.0f;     // 纬度
    private float altitude      = 0.0f;     // 高度(海拔)

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

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }
}
