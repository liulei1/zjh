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
<title>scheme list</title>
</head>
<body>
<h1 align="center"><strong>all scheme list</strong></h1>
	<div align="center">
		<s:actionerror/>
		<table frame="border" rules="all">
			<s:if test="schemes != null">
				<tr>

					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">标题</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">专家编号</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="13%">项目编号</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">详细</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">发布时间</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">文档名称</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">文档地址</td>

					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">scheme id</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">professor id</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">project id</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">details</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">release time</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">name of document</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">address of document</td>

				</tr>
			</s:if>
			<s:iterator value="schemes" var="schemes">
				<tr>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">
						${title}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
						${professor.id}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="13%">
						${cons_id}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">
						${details}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">
						${upload_date}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">
						${fileName}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">
						${filePath}
					</td>
				</tr>
			</s:iterator>
		</table>
	<a href="#" onclick="javascript:history.go(-1);" ><span class="glyphicon glyphicon-circle-arrow-left">return</span></a>
	</div>
</body>
</html>
