<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>方案信息</title>
</head>
<body>
<div align="center">
		<h1>方案详情</h1>
		<table>
			<tr>
				<td>描述：</td>
				<td>
					${model.details}
				</td>
			</tr>
			<tr>
				<td>文档：</td>
				<td>
					<s:a action="scheme_download" namespace="/scheme">
						<s:param name="id" value="model.id"></s:param>
						<s:property value="model.fileName"/>
					</s:a>
				</td>
			</tr>
			<tr>
				<td>
					作者：
				</td>
				<td>
					<s:a action="" namespace="">
						<s:param name="id" value="model.professor.id"></s:param>
						${model.professor.name}
					</s:a>
				</td>
			</tr>
			<tr>
				<td>
					发布时间：
				</td>
				<td>
					${model.upload_date}
				</td>
			</tr>
		</table>
		<a href="#" onclick="history.go(-1)">返回</a>
	</div>
</body>
</html>