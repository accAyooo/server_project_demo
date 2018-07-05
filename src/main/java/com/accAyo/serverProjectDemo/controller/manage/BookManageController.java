package com.accAyo.serverProjectDemo.controller.manage;

import com.accAyo.serverProjectDemo.framework.util.ResultFilter;
import com.accAyo.serverProjectDemo.pojo.Book;
import com.accAyo.serverProjectDemo.pojo.SpiderBook;
import com.accAyo.serverProjectDemo.service.impl.BookService;
import com.accAyo.serverProjectDemo.service.impl.SpiderService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import javafx.scene.input.MouseDragEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Resource
    private BookService bookService;

    @RequestMapping(value = "/spider/list")
    public String spiderBookListPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultFilter<SpiderBook> sbRF = spiderService.listNewSpiderBooks();
        model.addAttribute("sbRF", sbRF);
        return "/book/spider_book_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBookPage(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "/book/book_add";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String bookListPage(@RequestParam(defaultValue = "1") int page,
                               HttpServletRequest request, HttpServletResponse response, Model model) {
        ResultFilter<Book> bookRF = bookService.listBooks(page, 20);
        model.addAttribute("bookRF", bookRF);
        return "/book/list";
    }
}
