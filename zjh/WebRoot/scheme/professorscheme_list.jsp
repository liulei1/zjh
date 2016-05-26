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
	$.post("${pageContext.request.contextPath}/scheme/scheme_queryMyScheme",{pageIndex:pageIndex},function(data){
			document.open("text/html","replace");
			document.writeln(data);
			document.close();
	});
	
}
</script>
<title>方案列表</title>
</head>
<body>
	<div id="LG" class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="text-center">
					My Schemes
				</h3>
			<table class="table table-bordered">
				<thead>
						<tr class="warning">
							<th style="text-align:center;">
								SchemeNumber
							</th>
							<th style="text-align:center;">
								Title
							</th>
							<th style="text-align:center;">
								Details
							</th>
							<th style="text-align:center;">
								ReleaseDate
							</th>
							<th style="text-align:center;">
								Document
							</th>
						</tr>
					</thead>
				<tbody>
					<s:iterator value="schemes" var="scheme">
						<tr class="info">
							<td align="center" id="1">
								${cons_id}
							</td>
							<td align="center">
								${title}
							</td>
							<td align="center">
								${details}
							</td>
							<td align="center">
								${upload_date}
							</td>
							<td align="center">
								<s:a action="scheme_download" namespace="/scheme">
								<s:param name="id" value="id"></s:param>
								<s:property value="fileName"/>
							</s:a>
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
			    <span>第&nbsp;${pageIndex}/${pageCount}&nbsp;页&nbsp;</span><br>
</body>
</html>
