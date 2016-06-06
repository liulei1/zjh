<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>专家提交方案</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../qing_style/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../qing_style/css/navtop_new01.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
</head>
<body>
	<div align="center">
		<div class="form-group">
				<h1>
					Submit Scheme
					<small>Professor can submit your scheme here.</small>
				</h1>
				<hr>
			</div>
		
		<s:form cssClass="form-horizontal" action="scheme_publish" namespace="/scheme" method="post" enctype="multipart/form-data" theme="simple">
			<s:hidden name="cons_id" value="%{cons_id}"></s:hidden>
			<div class="form-group">
				<label class="col-sm-4 control-label">Consult Title</label>
				<div class="col-sm-4">
					${model.consultTitle}
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Scheme Title</label>
				<div class="col-sm-4">
					<s:textfield name="title" cssClass="form-control" placeholder="title"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Description</label>
				<div class="col-sm-4">
					<s:textfield name="details" cssClass="form-control" placeholder="description"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Document</label>
				<div class="col-sm-4">
					<s:file name="file"></s:file>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-4 col-md-7 form-btn-group">
					<input type="submit" class="btn btn-success btn-large pull-left" value="submit">
					<button class="btn btn-info btn-large pull-left" type="reset">reset</button>
				</div>
			</div>
		</s:form>
	</div>
</body>
</html>
