<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'proAudit.jsp' starting page</title>
  	<!-- 引入 Bootstrap -->
	<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>

  </head>
  
  <body>
   <h1 align="center"><strong>Unaudit List</strong></h1>
	<div align="center">
		<table frame="border" rules="all">
			<tr>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">name</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">real name</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">website</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">introduction</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">pass</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">refuse</td>
			</tr>
			<s:iterator value="professors" var="professor">
				<tr>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">
						${name}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
						${real_name}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">
						${website}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="8%">
						${introduction}
					</td>
					
					<td align="center" style="HEIGHT: 22px">
						<s:a action="professor_pass" namespace="/professor">
							<s:param name="id" value="id" />
							<button type="button" class="btn btn-success btn-xs">pass</button>
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 22px">
						<s:a action="professor_refuse" namespace="/professor" >
							<s:param name="id" value="id" />
							<button type="button" class="btn btn-success btn-xs">refuse</button>
						</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
		<br>
		<a href="#" onclick="javascript:history.go(-1);" ><span class="glyphicon glyphicon-circle-arrow-left">return</span></a>
	</div>
</body>
  </body>
</html>
