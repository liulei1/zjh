<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>专家密码修改</title>
<link href="${pageContext.request.contextPath }/qing_style/bootstrap.min.css"
rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/qing_style/load.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
	function passwordConfirm(){
		var newPwd = $("#newPassword").val();
		var rpt = $("#repeatPassword").val();
		if(rpt == newPwd){
			return true;
		}else{
			alert("两次输入不一致");
			return false;
		}
	}
</script>
<body>
	<h1 align="center">Change Password</h1>
	<div align="center">
		<span>
			<font color="red">
				<%=request.getAttribute("result")==null?"":request.getAttribute("result")%>
			</font>
		</span>
		<s:form onsubmit="return passwordConfirm();" action="professor_changePassword" namespace="/professor" theme="simple" method="post">
			<table>
				<tr>
					<td>Password</td>
					<td>
						<input type="password" name="password" id="oldPassword" required="required">
					</td>
				</tr>
				<tr>
					<td>New Password</td>
					<td>
						<input type="password" name="newPassword" id="newPassword" required="required">
					</td>
				</tr>
				<tr>
					<td>Repeat Password</td>
					<td>
						<input type="password" id="repeatPassword" required="required">
					</td>
				</tr>
			</table>
			<br>
			<button class="btn btn-info" type="submit">submit</button>
			<button class="btn btn-warning" onclick="javascript:history.go(-1);">back</button>
		</s:form>
	</div>
</body>
</html>