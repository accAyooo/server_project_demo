package com.accAyo.serverProjectDemo.service;

import com.accAyo.serverProjectDemo.pojo.Book;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/7/4
 */
public interface IBookService {

    public Book addBookForSpider(int bookId, int authorId, String bookName, String imgUrl, int words, String sort, String intro);
}
