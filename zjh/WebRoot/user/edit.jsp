<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<s:form action="user_edit" namespace="/user" theme="simple" method="post">
		<s:debug />
		<table>
			<tr>
				<td height="26" align="center">
					<strong> 
						<STRONG>user modify</STRONG>
					</strong>
				</td>
			</tr>
	
			<tr>
				<td bgColor="#f5fafe" align="right">
					name：
				</td>
				<td  bgColor="#ffffff">
					<s:hidden name="id" value="%{model.id}"/>
					<s:textfield name="name" value="%{model.name}"/>
				</td>
			</tr>
			<tr>
				<td bgColor="#f5fafe" align="right">
					password：
				</td>
				<td  bgColor="#ffffff">
					<s:password showPassword="true" name="password" value="%{model.password}" />
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
					sex：
				</td>
				<td bgColor="#ffffff">
					<s:radio list="{'男','女'}" name="sex" value="%{model.sex}"></s:radio>
				</td>
			</tr>
			<TR>
				<td align="left" bgColor="#f5fafe">
					<INPUT type="submit" value="modify"/> 
				</td>
				<td align="right" bgColor="#f5fafe">
					<INPUT type="button" onclick="history.go(-1)" value="return" /> 
					<span id="Label1"></span>
				</td>
			</TR>
		</table>
	</s:form>
</div>
</body>
</html>