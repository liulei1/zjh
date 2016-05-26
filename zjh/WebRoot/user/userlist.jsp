<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有用户信息</title>
<!-- 引入 Bootstrap -->
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".delLink").click(function(event) {
			var isConfirm = window.confirm("确定删除？");
			if (!isConfirm) {
				event.preventDefault();
			}
		});
	});

</script>
</head>
<body>
<h1 align="center"><strong>user list</strong></h1>
	<div align="center">
		<table frame="border" rules="all">
			<tr>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">user name</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">Email</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">sex</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">modify</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">review</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">delete</td>
			</tr>
			<s:iterator value="users" var="user">
				<tr>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">
						${name}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
						${email}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">
						${sex}
					</td>
					
					
					<td align="center" style="HEIGHT: 22px">
						<s:a action="user_editview" namespace="/user">
							<s:param name="id" value="id" />
							<button type="button" class="btn btn-info btn-xs">modification</button>
						</s:a>
					</td>
					
					
					<td align="center" style="HEIGHT: 22px">
						<s:a action="user_view" namespace="/user">
							<s:param name="id" value="id" />
							<button type="button" class="btn btn-success btn-xs">view</button>
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 22px">
						<s:a action="user_delete" namespace="/user" cssClass="btn btn-danger btn-xs delLink">
							<s:param name="id" value="id" />
							delete
						</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
		<br>
		<a href="#" onclick="javascript:history.go(-1);" ><span class="glyphicon glyphicon-circle-arrow-left">return</span></a>
	</div>
</body>
</html>