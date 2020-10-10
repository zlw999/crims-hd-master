package com.ireal.crims.sgws.structs;

import com.ireal.crims.sgws.enums.SgAppType;

public class SgAppHeader {

	public static final int MsgAppHeaderLen = 308;
	public static final String Protocol_CharsetName_Unicode = "Unicode";	// "GBK";
	public static final String Protocol_CharsetName_GBK = "GBK";			// "GBK";
	public static final String Protocol_CharsetName_UTF8 = "UTF-8";			// "GBK";

    private byte appType; 					    // 1字节        ///< qint8, 低7位：1.请求，2.事件，3.应答，最高位，如果为1表示由于目标地址无效，而被退回的帧。见Sg.APP_TYPE。

    private int sequenceNo;	 				// 4字节		///< qint32 发送序号，应答消息需要返回一样的sequenceNo。

    private short totalPacketCount  	= 1;	// 2字节		///< qint16 总包数

    private short currentPacketNo   	= 1; 	// 2字节		///< qint16当前包号
    private SgAddr srcAddr = new SgAddr();   	// 32+32字节	///< 源地址
    private SgAddr destAddr = new SgAddr(); 	// 32+32字节	///< 目标地址
    private String reqName = "";				// 32字节		///< 请求的名称，填写应用请求号的说明，可为空
    private int reqNo; 						// 4字节 		///< qint32, 应用请求号，决定了reqName
    private int result					= 0;	// 4字节		///< qint32, 响应结果，对于appType为3时有意义
    private byte dataFormat;					// 1字节		///< qint8, 应用数据格式，1：二进制 2：Xml(Unicode) 3：Json(Unicode) 4：Xml(ANSI) 5：Json(ANSI); 见Sg.DATA_FORMAT
    private short additionalDestCount 	= 0; 	// 2字节  		///< qint16目标个数，为零时表示无目标列表

    private String reserved = ""; 				// 128字节		///<保留
    
	public byte getAppType() {
		return appType;
	}

	public void setAppType(byte appType) {
		this.appType = appType;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public short getTotalPacketCount() {
		return totalPacketCount;
	}

	public void setTotalPacketCount(short totalPacketCount) {
		this.totalPacketCount = totalPacketCount;
	}

	public short getCurrentPacketNo() {
		return currentPacketNo;
	}

	public void setCurrentPacketNo(short currentPacketNo) {
		this.currentPacketNo = currentPacketNo;
	}

	public SgAddr getSrcAddr() {
		return srcAddr;
	}

	public void setSrcAddr(SgAddr srcAddr) {
		this.srcAddr = srcAddr;
	}

	public SgAddr getDestAddr() {
		return destAddr;
	}

	public void setDestAddr(SgAddr destAddr) {
		this.destAddr = destAddr;
	}

	public String getReqName() {
		return reqName;
	}

	public void setReqName(String reqName) {
		this.reqName = reqName;
	}

	public int getReqNo() {
		return reqNo;
	}

	public void setReqNo(int reqNo) {
		this.reqNo = reqNo;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public byte getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(byte dataFormat) {
		this.dataFormat = dataFormat;
	}

	public short getAdditionalDestCount() {
		return additionalDestCount;
	}

	public void setAdditionalDestCount(short additionalDestCount) {
		this.additionalDestCount = additionalDestCount;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public static SgAppHeader getResponseAppHeader(SgAppHeader reqSgAppHeader)
    {
        SgAppHeader respAppHeader = new SgAppHeader();

        respAppHeader.setAppType((byte)SgAppType.APP_ACK.getType());
        respAppHeader.setSequenceNo(reqSgAppHeader.getSequenceNo());
        respAppHeader.getSrcAddr().setDomainId(reqSgAppHeader.getDestAddr().getDomainId());
        respAppHeader.getSrcAddr().setAppNodeId(reqSgAppHeader.getDestAddr().getAppNodeId());
        respAppHeader.getDestAddr().setDomainId(reqSgAppHeader.getSrcAddr().getDomainId());
        respAppHeader.getDestAddr().setAppNodeId(reqSgAppHeader.getSrcAddr().getAppNodeId());
        respAppHeader.setReqNo(reqSgAppHeader.getReqNo());
        respAppHeader.setReqName(reqSgAppHeader.getReqName());
        respAppHeader.setDataFormat(reqSgAppHeader.getDataFormat());

        return respAppHeader;
    }

}
