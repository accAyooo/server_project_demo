<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ayooo
  Date: 2018/6/24
  Time: 下午7:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="navName" value="role_list" />
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
                角色列表
                <a href="/manage/role/add" class="btn btn-success btn-xs">添加</a>
            </h1>
            <div class="">
                <table class="table table-bordered" style="font-size: 14px;">
                    <tbody>
                    <tr>
                        <th>id</th>
                        <th>角色名称</th>
                        <th>操作</th>
                    </tr>
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
