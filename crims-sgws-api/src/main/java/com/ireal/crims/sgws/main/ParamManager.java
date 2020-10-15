package com.ireal.crims.sgws.main;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParamManager {

    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static ParamManager instance = new ParamManager();
    }

    public static ParamManager newInstance() {
        return SingletonHolder.instance;
    }

    public static String serverIp;

    public static int serverPort;


}
