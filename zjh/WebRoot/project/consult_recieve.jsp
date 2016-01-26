<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<title>咨询接受</title>
</head>
<body>
<h1 align="center"><strong>待接受咨询</strong></h1>
	<div align="center">
		<s:actionerror/>
		<table frame="border" rules="all">
			<tr>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">标题</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">酬金</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">类别</td>
				<!-- <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%">状态</td> -->
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">查看</td>
				<td style="CURSOR: hand; HEIGHT: 22px" align="center" width="10%">接受</td>
			</tr>
			<s:iterator value="consults" var="consult">
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
					<%-- <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="12%" class="state">
						${state}
					</td> --%>
					<td align="center" style="HEIGHT: 22px">
						<s:a action="consult_view" namespace="/consult">
							<s:param name="id" value="id"/>
							<img src="${pageContext.request.contextPath}/images/button_view.gif" border="0" style="CURSOR: hand">
						</s:a>
					</td>
					<td align="center" style="HEIGHT: 22px">
						<s:a action="consult_recieve" namespace="/consult">
							<s:param name="id" value="id"/>
							<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
						</s:a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
</body>
</html>