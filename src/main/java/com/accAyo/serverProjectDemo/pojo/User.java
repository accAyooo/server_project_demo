package com.accAyo.serverProjectDemo.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:47 2018/5/8
 */
@Entity
@Table(name = "accounts_user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64)
    private String email;
    @Column(length = 64)
    private String password;
    @Column(length = 64)
    private String name;
    @Column(length = 140)
    private String signatrue;
    @Lob
    private String intro;

    /**
     * 用户上传次数 1.jpg 2.jpg
     */
    private int icon;
    @Column(name = "icon_hidden")
    private boolean iconHidden;
    @Column(name = "city_id")
    private int cityId;
    private int type;
    private int mark;
    private byte status;
    @Column(length=128)
    private String random;
    @Column(length=64)
    private String verify;

    /**
     * enumBookGroupType
     */
    @Column(name = "_group")
    private byte group;

    /**
     * enumSiteType
     */
    private byte site;

    /**
     * enumThirdpartType
     */
    @Column(name = "from_type")
    private byte fromType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    // 激活邮箱的时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "active_time")
    private Date activeTime;

    // 最后一次修改邮箱的时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_active_time")
    private Date lastActiveTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignatrue() {
        return signatrue;
    }

    public void setSignatrue(String signatrue) {
        this.signatrue = signatrue;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isIconHidden() {
        return iconHidden;
    }

    public void setIconHidden(boolean iconHidden) {
        this.iconHidden = iconHidden;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public byte getGroup() {
        return group;
    }

    public void setGroup(byte group) {
        this.group = group;
    }

    public byte getSite() {
        return site;
    }

    public void setSite(byte site) {
        this.site = site;
    }

    public byte getFromType() {
        return fromType;
    }

    public void setFromType(byte fromType) {
        this.fromType = fromType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(Date lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }
}
