package com.ireal.crims.alarm.alarmprocessapi.structs;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("告警订阅请求信息")
public class AlarmSubscribeRequestInfo {
    private String nodeid;      // 订阅应用节点编号 SgAppHeader.srcAddr.appNodeId
    private String domainid;    // 订阅应用域编号  SgAppHeader.srcAddr.domainId
    private String subscribeid; //  订阅者(登录用户)编号
}
