<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ayooo
  Date: 2018/6/24
  Time: 下午7:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="navName" value="user_list" />
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
                员工列表
                <a href="/manage/staff/add" class="btn btn-success btn-xs">添加</a>
            </h1>
            <div class="">
                <table class="table table-bordered" style="font-size: 14px;">
                    <tbody>
                    <tr>
                        <th>id</th>
                        <th>邮箱</th>
                        <th>姓名</th>
                        <th>状态</th>
                        <th>员工类型</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${staffRF.items}" var="item">
                        <td>${item.id}</td>
                        <td>${item.user.email}</td>
                        <td>${item.name}</td>
                        <td><c:if test="${item.status == 0}">工作人员</c:if><c:if test="${item.status == -1}">非工作人员</c:if></td>
                        <td>${item.enumStaffType.desc}</td>
                        <td>
                            <a href="/manage/staff/${item.id}/edit">修改</a>
                            <c:if test="${item.status == 0}"><a href="/manage/staff/${item.id}/del">关闭</a></c:if>
                            <c:if test="${item.status == -1}"><a href="/manage/staff/${item.id}/add">开启</a></c:if>
                        </td>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script>

</script>
</body>
</html>
