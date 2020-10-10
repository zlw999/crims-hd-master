package com.ireal.crims.nivm.device.interfaces;

import com.ireal.crims.nivm.device.structs.DeviceInfoSet;

public interface NivmDeviceInterface {
    //初始化
    boolean Init();

    //反初始化
    void UnInit();

    //启动
    boolean OnStart();

    //停止
    void OnStop();

    //设置回调函数
    //boolean setCtrlCB(DeviceCallbackInterface ctrlCB);

    DeviceInfoSet GetDeviceInfo();
}
