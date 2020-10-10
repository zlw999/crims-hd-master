package com.ireal.crims.sgws.enums;

public enum SgDataFormat {
	
/*	DATA_BINARY : 1, //二进制
	DATA_XML : 2, //xml
	DATA_JSON : 3, //json
	DATA_XML_ANSI : 4, //xml(ansi)
	DATA_JSON_ANSI : 5, //json(ansi)
*/	
	DATA_BINARY(1, "二进制"),
	DATA_XML(2, "xml"),
	DATA_JSON(3, "json"),
	DATA_XML_ANSI(4, "xml(ansi)"),
	DATA_JSON_ANSI(5, "json(ansi)");	
	
	// 普通方法
    public static SgDataFormat getAppType(int dataType) {
        for (SgDataFormat type : SgDataFormat.values())
        {
	        if ( type.getDataType() == dataType )
	        {
	            return type;
	        }
        }
        return null;
    }
    
     // 覆盖方法
    @Override
    public String toString() {
        return this.dataType + "_" + this.dataTypeName;
    }
	
	private int dataType;
	private String dataTypeName;

	private SgDataFormat(int dataType, String dataTypeName)
	{
		this.dataType = dataType;
		this.dataTypeName = dataTypeName;
	}

	public int getDataType() {
		return dataType;
	}

	public String getDataTypeName() {
		return dataTypeName;
	}

}
