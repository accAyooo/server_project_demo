<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
            <h1 class="page-header">
                角色管理_${staff.name}
            </h1>
            <div class="">
                <form class="form-horizontal" action="/manage/staff/${staff.id}/role" method="post">
                    <div class="form-group">
                        <c:forEach items="${roleRF.items}" var="role">
                            <c:set value=",${role.id}," var="roleId"></c:set>
                            <label for="c${role.id}" class="checkboxes col-lg-3 col-sm-3">
                                <input type="checkbox" name="roles" id="c${role.id}" value="${role.id}"
                                       <c:if test="${fn:indexOf(roleStr, roleId) > -1}">checked</c:if> style="display: none">
                                <div style="height:55px;" title="${role.name}">
                                    <h5>${role.name}</h5>
                                </div>
                            </label>
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <div class=" col-sm-6">
                            <button class="btn btn-primary" type="submit">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>

</script>
</body>
</html>
