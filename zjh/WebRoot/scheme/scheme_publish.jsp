<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>scheme submit</title>
</head>
<body>
	<div align="center">
		<h1>detail of scheme</h1>
		<s:form action="scheme_publish" namespace="/scheme" method="post" enctype="multipart/form-data" theme="simple">
			<s:hidden name="cons_id" value="%{cons_id}"></s:hidden>
			<table>
				<tr>
					<td>标题：</td>
					<td>
						<s:textarea name="title"/>
					</td>
				</tr>
				<tr>
					<td>description</td>
					<td>
						<s:textarea name="details"/>
					</td>
				</tr>
				<tr>
					<td>document</td>
					<td>
						<s:file name="file"></s:file>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="submit">
					</td>
					<td>
						<input type="reset" value="cancle">
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>
