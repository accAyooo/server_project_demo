package com.accAyo.serverProjectDemo.spider;

import com.accAyo.serverProjectDemo.util.StringUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午10:41 2018/7/3
 */
public class HeiyanHomeSpiderProcess implements PageProcessor {

    private Site site = Site.me().setRetryTimes(10).setSleepTime(1).setTimeOut(3000);

    @Override
    public void process(Page page) {
        System.out.println(page.getUrl().regex("http://m.lemonyd.com/$").match());

        if (page.getUrl().regex("http://m.lemonyd.com/$").match()) {
            List<String> urls = page.getHtml().links().regex(".*/book/\\d+").all();
            page.addTargetRequests(urls);
        }
        else if (page.getUrl().regex(".*/book/\\d+").match()) {
            System.out.println(page.getHtml().$("h3"));
        } else if (page.getUrl().regex(".*/book/chapter/\\d+/\\d+.*").match()) {

        }
        System.out.println("抓取结束");
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new HeiyanHomeSpiderProcess())
                .addUrl("http://m.lemonyd.com/")
                .run();
    }
}
