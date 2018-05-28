package com.accAyo.serverProjectDemo.common;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:02 2018/5/28
 */
public enum EnumUserMark {

    SIGNED_WRITER(1, "签约作家"),
    SPECIAL_CRITIC(2, "特约评论人"),
    WEB_EDITOR(4, "网络编辑"),
    VIP_USER(8, "vip会员"),
    ;

    private String desc;
    private int value;

    private EnumUserMark(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    public static EnumUserMark getEnum(int type) {
        EnumUserMark[] status = EnumUserMark.values();
        for(int i = 0; i < status.length; i++) {
            if(status[i].getValue() == type) {
                return status[i];
            }
        }

        return null;
    }

}
