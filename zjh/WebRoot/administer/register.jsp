<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user register</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#add_username").blur(function(){
			$.post("${pageContext.request.contextPath}/user/checkUserName.action",{"name":$(this).val()},function(data){
				if(data.nameExsit){
					// 用户名已经存在
					$("#result").html("<font color='red'>用户名已经存在</font>");
				}else{
					// 用户名不存在
					$("#result").html("<font color='green'>用户名不存在，可以使用</font>");
				}
			}); 
		}); 
	});
</script>
</head>
<body>
	<h1 align="center">user register</h1>
	<div align="center">
		<font color="red">
			<s:actionerror/>
			<s:fielderror/>
		</font>
		<s:form action="user_register" namespace="/token" theme="simple" method="post">
			<table>
				<s:token></s:token>
				<tr>
					<td align="right">username</td>
					<td>
						<s:textfield name="name" id="add_username"/>
					</td>
					<td><span id="result"></span></td>
				</tr>
				<tr>
					<td align="right">Email：</td>
					<td>
						<s:textfield name="email"/>
					</td>
					<td></td>
				</tr><tr>
					<td align="right">sex：</td>
					<td>
						<s:radio  list="{'男','女'}" name="sex"/>
					</td>
					<td></td>
				</tr>
				<tr>
					<td align="right">password：</td>
					<td>
						<s:password name="password"/>
					</td>
					<td></td>
				</tr>
				<tr>
					<td align="right">repassword：</td>
					<td>
						<s:password name="repassword"/>
					</td>
					<td></td>
				</tr>
				<tr>
					<td align="center"><input type="submit" value="submit"></td>
					<td align="center"><input type="reset" value="reset"></td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>