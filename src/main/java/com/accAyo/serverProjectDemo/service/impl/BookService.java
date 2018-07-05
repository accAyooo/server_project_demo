package com.accAyo.serverProjectDemo.service.impl;

import com.accAyo.serverProjectDemo.common.Constants;
import com.accAyo.serverProjectDemo.common.EnumBookFromType;
import com.accAyo.serverProjectDemo.common.EnumInfoMessage;
import com.accAyo.serverProjectDemo.common.EnumInspectStatus;
import com.accAyo.serverProjectDemo.framework.Exception.MainException;
import com.accAyo.serverProjectDemo.framework.hibernateDao.HibernateBaseService.BaseService;
import com.accAyo.serverProjectDemo.framework.hibernateDao.HibernateBaseService.CompareType;
import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.Book;
import com.accAyo.serverProjectDemo.service.IBookService;
import com.accAyo.serverProjectDemo.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/7/4
 */

@Service
public class BookService extends BaseService implements IBookService {

    @Override
    public Book addBookForSpider(int bookId, int authorId, String bookName, String imgUrl, String sort, String intro) throws MainException {
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
        book.setFromType(EnumBookFromType.SPIDER.getValue());
        if (this.getObject(Book.class, book.getId()) != null)
            return null;
        this.addBook(book);

        // todo 添加到搜索任务
        // todo 审核
        return book;
    }

    @Override
    public ResultFilter<Book> listBooks(int page, int pageSize) {
        HashMap<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("status", Constants.STATUS_NORMAL);
        return getObjects(Book.class, formExpressionsByProperty(propertyMap, CompareType.Equal), pageSize, page, true, "id");
    }

    private Book addBook(Book book) throws MainException {
        if (book.getId() == 0)
            throw new MainException(EnumInfoMessage.BOOK_ID_NOT_AVAILABLE.getDesc());
        if (book.getSort() == null)
            throw new MainException(EnumInfoMessage.BOOK_ADD_SORT_ERROR.getDesc());
        if (book.getName() == null)
            throw new MainException(EnumInfoMessage.BOOK_NAME_NOT_AVAILABLE.getDesc());
        if (book.getName().length() > Constants.BOOK_MAX_LENTH  || book.getName().length() < Constants.BOOK_MIN_LENTH)
            throw new MainException(EnumInfoMessage.BOOK_NAME_NOT_AVAILABLE.getDesc());
        if (book.getIntroduce() == null)
            throw new MainException(EnumInfoMessage.BOOK_INTRO_NOT_AVAILABLE.getDesc());
        if (this.getObject(Book.class, book.getId()) != null)
            throw new MainException(EnumInfoMessage.BOOK_HAS_EXISTS.getDesc());
        book.setCreateTime(new Date());
        this.addObject(book);
        return book;
    }

}
