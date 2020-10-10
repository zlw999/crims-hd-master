

package com.ireal.crims.alarm.main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParamManager {
    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static ParamManager instance = new ParamManager();
    }

    public static ParamManager getInstance() {
        return ParamManager.SingletonHolder.instance;
    }

   protected static String sgServerEndPointsIp;

    protected static int nConnPort;

    protected static String wsClientDomainId;

    protected static String wsClientNodeId;

    protected static String wsDestDomainId;

    protected static String wsDestAppNodeId;

}
