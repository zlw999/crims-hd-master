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
}
