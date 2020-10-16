package com.ireal.crims.alarm.main;

import com.alibaba.fastjson.JSON;
import com.ireal.crims.alarm.alarmprocessapi.interfaces.AlarmProcessCallbackInterface;
import com.ireal.crims.alarm.alarmprocessapi.main.AlarmProcessApiMain;
import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.AlarmNotifyInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.AlarmProcessInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.AlarmSubscribeRequestInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.alarm.RecAlarmInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.device.DeviceStateInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.device.DeviceStateNotifyInfo;
import com.ireal.crims.alarm.alarmprocessapi.structs.device.DeviceStateSubReqInfo;
import com.ireal.crims.alarm.structs.Protocol_AlarmProcessInfo;
import com.ireal.crims.alarm.structs.Protocol_AlarmSubscribeRequestInfo;
import com.ireal.crims.alarm.structs.Protocol_DeviceSubscriberRequestInfo;
import com.ireal.crims.common.enums.ErrorCodeEnum;
import com.ireal.crims.common.enums.MsgCmdEnum;
import com.ireal.crims.sgws.enums.SgAppType;
import com.ireal.crims.sgws.enums.SgDataFormat;
import com.ireal.crims.sgws.interfaces.SgwsCallbackInterface;
import com.ireal.crims.sgws.main.SgwsClientMain;
import com.ireal.crims.sgws.structs.SgAppHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.UnsupportedEncodingException;
import java.util.List;

public class SystemBus implements SgwsCallbackInterface,
        AlarmProcessCallbackInterface {

    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static SystemBus instance = new SystemBus();
    }

    public static SystemBus getInstance() {
        return SingletonHolder.instance;
    }

    private SystemBus() {

    }

    @Override
    public boolean OnReceiveData(SgAppHeader sgAppHeader, String appData) throws UnsupportedEncodingException {

        int reqNo = sgAppHeader.getReqNo();
        MsgCmdEnum cmd = MsgCmdEnum.getEnumType(reqNo);
        if (cmd == null) {
            return false;
        }
        switch (cmd) {
            case AlarmNotify:    // 告警通知
            {

                //json字符串转为对象
                AlarmNotifyInfo alarmNotifyInfo = JSON.parseObject(appData, AlarmNotifyInfo.class);
                if (alarmNotifyInfo == null) {
                    return false;
                }

                ErrorCodeEnum result = ErrorCodeEnum.SUCCESS; // protocolAlarmProcessInfo.getResult();

                AlarmProcessApiMain.getInstance().OnAlarmNotify(sgAppHeader.getSequenceNo(), sgAppHeader.getAppType(), result, alarmNotifyInfo);

            }
            break;
            case AlarmProcess:    // 告警处理
            {
                //@TODO:解析告警处理响应协议

                //json字符串转为对象

                Protocol_AlarmProcessInfo protocolAlarmProcessInfo = JSON.parseObject(appData, Protocol_AlarmProcessInfo.class);

                if (null == protocolAlarmProcessInfo) {
                    return false;
                }
                //===============================================================================
                AlarmProcessInfo alarmProcessInfo = protocolAlarmProcessInfo.getParams();
                ErrorCodeEnum result = ErrorCodeEnum.SUCCESS; // protocolAlarmProcessInfo.getResult();

                AlarmProcessApiMain.getInstance().OnAlarmProcessResponse(sgAppHeader.getSequenceNo(), sgAppHeader.getAppType(), result, alarmProcessInfo);
            }
            break;
            //==================================================================================
            case AlarmSubscribe:    // 告警订阅
            {

                //     String json = "{\"id\": \"0x03040203\",\"name\": \"AlarmSubscribe\",\"type\": \"01\",\"result\": \"01\",\"params\": {\"subscriberid\": \"10101010101010\"}}";
                Protocol_AlarmSubscribeRequestInfo pro_alarmSubscribeRequestInfo
                        = JSON.parseObject(appData, Protocol_AlarmSubscribeRequestInfo.class);

                if (null == pro_alarmSubscribeRequestInfo) {
                    return false;
                }
                ErrorCodeEnum result = ErrorCodeEnum.SUCCESS; // protocolAlarmProcessInfo.getResult();

                AlarmSubscribeRequestInfo alarmSubscribeRequestInfo = new AlarmSubscribeRequestInfo();
                alarmSubscribeRequestInfo.setNodeid(sgAppHeader.getSrcAddr().getAppNodeId());
                alarmSubscribeRequestInfo.setDomainid(sgAppHeader.getSrcAddr().getDomainId());
                alarmSubscribeRequestInfo.setSubscribeid(pro_alarmSubscribeRequestInfo.getParams().getSubscriberid());


                AlarmProcessApiMain.getInstance().OnAlarmSubscribe(sgAppHeader.getSequenceNo(), sgAppHeader.getAppType(), result, alarmSubscribeRequestInfo);
            }
            break;


            case DeviceStatusSubscribe:   //设备状态订阅
            {

                Protocol_DeviceSubscriberRequestInfo protocol_devSubInfo = JSON.parseObject(appData, Protocol_DeviceSubscriberRequestInfo.class);

                if (null == protocol_devSubInfo) {
                    return false;
                }

                DeviceStateSubReqInfo devStateSubReqInfo = new DeviceStateSubReqInfo();
                devStateSubReqInfo.setDomainid(sgAppHeader.getSrcAddr().getDomainId());
                devStateSubReqInfo.setNodeid(sgAppHeader.getSrcAddr().getAppNodeId());
                devStateSubReqInfo.setSubscribeid(protocol_devSubInfo.getParams().getSubscriberid());
                devStateSubReqInfo.setDevlist(protocol_devSubInfo.getParams().getDevlist());

                ErrorCodeEnum result = ErrorCodeEnum.SUCCESS;

                AlarmProcessApiMain.getInstance().OnDeviceStateSubscriber(sgAppHeader.getSequenceNo(), sgAppHeader.getAppType(), result, devStateSubReqInfo);
            }
            break;

            case DeviceStatusNotify: //设备状态通知
            {
             DeviceStateNotifyInfo deviceStateNotifyInfo = JSON.parseObject(appData, DeviceStateNotifyInfo.class);

                if (null == deviceStateNotifyInfo) {

                    return false;
                }
                ErrorCodeEnum result = ErrorCodeEnum.SUCCESS;

                AlarmProcessApiMain.getInstance().OnDeviceStateNotify(sgAppHeader.getSequenceNo(), sgAppHeader.getAppType(), result, deviceStateNotifyInfo);

            }
            break;
            default:
                break;
        }
        return false;
    }

    /////告警的请求处理
    @Override
    public int OnAlarmProcessRequest(AlarmProcessInfo alarmProcessInfo) {

        SgAppType appType = SgAppType.APP_REQUEST;
        int sequenceNo = SgwsClientMain.getInstance().getProtocolRequenceNo();

        int reqNo = MsgCmdEnum.AlarmProcess.getCmd();
        SgDataFormat dataFormat = SgDataFormat.DATA_JSON;

        String destDomainId = ParamManager.wsDestDomainId;
        String destAppNodeId = ParamManager.wsDestAppNodeId;


        //SgAppHeader appHeader = sgAppHeader();
        // @TODO:告警处理请求信息进行协议封装，然后通过消息模块发送

        Protocol_AlarmProcessInfo protocolAlarmProcessInfo = new Protocol_AlarmProcessInfo();
        String hexString = Integer.toHexString(MsgCmdEnum.AlarmProcess.getCmd());
        int len = 8 - hexString.length();
        for (int i = 0; i < len; i++) {
            hexString = "0" + hexString;
        }
        protocolAlarmProcessInfo.setId("0x" + hexString);//   MsgCmdEnum.AlarmProcess;
        protocolAlarmProcessInfo.setName(MsgCmdEnum.AlarmProcess.getCmdname());       //MsgCmdEnum.AlarmProcess.getCmdname();
        protocolAlarmProcessInfo.setType(String.valueOf(SgAppType.APP_REQUEST.getType()));       //SgAppType.APP_REQUEST;
        protocolAlarmProcessInfo.setResult(ErrorCodeEnum.SUCCESS.getCode());        //ErrorCodeEnum.SUCCESS;
        protocolAlarmProcessInfo.setParams(alarmProcessInfo);

        //json 对象转字符串
        String appData = JSON.toJSONString(protocolAlarmProcessInfo);
        try {
            //通过消息模块发送
            if (!SgwsClientMain.getInstance().SendData(appType, sequenceNo, reqNo, dataFormat, destDomainId, destAppNodeId, appData)) {
                return 0;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return sequenceNo;
    }


    //TODO 集中告警服务中回调函数接口的方法，用以调用该方法  发送告警通知
    @Override
    public int OnAlarmNotify(String targetNodeId, String targetDomainId, List<RecAlarmInfo> alarmList) {
        SgAppType appType = SgAppType.APP_REQUEST;
        int sequenceNo = SgwsClientMain.getInstance().getProtocolRequenceNo();
        int reqNo = MsgCmdEnum.AlarmNotify.getCmd();
        SgDataFormat dataFormat = SgDataFormat.DATA_JSON_ANSI;

        //集合转json对象
        Object appdata = JSON.toJSON(alarmList);

        try {
            //通过消息模块发送

            if (!SgwsClientMain.getInstance().SendData(appType, sequenceNo, reqNo, dataFormat, targetDomainId, targetNodeId, appdata.toString())) {
                return 0;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return sequenceNo;


    }

    @Override
    public int OnDevStatusNotify(String targetNodeId, String targetDomainId, List<DeviceStateInfo> devList) {
        SgAppType appType = SgAppType.APP_REQUEST;
        int sequenceNo = SgwsClientMain.getInstance().getProtocolRequenceNo();
        int reqNo = MsgCmdEnum.DeviceStatusSubscribe.getCmd();
        SgDataFormat dataFormat = SgDataFormat.DATA_JSON_ANSI;

        //集合转json对象
        Object appdata = JSON.toJSON(devList);

        try {
            //通过消息模块发送

            if (!SgwsClientMain.getInstance().SendData(appType, sequenceNo, reqNo, dataFormat, targetDomainId, targetNodeId, appdata.toString())) {
                return 0;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return sequenceNo;
    }

    //将头的的信息封装  SequenceNo
/*    private static SgAppHeader sgAppHeader() {

        SgAppHeader appHeader = new SgAppHeader();
        //从 DataCache 缓存实例中获取
        appHeader.setSequenceNo(DataCache.getInstance().getProtocolRequenceNo());
        appHeader.setAppType((byte) SgAppType.APP_REQUEST.getType());
        appHeader.setTotalPacketCount((short) 1);
        appHeader.setCurrentPacketNo((short) 1);
        appHeader.getSrcAddr().setAppNodeId(ParamManager.wsClientNodeId);
        appHeader.getSrcAddr().setDomainId(ParamManager.wsClientDomainId);
        appHeader.getDestAddr().setAppNodeId(ParamManager.wsDestAppNodeId);
        appHeader.getDestAddr().setDomainId(ParamManager.wsDestDomainId);
        appHeader.setReqNo(MsgCmdEnum.AlarmProcess.getCmd());
        appHeader.setReqName("cmd1");
        appHeader.setDataFormat((byte) SgDataFormat.DATA_JSON_ANSI.getDataType());
        // int i = Integer.valueOf(ErrorCodeEnum.SUCCESS.getCode());
        int i = 0;
        appHeader.setResult(i);
        appHeader.setAdditionalDestCount((short) 0);
        appHeader.setReserved("");
        return appHeader;
    }

    //10进制转16进制
    private static String decimalToHax(int decimal) {
         String hex = "";
        while (decimal != 0) {
            int hexValue = decimal % 16;
            hex = toHexChar(hexValue) + hex;
            decimal = decimal / 16;
        }
        return hex;
    }
    private static char toHexChar(int hexValue) {
        if (hexValue <= 9 && hexValue >= 0)
            return (char) (hexValue + '0');
        else
            return (char) (hexValue - 10 + 'A');
    }*/
}
