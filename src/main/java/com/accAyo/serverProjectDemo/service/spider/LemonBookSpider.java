package com.accAyo.serverProjectDemo.service.spider;

import com.accAyo.serverProjectDemo.pojo.SpiderBook;
import com.accAyo.serverProjectDemo.service.impl.SpiderService;
import com.accAyo.serverProjectDemo.util.StringUtil;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.annotation.Resource;
import java.util.List;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/7/4
 */
@Service
public class LemonBookSpider implements PageProcessor {

    private int THREAD_NUM = 10;
    private String SPIDER_PATH = "http://m.lemonyd.com/";

    private Site site = Site.me().setSleepTime(100).setRetryTimes(5).setTimeOut(5000);

    @Resource
    private SpiderService spiderService;

    @Override
    public void process(Page page) {
        if (page.getUrl().regex("http://m.lemonyd.com/$").match()) {
            List<String> urls = page.getHtml().links().regex(".*/book/\\d+").all();
            page.addTargetRequests(urls);
        }
        else if (page.getUrl().regex(".*/book/\\d+").match()) {
            String bookName = page.getHtml().$("h3", "text").get();
            String bookIntro = page.getHtml().$("div.intro p", "text").get();
            String authorName = page.getHtml().$("div.right p:first-of-type a", "text").get();
            String sort = page.getHtml().$("div.right p:nth-of-type(2)", "text").get().substring(3);
            String imgUrl = page.getHtml().$("div.left img", "src").get();
            String bookId = page.getUrl().regex(".*/book/(\\d+)").toString();
            int id = StringUtil.str2int(bookId);
            try {
                if (id > 0) {
                    SpiderBook spiderBook = spiderService.addSpiderBook(id, bookName, bookIntro, authorName, sort, imgUrl);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            String listUrl = page.getHtml().links().regex("/book/\\d+/chapter").get();
        } else if (page.getUrl().regex(".*/book/chapter/\\d+/\\d+.*").match()) {
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void execute() {
        Spider.create(this).thread(THREAD_NUM).addUrl(SPIDER_PATH).run();
    }
}
