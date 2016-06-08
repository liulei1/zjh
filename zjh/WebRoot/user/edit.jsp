<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理员修改用户基本信息</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入 Bootstrap -->
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body>
<div align="center">
	<h1>
		<STRONG>Modify User Information</STRONG>
	</h1>
	<hr>
	<s:form action="user_edit" namespace="/user" theme="simple" method="post">
		<table>
			<tr>
				<td bgColor="#f5fafe" align="right">
					Name：
				</td>
				<td  bgColor="#ffffff">
					<s:hidden name="id" value="%{model.id}"/>
					<s:hidden name="usertype" value="%{model.usertype}"/>
					
					<s:textfield name="name" value="%{model.name}"/>
				</td>
			</tr>
			<tr>
				<td bgColor="#f5fafe" align="right">
					Password：
				</td>
				<td  bgColor="#ffffff">
					<s:textfield name="password" value="%{model.password}"/>
					<%-- <s:password showPassword="true" name="password" value="%{model.password}" /> --%>
				</td>
			</tr>
			<tr>
				<td bgColor="#f5fafe" align="right">
					Email：
				</td>
				<td bgColor="#ffffff">
					<s:textfield name="email" value="%{model.email}"/>
				</td>
			</tr>
			<tr>
				<td bgColor="#f5fafe" align="right">
					Sex：
				</td>
				<td bgColor="#ffffff">
					<s:radio list="{'male','female'}" name="sex" value="%{model.sex}"></s:radio>
				</td>
			</tr>
			<TR>
				<td align="left" bgColor="#f5fafe">
					<input type="submit" class="btn btn-success btn-sm" value="modify"/>
				</td>
				<td align="right" bgColor="#f5fafe">
					<a onclick="history.go(-1)"class="btn btn-info btn-sm">back</a>
				</td>
			</TR>
		</table>
	</s:form>
</div>
</body>
</html>