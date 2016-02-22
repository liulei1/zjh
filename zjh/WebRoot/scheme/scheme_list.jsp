<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<title>发布审核</title>
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
<h1 align="center"><strong>全部方案列表</strong></h1>
	<div align="center">
		<s:actionerror/>
		<table frame="border" rules="all">
			<tr>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">标题</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">酬金</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">类别</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">状态</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">查看</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">拒绝</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">批准</td>
			</tr>
			<s:iterator value="schemes" var="schemes">
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
					<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%" class="state">
						${state}
					</td>
					<td align="center" style="HEIGHT: 22px">
						<s:a action="scheme_view" namespace="/scheme">
							<s:param name="id" value="id" />
							<img src="${pageContext.request.contextPath}/images/button_view.gif" border="0" style="CURSOR: hand">
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 22px">
						<s:a action="scheme_delete" namespace="/scheme">
							<s:param name="id" value="id" />
							<img src="${pageContext.request.contextPath}/images/i_del.gif"
								width="16" height="16" border="0" style="CURSOR: hand">
						</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
	<a href="#" target=main onclick ="javascript:history.go(-1);">返回继续</a>
	</div>
</body>
</html>