<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>需求信息</title>
</head>
<body>
<div align="center">
		<s:debug></s:debug>
		<h1>需求信息</h1>
		<table>
			<tr>
				<td>标题：</td>
				<td>
					${model.title}
				</td>
			</tr>
			<tr>
				<td>酬金</td>
				<td>
					${model.budget}&nbsp;元
				</td>
			</tr>
			<tr>
				<td>描述：</td>
				<td>
					${model.details}
				</td>
			</tr>
			<tr>
				<td>文档：</td>
				<td>
					<s:a action="consult_download" namespace="/consult">
						<s:param name="id" value="model.id"></s:param>
						<s:property value="model.filePath"/>
					</s:a>
				</td>
			</tr>
			<tr>
				<td>领域：</td>
				<td id="field">
					${model.category}
				</td>
			</tr>
			<tr>
				<td>备注：</td>
				<td>
					${model.remark}
				</td>
			</tr>
			<tr>
				<td>状态：</td>
				<td>
					${model.state}
				</td>
			</tr>
			<tr>
				<td>
					<s:a action="consult_allow" namespace="/consult">
						<s:param name="id" value="id"/>
						批准
					</s:a>
				</td>
				<td>
					<s:a action="consult_reject" namespace="/consult">
						<s:param name="id" value="id"/>
						拒绝
					</s:a>
				</td>
				<td>
					<a href="#" onclick="javascript:history.go(-1);" ><span class="glyphicon glyphicon-circle-arrow-left">返回</span></a>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>