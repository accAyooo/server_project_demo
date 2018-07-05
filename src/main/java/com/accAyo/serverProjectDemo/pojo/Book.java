package com.accAyo.serverProjectDemo.pojo;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Date;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/7/4
 */

@Entity
@Table(name = "writing_book")
public class Book {

    @Id
    private int id;

    private int authorId;
    private String name;
    private String imgUrl;

    private String introduce;
    private boolean open;
    private int words;
    private byte status;
    private boolean free;
    private byte fromType;

    @Column(name = "last_chapter_id")
    private int lastChapterId;

    @Column(name = "first_chapter_id")
    private int fristChapterId;

    private int price;

    @Column(name = "inspect_need")
    private boolean inspectNeed;

    @Column(name = "inspect_status")
    private byte inspectStatus;

    private String sort;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_chapter_time")
    private Date lastChapterTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "finish_time")
    private Date finishTime;

    @Transient
    private int fansCount;
    @Transient
    private int reviewCount;
    @Transient
    private int readPV;
    @Transient
    private int visitCounts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getWords() {
        return words;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getLastChapterId() {
        return lastChapterId;
    }

    public void setLastChapterId(int lastChapterId) {
        this.lastChapterId = lastChapterId;
    }

    public int getFristChapterId() {
        return fristChapterId;
    }

    public void setFristChapterId(int fristChapterId) {
        this.fristChapterId = fristChapterId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isInspectNeed() {
        return inspectNeed;
    }

    public void setInspectNeed(boolean inspectNeed) {
        this.inspectNeed = inspectNeed;
    }

    public byte getInspectStatus() {
        return inspectStatus;
    }

    public void setInspectStatus(byte inspectStatus) {
        this.inspectStatus = inspectStatus;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastChapterTime() {
        return lastChapterTime;
    }

    public void setLastChapterTime(Date lastChapterTime) {
        this.lastChapterTime = lastChapterTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getReadPV() {
        return readPV;
    }

    public void setReadPV(int readPV) {
        this.readPV = readPV;
    }

    public int getVisitCounts() {
        return visitCounts;
    }

    public void setVisitCounts(int visitCounts) {
        this.visitCounts = visitCounts;
    }

    public byte getFromType() {
        return fromType;
    }

    public void setFromType(byte fromType) {
        this.fromType = fromType;
    }
}
