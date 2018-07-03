package com.accAyo.serverProjectDemo.pojo;

import javax.persistence.*;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/7/3
 */
@Entity
@Table(name = "manage_spider_book")
public class SpiderBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String intro;
    private String author;
    private String sort;
}
