package com.accAyo.serverProjectDemo.controller.manage;

import com.accAyo.serverProjectDemo.service.spider.LemonBookSpider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/7/4
 */

@Controller
@RequestMapping(value = "/spider")
public class SpiderManageController {

    @Resource LemonBookSpider lemonBookSpider;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void spiderTest() {
        lemonBookSpider.execute();
    }
}
