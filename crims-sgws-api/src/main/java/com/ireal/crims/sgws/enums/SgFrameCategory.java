package com.ireal.crims.sgws.enums;

public enum SgFrameCategory {
	SG_FRAME_INIT_ACK(12, "初始应答"),
	SG_FRAME_ACTIVE_NODE_CHANGED(16, "活动节点发生变化"),
	SG_FRAME_APP(1, "应用报文");	
	
	// 普通方法
    public static SgFrameCategory getAppType(int dataType) {
        for (SgFrameCategory type : SgFrameCategory.values())
        {
	        if ( type.getFrameType() == dataType )
	        {
	            return type;
	        }
        }
        return null;
    }
    
     // 覆盖方法
    @Override
    public String toString() {
        return this.frameType + "_" + this.frameTypeName;
    }
	
	private int frameType;
	private String frameTypeName;

	private SgFrameCategory(int frameType, String frameTypeName)
	{
		this.frameType = frameType;
		this.frameTypeName = frameTypeName;
	}

	public int getFrameType() {
		return frameType;
	}

	public void setFrameType(int frameType) {
		this.frameType = frameType;
	}

	public String getFrameTypeName() {
		return frameTypeName;
	}

	public void setFrameTypeName(String frameTypeName) {
		this.frameTypeName = frameTypeName;
	}


}
