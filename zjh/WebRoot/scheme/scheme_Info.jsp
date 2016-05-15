<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scheme information</title>
</head>
<body>
<div align="center">
		<h1>details of scheme</h1>
		<table>
			<tr>
				<td>标题：</td>
				<td>
					${model.title}
				</td>
			</tr>
			<tr>
				<td>description</td>
				<td>
					${model.details}
				</td>
			</tr>
			<tr>
				<td>document</td>
				<td>
					<s:a action="scheme_download" namespace="/scheme">
						<s:param name="id" value="model.id"></s:param>
						<s:property value="model.fileName"/>
					</s:a>
				</td>
			</tr>
			<tr>
				<td>
					author
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
					release time
				</td>
				<td>
					${model.upload_date}
				</td>
			</tr>
		</table>
		<a href="#" onclick="history.go(-1)">return</a>
	</div>
</body>
</html>