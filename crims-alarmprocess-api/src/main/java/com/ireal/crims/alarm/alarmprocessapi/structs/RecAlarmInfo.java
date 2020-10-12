package com.ireal.crims.alarm.alarmprocessapi.structs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data 
public class RecAlarmInfo implements Serializable {
    private String	deviceid;
    private String	devicename;
    private String	ipaddress;
    private String	mpid;
    private String	mpname;
    private String	alarmIndex;
    private String	alarmType;
    private String	alarmLevel;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date	alarmTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date alarmDisTime;
    private String	alarmValue;
    private String	alarmAffirmUserid;
    private String	alarmAffirmUsername;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date	alarmAffirmTime;
    private String	alarmClearUserid;
    private String	alarmClearUserName;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date	alarmClearTime;
    private String	alarmReportUserid;
    private String	alarmReportUername;
    private String	alarmDsp;
    private String	wOrderid;
    private int	changnum;
    private String	groupNodeCode;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date	updatetime;


    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getMpid() {
        return mpid;
    }

    public void setMpid(String mpid) {
        this.mpid = mpid;
    }

    public String getMpname() {
        return mpname;
    }

    public void setMpname(String mpname) {
        this.mpname = mpname;
    }

    public String getAlarmIndex() {
        return alarmIndex;
    }

    public void setAlarmIndex(String alarmIndex) {
        this.alarmIndex = alarmIndex;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Date getAlarmDisTime() {
        return alarmDisTime;
    }

    public void setAlarmDisTime(Date alarmDisTime) {
        this.alarmDisTime = alarmDisTime;
    }

    public String getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(String alarmValue) {
        this.alarmValue = alarmValue;
    }

    public String getAlarmAffirmUserid() {
        return alarmAffirmUserid;
    }

    public void setAlarmAffirmUserid(String alarmAffirmUserid) {
        this.alarmAffirmUserid = alarmAffirmUserid;
    }

    public String getAlarmAffirmUsername() {
        return alarmAffirmUsername;
    }

    public void setAlarmAffirmUsername(String alarmAffirmUsername) {
        this.alarmAffirmUsername = alarmAffirmUsername;
    }

    public Date getAlarmAffirmTime() {
        return alarmAffirmTime;
    }

    public void setAlarmAffirmTime(Date alarmAffirmTime) {
        this.alarmAffirmTime = alarmAffirmTime;
    }

    public String getAlarmClearUserid() {
        return alarmClearUserid;
    }

    public void setAlarmClearUserid(String alarmClearUserid) {
        this.alarmClearUserid = alarmClearUserid;
    }

    public String getAlarmClearUserName() {
        return alarmClearUserName;
    }

    public void setAlarmClearUserName(String alarmClearUserName) {
        this.alarmClearUserName = alarmClearUserName;
    }

    public Date getAlarmClearTime() {
        return alarmClearTime;
    }

    public void setAlarmClearTime(Date alarmClearTime) {
        this.alarmClearTime = alarmClearTime;
    }

    public String getAlarmReportUserid() {
        return alarmReportUserid;
    }

    public void setAlarmReportUserid(String alarmReportUserid) {
        this.alarmReportUserid = alarmReportUserid;
    }

    public String getAlarmReportUername() {
        return alarmReportUername;
    }

    public void setAlarmReportUername(String alarmReportUername) {
        this.alarmReportUername = alarmReportUername;
    }

    public String getAlarmDsp() {
        return alarmDsp;
    }

    public void setAlarmDsp(String alarmDsp) {
        this.alarmDsp = alarmDsp;
    }

    public String getwOrderid() {
        return wOrderid;
    }

    public void setwOrderid(String wOrderid) {
        this.wOrderid = wOrderid;
    }

    public int getChangnum() {
        return changnum;
    }

    public void setChangnum(int changnum) {
        this.changnum = changnum;
    }

    public String getGroupNodeCode() {
        return groupNodeCode;
    }

    public void setGroupNodeCode(String groupNodeCode) {
        this.groupNodeCode = groupNodeCode;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
