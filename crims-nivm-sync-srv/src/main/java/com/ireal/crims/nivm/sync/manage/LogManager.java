package com.ireal.crims.nivm.sync.manage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogManager {
    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static LogManager instance = new LogManager();
    }

    public static LogManager getInstance() {
        return SingletonHolder.instance;
    }

    private LogManager() {

    }
}
