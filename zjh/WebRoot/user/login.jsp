<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>用户登录</h1>
	
	<form action="${pageContext.request.contextPath}/user/user_login" method="post">
		<table>
			<tr>
				<td align="right">用户名：</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td align="right">密码：</td>
				<td><input type="password" name="password"/></td>
			</tr>
			<tr>
				<td align="right">用户类型：</td>
				<td>
					<select name="usertype" autofocus="autofocus">
						<option selected="selected">--请选择用户类型--</option>
  						<option value="company">企业用户</option>
  						<option value="professor">专家用户</option>
  						<option value ="normal">普通用户</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center"><input type="submit" value="提交"></td>
				<td align="center"><input type="reset" value="重置"></td>
			</tr>
			<tr>
				<td>
					<a href="${pageContext.request.contextPath}/user/register.jsp">注册</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>