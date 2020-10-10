package com.ireal.crims.nivm.syncagent.manage;

import com.ireal.crims.common.enums.MsgCmdEnum;
import com.ireal.crims.nivm.device.main.NivmDeviceApiMain;
import com.ireal.crims.nivm.device.structs.DeviceInfoSet;
import com.ireal.crims.nivm.syncagent.process.SyncMsgProcesser;
import com.ireal.crims.sgws.main.SgwsClientMain;
import com.ireal.crims.sgws.structs.SgAppHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class DeviceManager {
    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static DeviceManager instance = new DeviceManager();
    }

    public static DeviceManager getInstance() {
        return DeviceManager.SingletonHolder.instance;
    }

    private DeviceManager() {

    }

    public boolean ParseDeviceSyncRequest(SgAppHeader sgAppHeader, String appData)
    {
        MsgCmdEnum msgCmd = MsgCmdEnum.getEnumType(sgAppHeader.getReqNo());
        if( msgCmd != null )
        {
            SyncMsgProcesser.getInstance().processSyncMsg(sgAppHeader);
        }

        return true;
    }

    public boolean BuildDeviceSyncResponse(SgAppHeader srcSgAppHeader)
    {
        // 1.提取设备信息
        DeviceInfoSet deviceInfoSet = NivmDeviceApiMain.getInstance().GetDeviceInfo();

        // 2.组织协议头
        SgAppHeader respAppHeader = null;

        // 3.将设备对象信息转换成JSON格式
        String deviceData = null;
        this.SendDeviceInfo(respAppHeader, deviceData);

        return true;
    }

    private boolean SendDeviceInfo(SgAppHeader appHeader, String appData)
    {
        //@TODO

        try {
            SgwsClientMain.getInstance().SendData(appHeader, appData);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return true;
    }
}
