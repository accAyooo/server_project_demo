package com.accAyo.serverProjectDemo.service.impl;

import com.accAyo.serverProjectDemo.framework.hibernateDao.HibernateBaseService.BaseService;
import com.accAyo.serverProjectDemo.framework.hibernateDao.HibernateBaseService.CompareType;
import com.accAyo.serverProjectDemo.framework.hibernateDao.HibernateBaseService.HibernateExpression;
import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.SpiderBook;
import com.accAyo.serverProjectDemo.service.ISpiderService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/7/3
 */

@Service
public class SpiderService extends BaseService implements ISpiderService {

    public SpiderBook addSpiderBook(int bookId, String bookName, String bookIntro, String authorName, String sort, String imgUrl) {
        SpiderBook spiderBook = new SpiderBook();
        spiderBook.setName(bookName);
        spiderBook.setImgUrl(imgUrl);
        spiderBook.setSort(sort);
        spiderBook.setAuthor(authorName);
        spiderBook.setIntro(bookIntro);
        spiderBook.setBookId(bookId);
        // todo 查询本地book表中是否存在书的ID  去重
        HashMap<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("bookId", spiderBook.getBookId());
        ResultFilter<SpiderBook> sbRF = this.getObjectsByProperty(SpiderBook.class, propertyMap, CompareType.Equal, 1, 1, false, "id");
        if (sbRF.getTotalCount() > 0)
            return null;
        try {
            this.addObject(spiderBook);
        } catch (Exception e) {
            return null;
        }
        return spiderBook;
    }

    @Override
    public ResultFilter<SpiderBook> listNewSpiderBooks() {
        // todo 去重本地已有的书
        HashMap<String, Object> propertyMap = new HashMap<>();
        Collection<HibernateExpression> expressions = formExpressionsByProperty(propertyMap, CompareType.Equal);
        return this.getObjects(SpiderBook.class, expressions, Integer.MAX_VALUE, 1, true, "id");
    }
}
