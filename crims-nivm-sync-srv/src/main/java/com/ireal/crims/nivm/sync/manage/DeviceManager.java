package com.ireal.crims.nivm.sync.manage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeviceManager {
    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static DeviceManager instance = new DeviceManager();
    }

    public static DeviceManager getInstance() {
        return SingletonHolder.instance;
    }

    private DeviceManager() {

    }
}
