package com.ireal.crims.nivm.syncagent.manage;

import com.ireal.crims.sgws.main.SgwsClientMain;
import com.ireal.crims.sgws.structs.SgAppHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class LogManager {
    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static LogManager instance = new LogManager();
    }

    public static LogManager getInstance() {
        return LogManager.SingletonHolder.instance;
    }

    private LogManager() {

    }

    private boolean SendLogInfo()
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
