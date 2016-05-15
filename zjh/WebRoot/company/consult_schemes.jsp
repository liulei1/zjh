<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<title>project list</title>
</head>
<script type="text/javascript">
	function recieve_confirm(){
		if(confirm("确认使用这个方案？")){
			return true;
		}else{
			return false;
		}
	}
</script>
<body>
<h1 align="center"><strong>project list</strong></h1>
	<div align="center">
		<s:actionerror/>
		<table frame="border" rules="all">
			<s:if test="schemes != null">
				<tr>
					<th style="text-align:center;" width="18%" height="25px">id</th>
					<th style="text-align:center;" width="15%" height="25px">professor id</th>
					<th style="text-align:center;" width="15%" height="25px">project id</th>
					<th style="text-align:center;" width="12%" height="25px">explain of project</th>
					<th style="text-align:center;" width="10%" height="25px">time for release</th>
					<th style="text-align:center;" width="10%" height="25px">document</th>
					<th style="text-align:center;" width="7%"  height="25px">operation</th>
				</tr>
			</s:if>
			<s:iterator value="schemes" var="schemes">
				<tr>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">
						${id}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">
						${professor.id}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">
						${cons_id}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">
						${details}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">
						${upload_date}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">
						<s:a action="scheme_download" namespace="/scheme">
							<s:param name="id" value="id"></s:param>
							<s:property value="fileName"/>
						</s:a>
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="7%">
						<s:a onclick="return recieve_confirm()" action="consult_recieve" namespace="/consult">
							<s:param name="scm_id" value="id"></s:param>
							<s:param name="id" value="cons_id"></s:param>
							<button type="button" class="btn btn-success btn-xs">receive</button>
						</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
	<a href="#" onclick="javascript:history.go(-1);" ><span class="glyphicon glyphicon-circle-arrow-left">return</span></a>
	</div>
</body>
</html>