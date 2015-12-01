<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>register page</title>
  </head>
  
  <body style="text-align:center;">
  <h2>注册页面</h2>
    <form action="${pageContext.request.contextPath }/servlet/RegisterServlet" method="post">
    	用户名：<input type="text" name="username" value="${formbean.username }">${formbean.errors.username }<br>
    	密码：<input type="password" name="password" value="${formbean.password }">${formbean.errors.password }<br>
    	确认密码：<input type="password" name="password2" value="${formbean.password2 }">${formbean.errors.password2 }<br>
    	邮箱：<input type="text" name="email" value="${formbean.email }">${formbean.errors.email }<br>
    	生日：<input type="text" name="birthday" value="${formbean.birthday }">${formbean.errors.birthday }<br>
    	<input type="reset" value="清空">
    	<input type="submit" value="提交">
    </form>
  </body>
</html>
