package com.ireal.crims.nivm.sync.manage;

import com.ireal.crims.sgws.main.SgwsClientMain;
import com.ireal.crims.sgws.structs.SgAppHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class UserManager {
    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static UserManager instance = new UserManager();
    }

    public static UserManager getInstance() {
        return SingletonHolder.instance;
    }

    private UserManager() {

    }

    private boolean SendRoleInfo()
    {
        //@TODO

        SgAppHeader appHeader = null;
        String appData  = null;
        try {
            SgwsClientMain.getInstance().SendData(appHeader, appData);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean SendUserInfo()
    {
        //@TODO

        SgAppHeader appHeader = null;
        String appData  = null;
        try {
            SgwsClientMain.getInstance().SendData(appHeader, appData);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return true;
    }

    private boolean SendUserRightInfo()
    {
        //@TODO

        SgAppHeader appHeader = null;
        String appData  = null;
        try {
            SgwsClientMain.getInstance().SendData(appHeader, appData);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return true;
    }
}
