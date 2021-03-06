<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
  <head>
    <title>审核认证为企业</title>
    
  	<!-- 引入 Bootstrap -->
	<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>

  </head>
  
  <body>
   		<h1 align="center">
		 	<strong>Company Certificate Application</strong>
		 </h1>
		 <hr>
		<table class="table table-hover table-striped table-bordered">
			<tr>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="5%"></td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">Name</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="15%">Real Name</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">Website</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="25%">Address</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">Pass</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">Refuse</td>
			</tr>
			<s:iterator value="companys" var="company" status="index">
				<tr>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center">
						<s:property value="#index.index"/>
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center">
						${name}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center">
						${real_name}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center">
						<a href="http://${website}">${website}</a>
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center">
						${address}
					</td>
					
					<td align="center" style="HEIGHT: 22px">
						<s:a action="company_pass" namespace="/company">
							<s:param name="id" value="id" />
							<button type="button" class="btn btn-success btn-xs">pass</button>
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 22px">
						<s:a action="company_refuse" namespace="/company" >
							<s:param name="id" value="id" />
							<button type="button" cssClass="btn btn-danger btn-xs delLink">refuse</button>
						</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
		<br>
</body>
  </body>
</html>
