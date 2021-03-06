 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>未接受的咨询列表</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<!-- 引入 Bootstrap -->
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
	// 点击弹出窗口
	function add_dialog() {
	var str = window.showModalDialog("${pageContext.request.contextPath}/scheme/scheme_publish.jsp","schemeWindow","center:yes;dialogWidth:400px;dialogHeight:400px;resizable:no");
	}
	
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
		$.post("${pageContext.request.contextPath}/consult/consult_allowList",{pageIndex:pageIndex},function(data){
				document.open("text/html","replace");
				document.writeln(data);
				document.close();
		});
		
	}
</script>
</head>
<body>
<h1 align="center"><strong>Receive Consult</strong></h1>
	<div align="center">
		<table class="table table-striped">
			<tr>
				<th style="text-align:center;" width="25%">Title</th>
				<th style="text-align:center;" width="25%">Money</th>
				<th style="text-align:center;" width="20%">Field</th>
				<th style="text-align:center;" width="15%" >Details</th>
				<th style="text-align:center;" width="15%">Receive</th>
			</tr>
			<s:iterator value="consults" var="consult">
				<tr>
					<td align="center">
						${title}
					</td>
					<td align="center">
						${budget}
					</td>
					<td align="center">
						${category}
					</td>
					<td align="center">
						<s:a action="consult_view" namespace="/consult">
							<s:param name="id" value="id"/>
							<button type="button" class="btn btn-info btn-xs">View</button>
						</s:a>
					</td>
					<td align="center">
						<s:a action="scheme_submitView" namespace="/scheme">
							<s:param name="cons_id" value="id"/>
							<button type="button" class="btn btn-success btn-xs">Submit Scheme</button>
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
			    <span>第&nbsp;${pageIndex}/${pageCount}&nbsp;页&nbsp;</span>
	</div>
</body>
</html>
