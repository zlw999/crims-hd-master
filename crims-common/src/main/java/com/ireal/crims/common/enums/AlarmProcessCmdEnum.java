package com.ireal.crims.common.enums;

public enum AlarmProcessCmdEnum {
    Affirm("01", "确认"),
    Clear("02", "清除"),
    AffirmALL("03", "确认全部"),
    ClearALL("04", "清除全部");

    // 普通方法
    public static String getEnumName(String cmd) {
        for (AlarmProcessCmdEnum type : AlarmProcessCmdEnum.values()) {
            if (type.getCmd().equals(cmd)) {
                return type.getCmdname();
            }
        }
        return null;
    }

    public static AlarmProcessCmdEnum getEnumType(String cmd)
    {
        for (AlarmProcessCmdEnum type : AlarmProcessCmdEnum.values()) {
            if (type.getCmd().equals(cmd)) {
                return type;
            }
        }
        return null;
    }

    private String cmd;
    private String cmdname;

    private AlarmProcessCmdEnum(String cmd, String cmdname)
    {
        this.cmd = cmd;
        this.cmdname = cmdname;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getCmdname() {
        return cmdname;
    }

    public void setCmdname(String cmdname) {
        this.cmdname = cmdname;
    }
}
