<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布需求</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript">
	$(function(){
		$.post("${pageContext.request.contextPath}/listVocation.action",function(data){
			//alert(data.vocationList[0].name);
			var html = '<select name="category" autofocus="autofocus"><option selected="selected">--请选择领域--</option>';
			$.each(data.vocationList, function(index, context){
  				html += '<option value="' + context.id +'">' + context.name +'</option>';
             });
             html += '</select>';
              $('#field').html(html);
		});
	});
</script>
<body>
	<div align="center">
		<h1>需求发布</h1>
		<s:form action="consult_publish" namespace="/consult" method="post" enctype="multipart/form-data" theme="simple">
			<table>
				<tr>
					<td>标题：</td>
					<td>
						<s:textfield name="title"/>
					</td>
				</tr>
				<tr>
					<td>酬金</td>
					<td>
						<s:textfield name="budget"/>&nbsp;元
					</td>
				</tr>
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
					<td>领域：</td>
					<td id="field"></td>
				</tr>
				<tr>
					<td>备注：</td>
					<td>
						<s:textarea name="remark"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="发布">
					</td>
					<td>
						<input type="reset" value="取消">
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>