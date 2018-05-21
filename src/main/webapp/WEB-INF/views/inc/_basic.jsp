<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ayooo
  Date: 2018/5/17
  Time: 下午9:06
  To change this template use File | Settings | File Templates.
--%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, initial-scale=1.0, maximum-scale=1.0, user-scalable=1" />
<link rel="stylesheet" type="text/css" href="${CSS_RESOURCE}/base.css" />

<script >
    var _inlineCodes = [];
    var _inlineRun = function(fn){
        _inlineCodes.push(fn);
    };
    _inlineRun(function(){
        require(["kernel"], function(core){
            core.isAuth();
        })
    })
</script>



