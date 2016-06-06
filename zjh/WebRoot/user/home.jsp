<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>HomePage For User</title>
    
	
  </head>
  
  <body>
   <a href="${pageContext.request.contextPath}/user/user_userInitInformation">Apply to be Professor</a><br>
   <a href="${pageContext.request.contextPath}/user/user_companyInit">Apply to be Company</a>
  </body>
</html>
