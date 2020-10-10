package com.ireal.crims.common.enums;

public enum MsgCmdEnum {
    AlarmNotify(0x03040201, "告警通知"),
    AlarmProcess(0x03040202, "告警处理"),
    AlarmSubscribe(0x03040203, "告警订阅"),
    NivmRoleInfoSync(0x03040101, "角色信息同步"),
    NivmUserInfoSync(0x03040102, "用户信息同步"),
    NivmUserRightSync(0x03040103, "用户权限同步"),
    NivmDeviceInfoSync(0x03040104, "设备信息同步"),
    NivmOperateLogSync(0x03040105, "操作日志信息同步");

    // 普通方法


    public static String getEnumName(int cmd) {
        for (MsgCmdEnum type : MsgCmdEnum.values()) {
            if (type.getCmd() == cmd ) {
                return type.getCmdname();
            }
        }
        return null;
    }

    public static MsgCmdEnum getEnumType(int cmd)
    {
        for (MsgCmdEnum type : MsgCmdEnum.values()) {
            if (type.getCmd() == cmd) {
                return type;
            }
        }
        return null;
    }

    private int cmd;
    private String cmdname;

    private MsgCmdEnum(int cmd, String cmdname)
    {
        this.cmd = cmd;
        this.cmdname = cmdname;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public String getCmdname() {
        return cmdname;
    }

    public void setCmdname(String cmdname) {
        this.cmdname = cmdname;
    }
}
