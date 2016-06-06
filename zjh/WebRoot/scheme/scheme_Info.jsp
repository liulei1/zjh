<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>方案信息展示</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- 引入 Bootstrap -->
    <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/qing_style/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/qing_style/css/navtop_new.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
</head>

<body>
	<div id="LG" class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="login-wrapper">
					<div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">

						<div class="form-group">
							<h1>
								Scheme Details <small>Scheme of professor published.</small>
							</h1>
							<hr>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">Title</label>
							<div class="col-sm-8">
								<p class="form-control-static">${model.title}</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">Author</label>
							<div class="col-sm-8">
								<p class="form-control-static">
									<s:a action="professor_viewProfessorInfoById" namespace="/professor">
										<s:param name="id" value="model.professor.id"></s:param>
										${model.professor.name}
									</s:a>
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">Details</label>
							<div class="col-sm-8">
								<p class="form-control-static">${model.details}</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">File</label>
							<div class="col-sm-8">
								<p class="form-control-static">
									<s:a action="scheme_download" namespace="/scheme">
										<s:param name="id" value="model.id"></s:param>
										<s:property value="model.fileName"/>
									</s:a>
								</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">Release time</label>
							<div class="col-sm-8">
								<p class="form-control-static">${model.upload_date}</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
    <p><strong>ZJH</strong> &copy; 2016 All Rights Reserved By USTC</p>
</div>
</body>
</html>
