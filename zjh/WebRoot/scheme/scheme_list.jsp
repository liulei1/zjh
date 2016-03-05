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
<title>方案列表</title>
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
<h1 align="center"><strong>所有方案列表</strong></h1>
	<div align="center">
		<s:actionerror/>
		<table frame="border" rules="all">
			<s:if test="schemes != null">
				<tr>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">编号</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">专家编号</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">项目编号</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">详细</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">发布时间</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">文档名称</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">文档地址</td>
				</tr>
			</s:if>
			<s:iterator value="schemes" var="schemes">
				<tr>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">
						${id}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
						${professor.id}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">
<<<<<<< HEAD
						${cons_id}
=======
						${proj_id}
>>>>>>> refs/heads/dev
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">
						${details}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">
						${upload_date}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">
						${fileName}
					</td>
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">
						${filePath}
					</td>
					
				</tr>
			</s:iterator>
		</table>
	<a href="#" onclick="javascript:history.go(-1);" ><span class="glyphicon glyphicon-circle-arrow-left">返回</span></a>
	</div>
</body>
</html>
