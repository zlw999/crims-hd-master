package com.ireal.crims.sgws.structs;

public class SgHeader {
	
	public static final int MsgHeaderLen = 16;
	
	private int totalLength = 0 ; 		///< 4 qint32 总长度，包含头
	private String sig		= "SMSG"; 	///< 4 char[4]固定字符串"SMSG"
	private short version	= 1; 		///< 2 qint16 协议版本号，二字节，高位主版本号，低位次版本号。当前为1.0。
	private short category	= 1;		///< 2 qint16 分类，1.应用消息，11.应用节点标识，12.获取活动节点，13.活动节点的应答，14.心跳，15.时间同步，16.时间同步应答
	private int md5Code		= 0; 		///< 4 qint32 md5码，上述内容（从totalLength到category）的MD5值

	public int getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(int totalLength) {
		this.totalLength = totalLength;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public short getVersion() {
		return version;
	}

	public void setVersion(short version) {
		this.version = version;
	}

	public short getCategory() {
		return category;
	}

	public void setCategory(short category) {
		this.category = category;
	}

	public int getMd5Code() {
		return md5Code;
	}

	public void setMd5Code(int md5Code) {
		this.md5Code = md5Code;
	}
	
}
