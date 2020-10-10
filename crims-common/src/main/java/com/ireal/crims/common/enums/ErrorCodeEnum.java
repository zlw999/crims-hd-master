package com.ireal.crims.common.enums;

public enum ErrorCodeEnum {
    SUCCESS("01", "成功"),
    FAILURE("02", "失败"),
    OTHER("03", "未知");

    // 普通方法
    public static String getEnumName(String code) {
        for (ErrorCodeEnum type : ErrorCodeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type.getCodename();
            }
        }
        return null;
    }

    public static ErrorCodeEnum getEnumType(String code)
    {
        for (ErrorCodeEnum type : ErrorCodeEnum.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    private String code;
    private String codename;

    private ErrorCodeEnum(String code, String codename)
    {
        this.code = code;
        this.codename = codename;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }
}
