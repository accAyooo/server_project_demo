package com.accAyo.serverProjectDemo.service.impl;

import com.accAyo.serverProjectDemo.common.EnumInspectStatus;
import com.accAyo.serverProjectDemo.framework.hibernateDao.HibernateBaseService.BaseService;
import com.accAyo.serverProjectDemo.pojo.Book;
import com.accAyo.serverProjectDemo.service.IBookService;

import java.util.Date;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/7/4
 */
public class BookService extends BaseService implements IBookService {

    @Override
    public Book addBookForSpider(int bookId, int authorId, String bookName, String imgUrl, String sort, String intro) {
        Book book = new Book();
        book.setAuthorId(authorId);
        book.setId(bookId);
        book.setName(bookName);
        book.setImgUrl(imgUrl);
        book.setSort(sort);
        book.setIntroduce(intro);
        book.setInspectNeed(true);
        book.setInspectStatus(EnumInspectStatus.NORMAL.getValue());
        book.setFree(true);
        book.setCreateTime(new Date());
        book.setOpen(true);
        if (this.getObject(Book.class, book.getId()) != null)
            return null;
        try {
            this.addObject(book);
        } catch (Exception e) {
            return null;
        }

        // todo 添加到搜索任务
        // todo 审核
        return book;
    }
}
