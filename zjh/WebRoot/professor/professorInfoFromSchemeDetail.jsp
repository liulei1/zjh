<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>专家用户个人信息</title>
<link href="${pageContext.request.contextPath}/qing_style/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/qing_style/css/navtop_new.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</head>

<body>
<div id="myTabContent" class="tab-content">
	<div class="container tab-pane fade in active" style="margin-top:10px" id="home">
		<div class="row">
			<div class="col-xs-12">
				<div class="login-wrapper">
					<div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">
						<div class="form-center">
								<s:form cssClass="form-horizontal" action="professor_updateProfessorInfo" namespace="/professor" theme="simple" method="post" cssStyle="max-height: 400px;" >
									<div class="form-group">
										<h1 align="center">Professor Information</h1>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Username</label>
										<div class="col-sm-4">
											${model.name}
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Email</label>
										<div class="col-sm-4">
											${model.email}
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Telephone</label>
										<div class="col-sm-4">
											${model.telephone}
										</div>
									</div>
									<div class="form-group">
										<label  class="col-sm-4 control-label">Address</label>
										<div class="col-sm-4">
											${model.address}
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Website</label>
										<div class="col-sm-4">
											<a href="${model.website}">${model.website}</a>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Sex</label>
										<div class="col-sm-4">
											${model.sex}
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Field</label>
										<div class="col-sm-4">
											${model.field}
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Introduction</label>
										<div class="col-sm-4">
											<textarea rows="2" class="form-control" name="introduction" cols="30">${model.introduction}</textarea>
										</div>
									</div>
							</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>