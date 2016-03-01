<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</HEAD>
<body>
	<s:debug/>
	<div align="center">
		<table>
			<tr>
				<td colSpan="8" height="26" align="center">
					<strong> 
						<STRONG>查看用户</STRONG>
					</strong>
				</td>
			</tr>
	
			<tr>
				<td align="center" bgColor="#f5fafe">
					登录名：
				</td>
				<td  bgColor="#ffffff">
					<s:property value="model.name" />
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
					性别：
				</td>
				<td bgColor="#ffffff">
					<s:property value="model.sex"/>
				</td>
			</tr>
			<TR>
				<td align="center" colSpan="4" class="sep1"><img
					src="${pageContext.request.contextPath}/images/shim.gif"></td>
			</TR>
			<TR>
				<td class="ta_01" style="WIDTH: 100%" align="right" bgColor="#f5fafe" colSpan="4">
					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回" /> 
					<span id="Label1"></span>
				</td>
			</TR>
		</table>
	</div>
</body>
</HTML>