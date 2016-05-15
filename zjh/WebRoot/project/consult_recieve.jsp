 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<title>consult receive</title>
<!-- 引入 Bootstrap -->
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
	// 点击弹出窗口
	function add_dialog() {
	var str = window.showModalDialog("${pageContext.request.contextPath}/scheme/scheme_publish.jsp","schemeWindow","center:yes;dialogWidth:400px;dialogHeight:400px;resizable:no");
	}
</script>
</head>
<body>
<h1 align="center"><strong>consult have not been received</strong></h1>
	<div align="center">
		<s:actionerror/>
		<table frame="border" rules="all">
			<tr>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">title</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">money</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">category</td>
				<!-- <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">状态</td> -->
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">review</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">receive</td>
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
					<%-- <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%" class="state">
						${state}
					</td> --%>
					<td align="center" style="HEIGHT: 22px">
						<s:a action="consult_view" namespace="/consult">
							<s:param name="id" value="id"/>
							<button type="button" class="btn btn-info btn-xs">review</button>
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 22px">
						<s:a action="scheme_submitView" namespace="/scheme">
							<s:param name="cons_id" value="id"/>
							<button type="button" class="btn btn-success btn-xs">receive</button>
						</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
</body>
</html>
