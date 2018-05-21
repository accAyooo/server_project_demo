package com.accAyo.serverProjectDemo.common;

import com.sun.tools.internal.jxc.ap.Const;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:52 2018/5/20
 */
public enum  EnumBookGroupType {
    BOOK_BOY((byte) 1, "小说_男频", Constants.IMAGE_SERVER_DOMAIN, Constants.JS_SERVER_DOMAIN),
    BOOK_GIRL((byte) 2, "小说_女频", Constants.IMAGE_SERVER_DOMAIN, Constants.JS_SERVER_DOMAIN);

    private String desc;
    private byte value;
    private String imgDomain;
    private String jsDomain;

    private EnumBookGroupType(byte value, String desc, String imgDomain, String jsDomain) {
        this.value = value;
        this.desc = desc;
        this.imgDomain = imgDomain;
        this.jsDomain = jsDomain;
    }
}
