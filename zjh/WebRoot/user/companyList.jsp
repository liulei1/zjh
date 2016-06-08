<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员查看用户信息的页面</title>

<!-- 引入 Bootstrap -->
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {
		$(".delLink").click(function(event) {
			var isConfirm = window.confirm("Confirm delete User？");
			if (!isConfirm) {
				event.preventDefault();
			}
		});
	});

</script>
</head>
<body>
<h1 align="center"><strong>Company List</strong></h1>
	<div align="center">
		<a href="${pageContext.request.contextPath}/user/user_list" class="btn btn-primary btn-sm">普通用户</a>
		<a href="${pageContext.request.contextPath}/professor/professor_getAllProfessor" class="btn btn-success btn-sm">专家用户</a>
		<a href="${pageContext.request.contextPath}/company/company_getAllCompany" class="btn btn-warning btn-sm">企业用户</a>
		<hr>
		<table class="table table-hover table-striped">
			<tr>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="5%"></td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">Username</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">Email</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">Sex</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">Update</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">View</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">Delete</td>
			</tr>
			<s:iterator value="companys" var="user" status="index">
				<tr>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center">
						<s:property value="#index.index"/>
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center">
						${name}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center">
						${email}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center">
						${sex}
					</td>
					
					
					<td align="center" style="HEIGHT: 22px">
						<s:a action="company_editCompanView" namespace="/company">
							<s:param name="id" value="id"/>
							<button type="button" class="btn btn-info btn-xs">modify</button>
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 22px">
						<s:a action="company_viewCompanyInfoById" namespace="/company">
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
	</div>
</body>
</html>