package com.ireal.crims.sgws.enums;

public enum SgAppType {	
	
	APP_REQUEST(1, "请求"),
	APP_EVENT(2, "事件"),
	APP_ACK(3, "请求对应的应答"),
	APP_UNDO(0x80, "退回");
	
	// 普通方法
    public static SgAppType getAppType(int typeid) {
        for (SgAppType type : SgAppType.values())
        {
	        if ( type.getType() == typeid )
	        {
	            return type;
	        }
        }
        return null;
    }
    
     // 覆盖方法
    @Override
    public String toString() {
        return this.type + "_" + this.typeName;
    }
	
	private int type;
	private String typeName;

	private SgAppType(int type, String typeName)
	{
		this.type = type;
		this.typeName = typeName;
	}

	public  int getType() {
		return type;
	}

	public String getTypeName() {
		return typeName;
	}
	
}
