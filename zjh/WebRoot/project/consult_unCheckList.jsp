<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入 Bootstrap -->
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<title>发布审核</title>
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
<h1 align="center"><strong>全部需求列表</strong></h1>
	<div align="center">
		<s:actionerror/>
		<table frame="border" rules="all">
			<tr>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">标题</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">酬金</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">类别</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">状态</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">查看</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">拒绝</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">批准</td>
			</tr>
			<s:iterator value="consults" var="consult">
				<tr>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">
						${title}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
						${budget}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">
						${category}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%" class="state">
						${state}
					</td>
					<td align="center" style="HEIGHT: 22px">
						<s:a action="consult_view" namespace="/consult">
							<s:param name="id" value="id" />
							<button type="button" class="btn btn-info btn-xs">查看</button>
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 22px">
						<s:a action="consult_reject" namespace="/consult">
							<s:param name="id" value="id" />
							<button type="button" class="btn btn-danger btn-xs">拒绝</button>
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 22px">
						<s:a action="consult_allow" namespace="/consult">
							<s:param name="id" value="id" />
							<button type="button" class="btn btn-success btn-xs">批准</button>
						</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
</body>
</html>