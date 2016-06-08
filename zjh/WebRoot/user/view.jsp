<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<HTML>
<HEAD>
<title>用户基本信息页面</title>

<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入 Bootstrap -->
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css"rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</HEAD>
<body>
<div align="center">
	<h1>
		<STRONG>User Information</STRONG>
	</h1>
	<hr>
	<div align="center">
		<table class="table table-bordered">
			<tr>
				<td align="center" bgColor="#f5fafe" width="20%">
					UserName
				</td>
				<td bgColor="#ffffff" width="20%">
					<s:property value="model.name"/>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe">
					Email：
				</td>
				<td bgColor="#ffffff">
					<s:property value="model.email"/>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe">
					Sex：
				</td>
				<td bgColor="#ffffff">
					<s:property value="model.sex"/>
				</td>
			</tr>
		</table>
		<a onclick="history.go(-1)"class="btn btn-info btn-sm">back</a>
	</div>
</div>
</body>
</HTML>