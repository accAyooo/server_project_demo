<%--
  Created by IntelliJ IDEA.
  User: ayooo
  Date: 2018/5/17
  Time: 下午9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${JS_RESOURCE}/components/jquery/dist/jquery.js"></script>
<script>
    var require = {
        baseUrl: "${JS_RESOURCE}",
        waitSeconds: 15,
        deps : ["/js/"]
    }
</script>
<script src="${JS_RESOURCE}/components/requirejs/require.js"></script>
