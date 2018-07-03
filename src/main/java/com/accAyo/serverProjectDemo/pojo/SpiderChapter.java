package com.accAyo.serverProjectDemo.pojo;

import javax.persistence.*;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/7/3
 */

@Entity
@Table(name = "manage_spider_chapter")
public class SpiderChapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int name;
    private int bookId;
    private int chapterId;
}
