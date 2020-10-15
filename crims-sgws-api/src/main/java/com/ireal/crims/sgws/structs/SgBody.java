package com.ireal.crims.sgws.structs;

import com.ireal.crims.sgws.enums.SgFrameCategory;
import com.ireal.crims.sgws.utils.EndianUtil;
import com.ireal.crims.sgws.utils.MD5Util;
import com.ireal.crims.sgws.utils.TypeConvert;
import com.ireal.crims.sgws.utils.UtilFun;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SgBody {
	
	private SgHeader header = null;
	private SgAppHeader appHeader = null;
	private int bodyLength = 0;
	private byte[] msgBody = null;
	
	public SgBody() {
	}
	
	public SgBody(SgHeader header, SgAppHeader appHeader, byte[] msgBody) {
		super();
		this.header = header;
		this.appHeader = appHeader;
		this.msgBody = msgBody;
	}
	///////消息头和消息体进行初始化
	public boolean Init()
	{
		if ( this.header == null )
		{
			return false;
		}
		
		if ( this.appHeader == null )
		{
			return false;
		}
		
		if ( this.msgBody == null )
		{
			return false;
		}

		this.bodyLength = this.msgBody.length;
		this.header.setVersion((short)1);
		this.header.setCategory((short) SgFrameCategory.SG_FRAME_APP.getFrameType());
		this.header.setTotalLength(SgHeader.MsgHeaderLen + SgAppHeader.MsgAppHeaderLen + 4 + this.msgBody.length);
		
		return true;
	}

	public SgHeader getHeader() {
		return header;
	}

	public void setHeader(SgHeader header) {
		this.header = header;
	}

	public SgAppHeader getAppHeader() {
		return appHeader;
	}

	public void setAppHeader(SgAppHeader appHeader) {
		this.appHeader = appHeader;
	}

	public byte[] getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(byte[] msgBody) {
		this.msgBody = msgBody;
	}

	//SgHeader，SgAppHeader 数据拼接塞到byte数组中

	public byte[] encode(){
//    	try {

		   	UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
			//ByteBuf buffer = allocator.buffer(SgHeader.MsgHeaderLen + SgAppHeader.MsgAppHeaderLen + this.msgBody.length);
			ByteBuf buffer = allocator.buffer(this.header.getTotalLength());

			int index = 0;
			// SgHeader
	    	buffer.setBytes(index, EndianUtil.int2LittleEndianBytes(this.header.getTotalLength()));
			index += 4;
			
	    	// int length = 0; //this.header.getSig().getBytes("GBK").length;
	    	byte[] byteSig = TypeConvert.StringToByteArray(this.header.getSig(), SgAppHeader.Protocol_CharsetName_GBK);
			buffer.setBytes(index, byteSig, 0, byteSig.length);
			index += 4;
			
	    	buffer.setBytes(index, EndianUtil.short2LittleEndianBytes(this.header.getVersion()));
	    	index += 2;
	    	
	    	buffer.setBytes(index, EndianUtil.short2LittleEndianBytes(this.header.getCategory()));
	    	index += 2;
	    	
	    	byte[] tmpByte = buffer.array();
	    	byte[] md5Code = new byte[4];
			MD5Util.MD5Generate(tmpByte, 1, md5Code);
	    	
	    	//buffer.setBytes(index, EndianUtil.int2LittleEndianBytes(this.header.getMd5Code()));
			buffer.setBytes(index, md5Code);
			index += 4;
			
			// SgAppHeader
	    	buffer.setByte(index, this.appHeader.getAppType());
	    	index += 1;
	    	
	    	byte[] btSequenceNo = EndianUtil.int2LittleEndianBytes(this.appHeader.getSequenceNo());
	    	buffer.setBytes(index, btSequenceNo);
	    	index += 4;
	    	
	    	buffer.setBytes(index, EndianUtil.short2LittleEndianBytes(this.appHeader.getTotalPacketCount()));
	    	index += 2;
	    	
	    	buffer.setBytes(index, EndianUtil.short2LittleEndianBytes(this.appHeader.getCurrentPacketNo()));
	    	index += 2;

			byte[] btSrcDomainId = TypeConvert.StringToByteArray(this.appHeader.getSrcAddr().getDomainId(), SgAppHeader.Protocol_CharsetName_UTF8);
			buffer.setBytes(index, btSrcDomainId, 0, btSrcDomainId.length);
			index += 32;

			byte[] btSrcAppNodeId = TypeConvert.StringToByteArray(this.appHeader.getSrcAddr().getAppNodeId(), SgAppHeader.Protocol_CharsetName_UTF8);
			buffer.setBytes(index, btSrcAppNodeId, 0, btSrcAppNodeId.length);
			index += 32;

			byte[] btDestDomainId = TypeConvert.StringToByteArray(this.appHeader.getDestAddr().getDomainId(), SgAppHeader.Protocol_CharsetName_UTF8);
			buffer.setBytes(index, btDestDomainId, 0, btDestDomainId.length);
			index += 32;

			byte[] btDestAppNodeId = TypeConvert.StringToByteArray(this.appHeader.getDestAddr().getAppNodeId(), SgAppHeader.Protocol_CharsetName_UTF8);
			buffer.setBytes(index, btDestAppNodeId, 0, btDestAppNodeId.length);
			index += 32;
			///////////////////////////////////////////////////////////////
			//length = this.appHeader.getReqName().getBytes(SgAppHeader.Protocol_CharsetName_GBK).length;
			byte[] byteReqName = TypeConvert.StringToByteArray(this.appHeader.getReqName(), SgAppHeader.Protocol_CharsetName_GBK);
	    	buffer.setBytes(index, byteReqName, 0, byteReqName.length);
	    	index += 32;
	    	
	    	buffer.setBytes(index, EndianUtil.int2LittleEndianBytes(this.appHeader.getReqNo()));
	    	index += 4;
	    	
	    	buffer.setBytes(index, EndianUtil.int2LittleEndianBytes(this.appHeader.getResult()));
	    	index += 4;
	    	
	    	buffer.setByte(index, this.appHeader.getDataFormat());
	    	index += 1;
	    	
	    	buffer.setBytes(index, EndianUtil.short2LittleEndianBytes(this.appHeader.getAdditionalDestCount()));
	    	index += 2;   	
	    	
	    	//length = this.appHeader.getReserved().getBytes(SgAppHeader.Protocol_CharsetName_Unicode).length;
			byte[] byteReserved = TypeConvert.StringToByteArray(this.appHeader.getReserved(), SgAppHeader.Protocol_CharsetName_GBK);
			buffer.setBytes(index, byteReserved, 0, Math.min(byteReserved.length, 128));
	    	
	    	index += 128;

			buffer.setBytes(index, EndianUtil.int2LittleEndianBytes(this.bodyLength));
			index += 4;

	    	buffer.setBytes(index, this.msgBody);
	    	
	    	return buffer.array();

/*		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}   */

//    	return null;
	}


	//消息头解析

	public static boolean decodeHeader(byte[] msg, SgHeader sgHeader, SgAppHeader appHeader)
	{
		int length = 0;
		if( null == msg )
		{
			return false;
		}
		
		length = msg.length;
		if( length < SgHeader.MsgHeaderLen + SgAppHeader.MsgAppHeaderLen )
		{
			return false;
		}

        int nPos = 0;
        // SgHeader
        int totalLength = ByteBuffer.wrap(msg, nPos, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();  
        sgHeader.setTotalLength(totalLength);
        nPos += 4;
        
        sgHeader.setSig(UtilFun.ByteArrayToString(msg, nPos, 4, "GBK"));
        nPos += 4;
        
        short version = ByteBuffer.wrap(msg, nPos, 2).order(ByteOrder.LITTLE_ENDIAN).getShort();
        sgHeader.setVersion(version);
        nPos += 2; 
        
        short category = ByteBuffer.wrap(msg, nPos, 2).order(ByteOrder.LITTLE_ENDIAN).getShort();
        sgHeader.setCategory(category);
        nPos += 2;
        
        int md5Code = ByteBuffer.wrap(msg, nPos, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();  
        sgHeader.setMd5Code(md5Code);
        nPos += 4;
        
        // SgAppHeader
        appHeader.setAppType(msg[nPos]);
        nPos += 1;

        int sequenceNo = ByteBuffer.wrap(msg, nPos, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();  
        appHeader.setSequenceNo(sequenceNo);//(int)TypeConvert.ByteArrayToLong(msg, nPos, 4));
        nPos += 4;
        
        short totalPkt = ByteBuffer.wrap(msg, nPos, 2).order(ByteOrder.LITTLE_ENDIAN).getShort();
        appHeader.setTotalPacketCount(totalPkt); //(short)TypeConvert.ByteArrayToLong(msg, nPos, 2));
        nPos += 2;
        
        short currPkt = ByteBuffer.wrap(msg, nPos, 2).order(ByteOrder.LITTLE_ENDIAN).getShort();
        appHeader.setCurrentPacketNo(currPkt); //(short)TypeConvert.ByteArrayToLong(msg, nPos, 2));
        nPos += 2;        
        
        appHeader.getSrcAddr().setDomainId(UtilFun.ByteArrayToString(msg, nPos, 32, SgAppHeader.Protocol_CharsetName_UTF8));
        nPos += 32;
        
        appHeader.getSrcAddr().setAppNodeId(UtilFun.ByteArrayToString(msg, nPos, 32, SgAppHeader.Protocol_CharsetName_UTF8));
        nPos += 32;    
  
        appHeader.getDestAddr().setDomainId(UtilFun.ByteArrayToString(msg, nPos, 32, SgAppHeader.Protocol_CharsetName_UTF8));
        nPos += 32;
        
        appHeader.getDestAddr().setAppNodeId(UtilFun.ByteArrayToString(msg, nPos, 32, SgAppHeader.Protocol_CharsetName_UTF8));
        nPos += 32;
        
        appHeader.setReqName(UtilFun.ByteArrayToString(msg, nPos, 32, SgAppHeader.Protocol_CharsetName_GBK));
        nPos += 32;
        
        int reqNo = ByteBuffer.wrap(msg, nPos, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
        appHeader.setReqNo(reqNo); //(int)TypeConvert.ByteArrayToLong(msg, nPos, 4));
        nPos += 4;
        
        int result = ByteBuffer.wrap(msg, nPos, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
        appHeader.setResult(result); //(int)TypeConvert.ByteArrayToLong(msg, nPos, 4));
        nPos += 4;
        
        appHeader.setDataFormat(msg[nPos]);
        nPos += 1;
     
        short additionalDestCount = ByteBuffer.wrap(msg, nPos, 2).order(ByteOrder.LITTLE_ENDIAN).getShort();  
        appHeader.setAdditionalDestCount(additionalDestCount);
        nPos += 2;
        
        appHeader.setReserved(UtilFun.ByteArrayToString(msg, nPos, 128, SgAppHeader.Protocol_CharsetName_GBK));
        nPos += 128;

		int bodyLength = ByteBuffer.wrap(msg, nPos, 4).order(ByteOrder.LITTLE_ENDIAN).getInt();
		nPos += 4;

		int nMsgLen = length - SgHeader.MsgHeaderLen - SgAppHeader.MsgAppHeaderLen - 4;
		if( nMsgLen != bodyLength )
		{
			return false;
		}

		return true;
	}

	//消息体解析

	public static String decodeBody(byte[] msg, final SgHeader sgHeader, final SgAppHeader appHeader)
	{
		int length = 0;
		if( null == msg )
		{
			return null;
		}
		
		length = msg.length;
		if( length != sgHeader.getTotalLength() )
		{
			return null;
		}

        int nPos = SgHeader.MsgHeaderLen + SgAppHeader.MsgAppHeaderLen + 4;
        int nLen = length - nPos;
        
        String msgBody = "";
        if( nLen > 0 )
        {
        	msgBody = UtilFun.ByteArrayToStringBody(msg, nPos, nLen, SgAppHeader.Protocol_CharsetName_Unicode);
        }

		return msgBody;
	}
	
}
