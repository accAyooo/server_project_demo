package com.accAyo.serverProjectDemo.service;

import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.SpiderBook;
import com.accAyo.serverProjectDemo.service.impl.SpiderService;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/7/3
 */

public interface ISpiderService {
    /**
     * 添加spiderbook
     * @param bookId
     * @param bookName
     * @param bookIntro
     * @param authorName
     * @param sort
     * @param imgUrl
     * @return
     */
    public SpiderBook addSpiderBook(int bookId, String bookName, String bookIntro, String authorName, String sort, String imgUrl);

    public ResultFilter<SpiderBook> listNewSpiderBooks();
}
