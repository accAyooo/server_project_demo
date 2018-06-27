<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ayooo
  Date: 2018/6/24
  Time: 下午7:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="navName" value="access_list" />
<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/views/inc/_import.jsp"%>
</head>
<body>
<%@include file="/WEB-INF/views/inc/_header.jsp"%>

<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/views/inc/_nav.jsp"%>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">
                权限列表
                <a href="/manage/role/access/add" class="btn btn-success btn-xs">添加</a>
            </h1>
            <div class="">
                <table class="table table-bordered" style="font-size: 14px;">
                    <tbody>
                    <tr>
                        <th>id</th>
                        <th>名称</th>
                        <th>路径</th>
                        <th>操作</th>
                    </tr>
                    </tbody>
                    <c:forEach items="${rf.items}" var="item">
                        <tr>
                            <td>
                                ${item.id}
                            </td>
                            <td>
                                ${item.name}
                            </td>
                            <td>${item.path}</td>
                            <td>
                                <a href="/manage/role/access/${item.id}/edit">编辑</a>
                                <a href="/manage/role/access/${item.id}/del">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

<script>

</script>
</body>
</html>
