<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提交方案</title>
</head>
<body>
	<div align="center">
		<h1>方案详情</h1>
		<s:form action="scheme_publish" namespace="/scheme" method="post" enctype="multipart/form-data" theme="simple">
			<s:hidden name="proj_id" value="%{proj_id}"></s:hidden>
			<table>
				<tr>
					<td>描述：</td>
					<td>
						<s:textarea name="details"/>
					</td>
				</tr>
				<tr>
					<td>文档：</td>
					<td>
						<s:file name="file"></s:file>
					</td>
				</tr>
				<tr>
					<td>
						<!-- <input type="submit" value="提交" onclick="alert('操作成功！');window.close();"> -->
						<input type="submit" value="提交">
					</td>
					<td>
						<input type="reset" value="取消">
					</td>
				</tr>
			</table>
		</s:form>
	</div>
	<s:debug></s:debug>
</body>
</html>