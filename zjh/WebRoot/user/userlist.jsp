<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普通用户列表</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
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
							<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 22px"><s:a action="user_view" namespace="/user">
							<s:param name="id" value="id" />
							<img src="${pageContext.request.contextPath}/images/button_view.gif" border="0" style="CURSOR: hand">
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 22px"><s:a action="user_delete" namespace="/user" cssClass="delLink">
							<s:param name="id" value="id" />
							<img src="${pageContext.request.contextPath}/images/i_del.gif"
								width="16" height="16" border="0" style="CURSOR: hand">
						</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
</body>
</html>