package com.accAyo.serverProjectDemo.service;

import com.accAyo.serverProjectDemo.framework.Exception.MainException;
import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.Book;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/7/4
 */
public interface IBookService {

    public Book addBookForSpider(int bookId, int authorId, String bookName, String imgUrl, String sort, String intro) throws MainException;

    /**
     * 获取全部书
     * @return
     */
    ResultFilter<Book> listBooks(int page, int pageSize);
}
