<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
</head>
<body>
	<h1 align="center">用户注册</h1>
	<div align="center">
		<font color="red">
			<s:actionerror/>
			<s:fielderror/>
		</font>
		<s:form action="user_register" namespace="/user" theme="simple" method="post">
			<table>
				<tr>
					<td align="right">用户名：</td>
					<td>
						<s:textfield name="name"/>
					</td>
				</tr>
				<tr>
					<td align="right">Email：</td>
					<td>
						<s:textfield name="email"/>
					</td>
				</tr><tr>
					<td align="right">性别：</td>
					<td>
						<s:radio  list="{'男','女'}" name="sex"/>
					</td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td>
						<s:password name="password"/>
					</td>
				</tr>
				<tr>
					<td align="right">重复密码：</td>
					<td>
						<s:password name="repassword"/>
					</td>
				</tr>
				<tr>
					<td align="center"><input type="submit" value="提交"></td>
					<td align="center"><input type="reset" value="重置"></td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>