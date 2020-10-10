package com.ireal.crims.common.enums;

public enum NetConnectStateEnum {
    Offline(0, "离线"),
    Online(1, "在线"),
    Unknown(-1, "未知");

    // 普通方法
    public static String getEnumName(int state) {
        for (NetConnectStateEnum type : NetConnectStateEnum.values()) {
            if (type.getState() == state ) {
                return type.getStatename();
            }
        }
        return null;
    }

    public static NetConnectStateEnum getEnumType(int state)
    {
        for (NetConnectStateEnum type : NetConnectStateEnum.values()) {
            if (type.getState() == state) {
                return type;
            }
        }
        return null;
    }

    private int state;
    private String statename;

    private NetConnectStateEnum(int state, String statename)
    {
        this.state = state;
        this.statename = statename;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }
}
