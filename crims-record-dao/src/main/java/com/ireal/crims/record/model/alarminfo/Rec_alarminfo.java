package com.ireal.crims.record.model.alarminfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageInfo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther shkstart
 * @create 2020-09-05-9:17
 */
@Data
public class Rec_alarminfo implements Serializable {

    private Integer id;

    private String appsvrid;

    private String nodeaid;

    private String nodeaname;

    private String nodename;

    private String deviceid;

    private String devicename;

    private String mpid;

    private String mpname;

    private String alarmindex;

    private String alarmtype;

    private String alarmlevel;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date alarmtime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date alarmdistime;

    private String alarmvalue;

    private String alarmaffirmuserid;

    private String alarmaffirmusername;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date alarmaffirmtime;

    private String alarmclearuserid;

    private String alarmclearusername;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date alarmcleartime;

    private String alarmreportuserid;

    private String alarmreportusername;

    private String alarmdsp;

    private String worderid;

    private String groupnodecode;

    private byte[] timeinfo;

    private String ipaddress;


    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private  Date updatetime;

    private  Integer changenum;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppsvrid() {
        return appsvrid;
    }

    public void setAppsvrid(String appsvrid) {
        this.appsvrid = appsvrid == null ? null : appsvrid.trim();
    }

    public String getNodeaid() {
        return nodeaid;
    }

    public void setNodeaid(String nodeaid) {
        this.nodeaid = nodeaid == null ? null : nodeaid.trim();
    }

    public String getNodeaname() {
        return nodeaname;
    }

    public void setNodeaname(String nodeaname) {
        this.nodeaname = nodeaname == null ? null : nodeaname.trim();
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename == null ? null : nodename.trim();
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename == null ? null : devicename.trim();
    }

    public String getMpid() {
        return mpid;
    }

    public void setMpid(String mpid) {
        this.mpid = mpid == null ? null : mpid.trim();
    }

    public String getMpname() {
        return mpname;
    }

    public void setMpname(String mpname) {
        this.mpname = mpname == null ? null : mpname.trim();
    }

    public String getAlarmindex() {
        return alarmindex;
    }

    public void setAlarmindex(String alarmindex) {
        this.alarmindex = alarmindex == null ? null : alarmindex.trim();
    }

    public String getAlarmtype() {
        return alarmtype;
    }

    public void setAlarmtype(String alarmtype) {
        this.alarmtype = alarmtype == null ? null : alarmtype.trim();
    }

    public String getAlarmlevel() {
        return alarmlevel;
    }

    public void setAlarmlevel(String alarmlevel) {
        this.alarmlevel = alarmlevel;
    }

    public Date getAlarmtime() {
        return alarmtime;
    }

    public void setAlarmtime(Date alarmtime) {
        this.alarmtime = alarmtime;
    }

    public Date getAlarmdistime() {
        return alarmdistime;
    }

    public void setAlarmdistime(Date alarmdistime) {
        this.alarmdistime = alarmdistime;
    }

    public String getAlarmvalue() {
        return alarmvalue;
    }

    public void setAlarmvalue(String alarmvalue) {
        this.alarmvalue = alarmvalue == null ? null : alarmvalue.trim();
    }

    public String getAlarmaffirmuserid() {
        return alarmaffirmuserid;
    }

    public void setAlarmaffirmuserid(String alarmaffirmuserid) {
        this.alarmaffirmuserid = alarmaffirmuserid == null ? null : alarmaffirmuserid.trim();
    }

    public String getAlarmaffirmusername() {
        return alarmaffirmusername;
    }

    public void setAlarmaffirmusername(String alarmaffirmusername) {
        this.alarmaffirmusername = alarmaffirmusername == null ? null : alarmaffirmusername.trim();
    }

    public Date getAlarmaffirmtime() {
        return alarmaffirmtime;
    }

    public void setAlarmaffirmtime(Date alarmaffirmtime) {
        this.alarmaffirmtime = alarmaffirmtime;
    }

    public String getAlarmclearuserid() {
        return alarmclearuserid;
    }

    public void setAlarmclearuserid(String alarmclearuserid) {
        this.alarmclearuserid = alarmclearuserid == null ? null : alarmclearuserid.trim();
    }

    public String getAlarmclearusername() {
        return alarmclearusername;
    }

    public void setAlarmclearusername(String alarmclearusername) {
        this.alarmclearusername = alarmclearusername == null ? null : alarmclearusername.trim();
    }

    public Date getAlarmcleartime() {
        return alarmcleartime;
    }

    public void setAlarmcleartime(Date alarmcleartime) {
        this.alarmcleartime = alarmcleartime;
    }

    public String getAlarmreportuserid() {
        return alarmreportuserid;
    }

    public void setAlarmreportuserid(String alarmreportuserid) {
        this.alarmreportuserid = alarmreportuserid == null ? null : alarmreportuserid.trim();
    }

    public String getAlarmreportusername() {
        return alarmreportusername;
    }

    public void setAlarmreportusername(String alarmreportusername) {
        this.alarmreportusername = alarmreportusername == null ? null : alarmreportusername.trim();
    }

    public String getAlarmdsp() {
        return alarmdsp;
    }

    public void setAlarmdsp(String alarmdsp) {
        this.alarmdsp = alarmdsp == null ? null : alarmdsp.trim();
    }

    public String getWorderid() {
        return worderid;
    }

    public void setWorderid(String worderid) {
        this.worderid = worderid == null ? null : worderid.trim();
    }

    public String getGroupnodecode() {
        return groupnodecode;
    }

    public void setGroupnodecode(String groupnodecode) {
        this.groupnodecode = groupnodecode == null ? null : groupnodecode.trim();
    }

    public byte[] getTimeinfo() {
        return timeinfo;
    }

    public void setTimeinfo(byte[] timeinfo) {
        this.timeinfo = timeinfo;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getChangenum() {
        return changenum;
    }

    public void setChangenum(Integer changenum) {
        this.changenum = changenum;
    }

}
