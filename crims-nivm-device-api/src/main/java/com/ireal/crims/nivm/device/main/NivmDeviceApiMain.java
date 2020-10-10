package com.ireal.crims.nivm.device.main;

import com.ireal.crims.nivm.device.interfaces.NivmDeviceInterface;
import com.ireal.crims.nivm.device.structs.DeviceInfoSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NivmDeviceApiMain implements NivmDeviceInterface {
    public Logger logger = LoggerFactory.getLogger(getClass());

    private static class SingletonHolder {
        public static NivmDeviceApiMain instance = new NivmDeviceApiMain();
    }

    public static NivmDeviceApiMain getInstance()
    {
        return SingletonHolder.instance;
    }

    @Override
    public boolean Init() {
        return true;
    }

    @Override
    public void UnInit() {

    }

    @Override
    public boolean OnStart() {
        return true;
    }

    @Override
    public void OnStop() {

    }

    @Override
    public DeviceInfoSet GetDeviceInfo() {
        return null;
    }

}
