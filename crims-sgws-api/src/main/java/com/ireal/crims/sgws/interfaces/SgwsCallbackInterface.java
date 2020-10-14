package com.ireal.crims.sgws.interfaces;


import com.ireal.crims.sgws.structs.SgAppHeader;

import java.io.UnsupportedEncodingException;

//回调接口
public interface SgwsCallbackInterface {

	//接收数据
	boolean OnReceiveData(SgAppHeader sgAppHeader, String appData) throws UnsupportedEncodingException;

	//接收数据
	//boolean OnReceiveData(SgAppType appType, int sequenceNo, int reqNo, SgDataFormat dataFormat, String appData) throws UnsupportedEncodingException;

}
