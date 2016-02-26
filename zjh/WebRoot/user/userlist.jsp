<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普通用户列表</title>
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
<h1 align="center"><strong>普通用户列表</strong></h1>
	<div align="center">
		<table frame="border" rules="all">
			<tr>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">用户名</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">Email</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">性别</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">修改</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">查看</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">删除</td>
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
					<td align="center" style="HEIGHT: 22px"><s:a
							action="user_editview" namespace="/user">
							<s:param name="id" value="id" />
							<button type="button" class="btn btn-success btn-xs">修改</button>
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 22px"><s:a action="user_view" namespace="/user">
							<s:param name="id" value="id" />
							<button type="button" class="btn btn-info btn-xs">查看</button>
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 22px"><s:a action="user_delete" namespace="/user" cssClass="delLink">
							<s:param name="id" value="id" />
							<button type="button" class="btn btn-danger btn-xs">拒绝</button>
						</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
		<br>
		<a href="#" onclick="javascript:history.go(-1);" ><span class="glyphicon glyphicon-circle-arrow-left">返回</span></a>
	</div>
</body>
</html>