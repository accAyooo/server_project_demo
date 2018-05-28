package com.accAyo.serverProjectDemo.common;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:51 2018/5/28
 */
public enum  EnumUserType {
    WRITER(1, "作家"),
    STAFF(2, "工作人员"),
    PARTTIME(4, "兼职人员");

    private int value;
    private String desc;

    EnumUserType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public int getValue() {
        return value;
    }
}
