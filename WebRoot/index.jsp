<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>网站首页</title>
    <script type="text/javascript">
    	function doLogout(){
    		window.location.href = "${pageContext.request.contextPath}/servlet/LogoutServlet";
    	}
    </script>
  </head>
  
  <body>
    <h1>我的网站</h1>
    <br/>
    <c:if test="${user==null }">
	    <a href="${pageContext.request.contextPath }/servlet/RegisterUIServlet">注册</a>
	    <a href="${pageContext.request.contextPath }/servlet/LoginUIServlet">登陆</a>
    </c:if>
    
    <c:if test="${user!=null }">
    	欢迎您${user.username }
    	<input type="button" value="退出登录" onclick="doLogout()">
    </c:if>
    <hr/>
  </body>
</html> 
