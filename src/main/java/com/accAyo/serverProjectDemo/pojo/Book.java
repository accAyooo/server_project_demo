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

}
