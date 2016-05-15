<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
	 <link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
	 <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	 <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
    
    <title>professor list</title>
    
  </head>
  	
  <body>
   	
   	<s:debug></s:debug>
   	
  		<s:iterator value="professors" var="professors">
  			<tr class="info">
  				<td align="center">
  					${name}
  				</td>
  				<td align="center">
  					${sex}
  				</td>
  				<td align="center">
  					${email}
  				</td>
  				<td align="center">
  					${field}
  				</td>
  				<td align="center">
  					${introduction}
  				</td>
  			</tr>
  		</s:iterator>
  </body>
  
</html>
