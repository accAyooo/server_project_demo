<%--
  Created by IntelliJ IDEA.
  User: ayooo
  Date: 2018/6/24
  Time: 下午10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .header {
        position: relative;
    }
    .header .row-1, .header .row-2 {
        position: absolute;
        top: 25px;
    }
    .header .row-2 {
        right: 20px;
    }
    .header .row-1 {
        right: 150px;
        top: 30px;
    }
</style>
<header class="header" id="Header" style="overflow:hidden;background-color: #333">
    <div class="logo" style="">
        <h3 href="/manage/index" style="color: #ffffff; padding-top: 10px;padding-bottom: 10px;padding-left: 10px;">柠檬阅读后台</h3>
    </div>
    <div class="row-1" style="color: #fff;">
        ${LOGIN_USER.name}
    </div>
    <div class="row-2" style="color: #fff;margin-right: 30px;">
        <div class="accounts">
            <a class="logout btn btn-info">退出</a>
        </div>
    </div>
</header>
