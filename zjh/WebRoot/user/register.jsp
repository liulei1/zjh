<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>用户注册</h1>
	<font color="red">
		<s:actionerror/>
	</font>
	<form action="${pageContext.request.contextPath}/user/user_register.action" method="post">
		用户名：<input type="text" name="name"/><br/>
		密码：<input type="password" name="password"/><br/>
		Email：<input type="text" name="email"/><br/>
		性别：<input type="radio" name="sex" value="male" id="male"> <label for="male">男</label>
			<input type="radio" name="sex" value="female" id="female"><label for="female">女</label><br/>
		<input type="submit" value="提交">
		<input type="reset" value="重置">
	</form>
</body>
</html>