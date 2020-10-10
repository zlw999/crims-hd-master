package com.ireal.crims.sgws.interfaces;


import com.ireal.crims.sgws.enums.SgAppType;
import com.ireal.crims.sgws.enums.SgDataFormat;
import com.ireal.crims.sgws.structs.SgAppHeader;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public interface SgwsInterface {
	//初始化

	boolean Init(String sgServerEndPointsIp, int nConnPort, String wsClientNodeId, String wsClientDomainId) throws URISyntaxException;
	//反初始化
	void UnInit();
	//启动连接
	boolean OnStart();

	//停止连接
	void OnStop();

	//发送数据

 	boolean SendData(SgAppHeader sgAppHeader, String appData) throws UnsupportedEncodingException;

	//发送数据

	boolean SendData(SgAppType appType, int sequenceNo, int reqNo, SgDataFormat dataFormat, String destDomainId, String destAppNodeId, String appData) throws UnsupportedEncodingException;

	//设置回调函数
	boolean setCtrlCB(SgwsCallbackInterface ctrlCB);
}
