package com.accAyo.serverProjectDemo.vo;

import com.accAyo.serverProjectDemo.common.EnumUserMark;
import com.accAyo.serverProjectDemo.common.EnumUserStatus;
import com.accAyo.serverProjectDemo.common.EnumUserType;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:25 2018/5/28
 */
public class UserVO implements Serializable {
    private int id;
    private String name;
    private int icon;
    private boolean isIconHidden;
    private boolean isWriter;
    private boolean isStaff;
    private boolean isSignWriter;
    private boolean isWebEditor;
    private boolean isVipUser;
    private int bookCount;
    private int reviewCount;
    private int fansCount;
    private byte status;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(int type) {
        isWriter = (type & EnumUserType.WRITER.getValue()) != 0;
        isStaff = (type & EnumUserType.STAFF.getValue()) != 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isIconHidden() {
        return isIconHidden;
    }

    public void setIconHidden(boolean iconHidden) {
        isIconHidden = iconHidden;
    }

    public boolean isWriter() {
        return isWriter;
    }

    public void setWriter(boolean writer) {
        isWriter = writer;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }

    public boolean isSignWriter() {
        return isSignWriter;
    }

    public void setSignWriter(boolean signWriter) {
        isSignWriter = signWriter;
    }

    public boolean isWebEditor() {
        return isWebEditor;
    }

    public void setWebEditor(boolean webEditor) {
        isWebEditor = webEditor;
    }

    public boolean isVipUser() {
        return isVipUser;
    }

    public void setVipUser(boolean vipUser) {
        isVipUser = vipUser;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public EnumUserStatus getEnumUserStatus() {
        return EnumUserStatus.getEnum(status);
    }

    public void setMark(int mark) {
        isSignWriter = (mark & EnumUserMark.SIGNED_WRITER.getValue()) != 0;
        isWebEditor = (mark & EnumUserMark.WEB_EDITOR.getValue()) != 0;
        isVipUser = (mark & EnumUserMark.VIP_USER.getValue()) != 0;
    }
}
