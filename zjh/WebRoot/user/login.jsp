<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	function loginCheck(){
		var usertype = $("#usertype").val();
		if("--请选择用户类型--" == usertype){
			alert("请选择用户类型");
			return false;
		}else {
			return true;
		}
	}
</script>
<body>
	<h1 align="center">用户登录</h1>
	<div align="center">
	<s:actionerror/>
		<form action="${pageContext.request.contextPath}/user/user_login" method="post" onsubmit="return loginCheck()">
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
						<select name="usertype" id="usertype">
							<option selected="selected">--请选择用户类型--</option>
	  						<option value="company">企业用户</option>
							<option value="professor">专家用户</option>
							<option value="normal">普通用户</option>
							<option value="administer">管理员</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center"><input type="submit" value="提交"></td>
					<td align="center"><input type="reset" value="重置"></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="3">
						<a href="${pageContext.request.contextPath}/user/register.jsp">普通用户注册</a>
						<a href="${pageContext.request.contextPath}/company/company_register.jsp">企业注册</a>
						<a href="${pageContext.request.contextPath}/professor/professor_register.jsp">专家注册</a>
					</td>
					<td></td>
				</tr>
				
			</table>
		</form>
	</div>
</body>
</html>