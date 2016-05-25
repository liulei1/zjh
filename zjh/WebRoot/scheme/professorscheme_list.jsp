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
<h1 align="center"><strong>我的方案</strong></h1>
	<div align="center">
		<s:actionerror/>
		<table frame="border" rules="all">
			<s:if test="schemes != null">
				<tr>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">项目编号</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">方案标题</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">查看详细</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">发布时间</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">方案文档</td>
				</tr>
			</s:if>
			<s:iterator value="schemes" var="scheme">
				<tr>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">
						${cons_id}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">
						${title}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">
						${details}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">
						${upload_date}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="20%">
						<s:a action="scheme_download" namespace="/scheme">
						<s:param name="id" value="id"></s:param>
						<s:property value="fileName"/>
					</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
		<p id="pageIndex" style="font-size:20px;font-weight:bold;color:blue;margin-left:150px;"></p>
				<!-- 分页 -->
				<ul class="pager">
			      <li id="lastpage"><a href="#" onclick="changePage('last')">上一页</a></li>
			      <li id="lastpage"><a href="#" onclick="changePage('next')">下一页</a></li>
			    </ul>
			    <span>第&nbsp;${pageIndex}/${pageCount}&nbsp;页&nbsp;</span><br>
	<a href="#" onclick="javascript:history.go(-1);" ><span class="glyphicon glyphicon-circle-arrow-left">返回</span></a>
	</div>
</body>
</html>
