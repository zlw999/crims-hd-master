package com.ireal.crims.sgws.structs;

public class SgAddr {
	String domainId  = ""; 	// 32字节, //域标识
	String appNodeId = ""; 	// 32字节, //应用节点标识

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getAppNodeId() {
		return appNodeId;
	}

	public void setAppNodeId(String appNodeId) {
		this.appNodeId = appNodeId;
	}
}
