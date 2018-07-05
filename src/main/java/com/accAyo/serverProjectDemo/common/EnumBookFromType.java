package com.accAyo.serverProjectDemo.common;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/7/5
 */
public enum EnumBookFromType {
    LOCAL((byte)1, "本站"),
    SPIDER((byte)2, "爬虫"),
    ;

    private byte value;
    private String desc;

    EnumBookFromType(byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public byte getValue() {
        return value;
    }

    public static EnumBookFromType getEnum(byte val) {
        EnumBookFromType[] types = EnumBookFromType.values();
        for (int i = 0; i < types.length; i ++) {
            if (val == types[i].getValue()) {
                return types[i];
            }
        }
        return null;
    }
}
