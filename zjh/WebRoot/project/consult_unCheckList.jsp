<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>管理员审核咨询的列表</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入 Bootstrap -->
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<title>check release</title>
<script type="text/javascript">
	$(function(){
		$(".state").each(function(i){
			var tag = $(".state")[i].innerHTML;
			var state;
			if(tag == 0){
				state = "待审核";
			}else if(tag == 1){
				state = "通过";
			}else if(tag == 2){
				state = "拒绝";
			}
			$(".state")[i].innerHTML = state;
		});
	});
</script>
</head>
<body>
	<div align="center">
		<h1>
			<strong>Check Consults</strong>
		</h1>
		<hr>
		<s:actionerror/>
		<table class="table table-hover table-striped table-bordered">
			<tr>
				<td style="CURSOR: hand; HEIGHT: 15px" align="center" width="5%"></td>
				<td style="CURSOR: hand; HEIGHT: 15px" align="center" width="20%">title</td>
				<td style="CURSOR: hand; HEIGHT: 15px" align="center" width="20%">money</td>
				<td style="CURSOR: hand; HEIGHT: 15px" align="center" width="15%">category</td>
				<td style="CURSOR: hand; HEIGHT: 15px" align="center" width="10%">state</td>
				<td style="CURSOR: hand; HEIGHT: 15px" align="center" width="10%">review</td>
				<td style="CURSOR: hand; HEIGHT: 15px" align="center" width="10%">reject</td>
				<td style="CURSOR: hand; HEIGHT: 15px" align="center" width="10%">approve</td>
			</tr>
			<s:iterator value="consults" var="consult" status="index">
				<tr> 
					<td style="CURSOR: hand; HEIGHT: 15px" align="center">
						<s:property value="#index.index"/>
					</td>
					<td style="CURSOR: hand; HEIGHT: 15px" align="center">
						${title}
					</td>
					<td style="CURSOR: hand; HEIGHT: 15px" align="center">
						${budget}
					</td>
					<td style="CURSOR: hand; HEIGHT: 15px" align="center">
						${category}
					</td>
					<td style="CURSOR: hand; HEIGHT: 15px" align="center" class="state">
						${state}
					</td>
					<td align="center" style="HEIGHT: 15px">
						<s:a action="consult_view" namespace="/consult">
							<s:param name="id" value="id" />
							<button type="button" class="btn btn-info btn-xs">review</button>
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 15px">
						<s:a action="consult_reject" namespace="/consult">
							<s:param name="id" value="id" />
							<button type="button" class="btn btn-danger btn-xs">reject</button>
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 15px">
						<s:a action="consult_allow" namespace="/consult">
							<s:param name="id" value="id" />
							<button type="button" class="btn btn-success btn-xs">approve</button>
						</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
</body>
</html>