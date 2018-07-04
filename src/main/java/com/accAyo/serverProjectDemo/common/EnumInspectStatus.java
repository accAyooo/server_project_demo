package com.accAyo.serverProjectDemo.common;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:17 2018/7/4
 */
public enum EnumInspectStatus {
    REJECT((byte)-2, "驳回"),
    DELETE((byte)-1, "删除"),
    NORMAL((byte)0, "草稿"),
    WAIT((byte)1, "待审核"),
    PASS((byte)2, "已通过"),
    ;

    private byte value;
    private String desc;

    EnumInspectStatus(byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public byte getValue() {
        return this.value;
    }

    public String getDesc() {
        return desc;
    }

    public static EnumInspectStatus getEnum(byte type) {
        EnumInspectStatus[] status = EnumInspectStatus.values();
        for (int i = 0; i < status.length; i ++) {
            if (status[i].getValue() == type) {
               return status[i];
            }
        }
        return null;
    }
}
