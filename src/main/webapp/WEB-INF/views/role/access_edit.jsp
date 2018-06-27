<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ayooo
  Date: 2018/6/24
  Time: 下午7:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="navName" value="" />
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
            <h1 class="page-header">添加权限</h1>
            <div class="">
                <form action="/manage/role/access/${access.id}/edit" class="form-horizontal" method="post">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">权限名称:</label>
                        <div class="col-lg-6 col-md-8 col-sm-8">
                            <input type="text" class="form-control" placeholder="输入权限名称" value="${access.name}" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">路径:</label>
                        <div class="col-lg-6 col-md-8 col-sm-8">
                            <input type="text" class="form-control" placeholder="输入路径" name="path" value="${access.path}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-6">
                            <button class="btn btn-primary" type="submit">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    $("body").click(function() {
        console.log(1)
    })
</script>
</body>
</html>
