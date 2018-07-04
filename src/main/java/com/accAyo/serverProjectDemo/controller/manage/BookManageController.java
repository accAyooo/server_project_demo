package com.accAyo.serverProjectDemo.controller.manage;

import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.SpiderBook;
import com.accAyo.serverProjectDemo.service.impl.SpiderService;
import javafx.scene.input.MouseDragEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:
 *
 * @author shixiangyu
 * @date 2018/7/4
 */

@Controller
@RequestMapping(value = "/manage/book")
public class BookManageController {

    @Resource
    private SpiderService spiderService;

    @RequestMapping(value = "/spider/list")
    public String spiderBookListPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultFilter<SpiderBook> sbRF = spiderService.listNewSpiderBooks();
        model.addAttribute("sbRF", sbRF);
        return "/book/spider_book_list";
    }
}
