package com.accAyo.serverProjectDemo.common;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:04 2018/6/25
 */
public enum EnumStaffType {

    TEACHER((byte)1, "责编", 2),
    TEACHER_MASTER((byte)2, "责编主管", 7),
    OTHER((byte)3, "其他", 0),
    SAIL_MASTER((byte)5, "销售主管", 7),
    PUBLISH((byte)6, "出版", 7),
    MASTER((byte)7, "总管", 8),
    FINANCIAL((byte)8, "财务", 0),
    COPYRIGHT((byte)9, "版权", 0),
    INSPECT((byte)10, "审核", 0),
    APPM((byte)11, "APP编辑", 0),

    PARTNER((byte)12, "合作商", 0),
    PARTNER_CONTENT((byte)13, "内容合作商", 12),
    PARTNER_USER((byte)14, "用户合作商", 12),
    ;

    private String desc;
    private byte value;
    private int parent;

    EnumStaffType(byte value, String desc, int parent) {
        this.value = value;
        this.desc = desc;
        this.parent = parent;
    }

    public String getDesc() {
        return desc;
    }

    public byte getValue() {
        return value;
    }

    public int getParent() {
        return parent;
    }

    public static EnumStaffType getEnum(int type) {
        EnumStaffType[] status = EnumStaffType.values();
        for (int i = 0; i < status.length; i ++) {
            if (status[i].getValue() == type) {
                return status[i];
            }
        }
        return null;
    }
}
