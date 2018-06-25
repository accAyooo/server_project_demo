package com.accAyo.serverProjectDemo.common;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:32 2018/6/25
 */
public enum EnumUserStatus {

    REGISTED((byte)1, "仅注册用户"),
    ACTIVED((byte)2, "激活用户"),
    DELETED((byte)-1, "封禁用户"),
    LOGOFF((byte)-2, "已注销用户"),
    ;

    private byte value;
    private String desc;

    EnumUserStatus(byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public byte getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static EnumUserStatus getEnum(int type) {
        EnumUserStatus[] statuses = EnumUserStatus.values();
        for (int i = 0; i < statuses.length; i ++) {
            if (statuses[i].getValue() == type) {
                return statuses[i];
            }
        }
        return null;
    }
}
