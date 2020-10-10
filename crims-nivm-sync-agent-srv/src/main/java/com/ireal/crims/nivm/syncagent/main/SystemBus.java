package com.ireal.crims.nivm.syncagent.main;

import com.ireal.crims.common.enums.MsgCmdEnum;
import com.ireal.crims.nivm.syncagent.manage.DeviceManager;
import com.ireal.crims.sgws.interfaces.SgwsCallbackInterface;
import com.ireal.crims.sgws.structs.SgAppHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class SystemBus implements SgwsCallbackInterface
{
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
        if( cmd == null )
        {
            return false;
        }

        switch (cmd)
        {
            case NivmRoleInfoSync:    // 角色信息同步
            {

            }
            break;
            case NivmUserInfoSync:    // 用户信息同步
            {

            }
            break;
            case NivmUserRightSync:    // 用户权限同步
            {

            }
            break;
            case NivmDeviceInfoSync:    // 设备信息同步
            {
                DeviceManager.getInstance().ParseDeviceSyncRequest(sgAppHeader, appData);
            }
            break;
            default:
                break;
        }

        return false;
    }
}
