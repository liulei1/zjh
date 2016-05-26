<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>从方案中查看专家用户的信息</title>

<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="${pageContext.request.contextPath }/qing_style/bootstrap.min.css"
rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/qing_style/load.css">


<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body class="">
	<div class="navbar">
		<a class="brand" href="${pageContext.request.contextPath}/professor/home.jsp"><span class="first">collection of professors platform</span>
		</a>
	</div>
	<div class="container" style="margin-top:10px">
		<div align="center">
			<span id="result"></span><font color="red"> <s:actionerror /><s:fielderror /> </font>
		</div>
		<s:form cssClass="form-signin" action="professor_updateProfessorInfo" namespace="/professor" theme="simple" method="post" cssStyle="max-height: 400px;" >
			<div>
				<div align="right">
					<img alt="" src="${model.image}" onerror="this.src='/qing_style/img/list/04.jpg'">
				</div>
				<div class="main">
					<table>
						<tr>
							<td>Name</td>
							<td>
								${model.name}
							</td>
						</tr>
						<tr>
							<td>Email</td>
							<td>
								${model.email}
							</td>
						</tr>
						<tr>
							<td>Education</td>
							<td>
								${model.education}
							</td>
						</tr>
						<tr>
							<td>Address</td>
							<td>
								${model.address}
							</td>
						</tr>
						<tr>
							<td>Website</td>
							<td>
								${model.website}
							</td>
						</tr>
						<tr>
							<td>Sex</td>
							<td>
								${model.sex}
							</td>
						</tr>
						<tr>
							<td>Vocation</td>
							<td>
								${model.field}
							</td>
						</tr>
						<tr>
							<td>Introduction</td>
							<td>
								<textarea rows="2" name="introduction" cols="30">${model.introduction}</textarea>
							</td>
						</tr>
						<tr>
							<td>Point</td>
							<td>
								${model.points}
							</td>
						</tr>
						<tr>
							<td>Achieve</td>
							<td>
								${model.achieve}
							</td>
						</tr>
					</table>
					<br>
					<button class="btn btn-info" type="submit">submit</button>
					<button class="btn btn-warning" onclick="javascript:history.go(-1);">back</button>
				</div>
			</div>
		</s:form>
	</div>
</body>
</html>