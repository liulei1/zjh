<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UserManagement</title>
<!-- 引入 Bootstrap -->
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body>

	
	<div align="center">
		<h1>MangerPage</h1>
	</div>
	<p class="text-right">
	    <a href="#">
          <span class="glyphicon glyphicon-user">&nbsp;${user.name}&nbsp;</span>
        </a>
    </p>
	<div class="btn-group" >
		<button class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/user/user_list'">userlist</button>
      	<button class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/consult/consult_unCheckList.action'">consultcheck</button>
      	<button class="btn btn-primary" onclick="window.location.href=''">usercheck</button>
      	<button class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/manage/management.jsp'">viewuser</button>
    </div>
   
</body>
</html>