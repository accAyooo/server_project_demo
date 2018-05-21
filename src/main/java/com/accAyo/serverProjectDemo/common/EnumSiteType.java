package com.accAyo.serverProjectDemo.common;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:06 2018/5/20
 */
public enum EnumSiteType {
    ALL((byte) 0, "全部"),
    WEB((byte) 1, "web站"),
    WAP((byte) 2, "手机站"),
    ANDROID((byte) 3, "安卓"),
    IOS((byte) 4, "苹果");

    private byte value;
    private String desc;

    private EnumSiteType(byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public byte getValue() {
        return value;
    }

    public static EnumSiteType[] getNormalSiteType() {
        return new EnumSiteType[] {EnumSiteType.WEB, EnumSiteType.WAP, EnumSiteType.ANDROID, EnumSiteType.IOS};
    }

    public static EnumSiteType getEnum(byte type) {
        EnumSiteType[] status = EnumSiteType.values();

        for (int i = 0; i < status.length; i ++) {
            if (status[i].getValue() == type) {
                return status[i];
            }
        }

        return null;
    }

    public static  EnumSiteType getEnum(int type) {
        EnumSiteType[] status = EnumSiteType.values();
        for(int i = 0; i < status.length; i++) {
            if(status[i].getValue() == type) {
                return status[i];
            }
        }

        return null;
    }
}


