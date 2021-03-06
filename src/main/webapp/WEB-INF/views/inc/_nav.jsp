<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: ayooo
  Date: 2018/6/25
  Time: 下午8:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .nav-slidebar > li.active > a {
        color: #fff;
        background-color: #428bca;
    }
    .nav-slidebar {
        margin-right: -21px;
        margin-bottom: 20px;
        margin-left: -20px;
    }
    .slidebar {
        position: fixed;
        left: 0;
        top: 76px;
        z-index: 1000;
        bottom: 0;
        overflow-x: hidden;
        overflow-y: auto;
        background-color: #f5f5f5;
        border-right: 1px solid #eee;
    }
</style>

<%
    List navItems = new ArrayList();

    Map userItems = new LinkedHashMap();
    userItems.put("user_list", new String[]{"员工列表", "/staff/list"});
    userItems.put("role_list", new String[]{"角色列表", "/role/list"});
    userItems.put("access_list", new String[]{"权限列表", "/role/access/list"});
    navItems.add(userItems);

    Map bookItems = new LinkedHashMap();
    bookItems.put("spider_book_all", new String[]{"小说列表（爬虫）", "/book/spider/list"});
    bookItems.put("book_add", new String[]{"添加小说", "/book/add"});
    navItems.add(bookItems);

    request.setAttribute("navItems", navItems);
%>

<div class="col-sm-3 col-md-2 slidebar" style="padding-top: 20px">
    <c:forEach items="${navItems}" var="ul" >
        <ul class="nav-slidebar nav">
            <c:forEach items="${ul}" var="item">
                <li <c:if test="${navName == item.key}">class="active"</c:if>>
                    <a href="/manage${item.value[1]}">${item.value[0]}</a>
                </li>
            </c:forEach>
        </ul>
    </c:forEach>
</div>
