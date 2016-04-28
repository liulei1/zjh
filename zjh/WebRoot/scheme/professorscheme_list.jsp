<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<title>方案列表</title>
</head>
<body>
<h1 align="center"><strong>我的方案</strong></h1>
	<div align="center">
		<s:actionerror/>
		<table frame="border" rules="all">
			<s:if test="schemes != null">
				<tr>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">项目编号</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">查看详细</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">发布时间</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">方案文档</td>
				</tr>
			</s:if>
			<s:iterator value="schemes" var="schemes">
				<tr>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">
						${cons_id}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="30%">
						${details}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">
						${upload_date}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="30%">
						<s:a action="scheme_download" namespace="/scheme">
						<s:param name="id" value="id"></s:param>
						<s:property value="fileName"/>
					</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
	<a href="#" onclick="javascript:history.go(-1);" ><span class="glyphicon glyphicon-circle-arrow-left">返回</span></a>
	</div>
</body>
</html>