<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入 Bootstrap -->
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<title>consult information</title>
<script type="text/javascript">
	$(function(){
		var type = ${user.usertype};
		if(type != 0){
			$(".btnview").hide();
		}
	});
</script>
</head>
<body>
<div align="center">
		<s:debug></s:debug>
		<h1>Consult Information</h1>
		<table>
			<tr>
				<td>Title</td>
				<td>
					${model.title}
				</td>
			</tr>
			<tr>
				<td>Money</td>
				<td>
					${model.budget}&nbsp;元
				</td>
			</tr>
			<tr>
				<td>Describation</td>
				<td>
					${model.details}
				</td>
			</tr>
			<tr>
				<td>Document</td>
				<td>
					<s:a action="consult_download" namespace="/consult">
						<s:param name="id" value="model.id"></s:param>
						<s:property value="model.filePath"/>
					</s:a>
				</td>
			</tr>
			<tr>
				<td>vocation</td>
				<td id="field">
					${model.category}
				</td>
			</tr>
			<tr>
				<td>remarks</td>
				<td>
					${model.remark}
				</td>
			</tr>
			<tr>
				<td>state</td>
				<td>
					${model.state}
				</td>
			</tr>
			<tr>
				<td class="btnview text-center">
					<s:a action="consult_allow" namespace="/consult">
						<s:param name="id" value="id"/>
						<button type="button" class="btn btn-success">approve</button>
					</s:a>
				</td>
				<td colspan="2" class="btnview text-center">
					<s:a action="consult_reject" namespace="/consult">
						<s:param name="id" value="id"/>
						<button type="button" class="btn btn-warning">reject</button>
					</s:a>
				</td>
				<td>
					<a href="#" onclick="javascript:history.go(-1)" ><span class="glyphicon glyphicon-circle-arrow-left">return</span></a>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
