<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <title>专家用户列表</title>
  
	 <link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
	 <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	 <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
  </head>
  
  <script type="text/javascript">
  	//翻页
	function changePage(operate){
		var pageIndex = "${pageIndex}";
		var pageCount = "${pageCount}";
		if(operate == "last"){
			pageIndex = parseInt(pageIndex) - 1;
		}else if(operate == "next"){
			pageIndex = parseInt(pageIndex) + 1;
		}
		if(pageIndex < 1){
			pageIndex = 1;
		}else if(pageIndex > pageCount){
			pageIndex = pageCount;
		}
		$.post("${pageContext.request.contextPath}/professor/professor_getProfessorWithPage",{pageIndex:pageIndex},function(data){
				document.open("text/html","replace");
				document.writeln(data);
				document.close();
		});
	}
	
  </script>
  
  <body>
  <div id="LG" class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="text-center">
					Project List
				</h3>
				<table class="table table-striped">
		               <thead>
		                <tr class="warning">
							<th style="text-align:center;">
								Name
							</th>
							<th style="text-align:center;">
								Sex
							</th>
							<th style="text-align:center;">
								Email
							</th>
							<th style="text-align:center;">
								Address
							</th>
							<th style="text-align:center;">
								Website
							</th>
							<th style="text-align:center;">
								Points
							</th>
								
							<th style="text-align:center;">
								Field
							</th>
						</tr>
		               </thead>
		               <tbody>
						<s:iterator value="professors" var="professor">
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
								${address }
							</td>
							<td align="center">
								<a href="http://${website}" target="_blank">${website}</a>
							</td>
							<td align="center">
								${points}
							</td>
							<td align="center">
								${field}
							</td>
						</tr>
					</s:iterator>
				</tbody>
		     </table>
			<p id="pageIndex" style="font-size:20px;font-weight:bold;color:blue;margin-left:150px;"></p>
			<!-- 分页 -->
			<ul class="pager">
		      <li id="lastpage"><a href="#" onclick="changePage('last')">上一页</a></li>
		      <li id="lastpage"><a href="#" onclick="changePage('next')">下一页</a></li>
		    </ul>
		    <span>第&nbsp;${pageIndex}/${pageCount}&nbsp;页&nbsp;</span>
		</div>
	</div>
</div>
</body>
</html>
