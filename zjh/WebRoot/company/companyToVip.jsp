<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
	<title>余额信息</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入 Bootstrap -->
	<link href="${pageContext.request.contextPath}/qing_style/css/bootstrap.min.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
	<style>
	.pull-left{ margin-right: 40px; }
	.form-btn-group {  padding: 15px 16px; }
	h1 small {
		font-size: 18px;
		padding-left: 15px;
	}
	hr{
		-moz-border-bottom-colors: none;
		-moz-border-left-colors: none;
		-moz-border-right-colors: none;
		-moz-border-top-colors: none;
		border-color: #f6f6f6 -moz-use-text-color #b4b4b4;
		border-image: none;
		border-style: solid none;
		border-width: 1px 0;
		margin: 20px 0;
	}
	.btn-large{width:65px;}
	</style>
</head>
<body>
<div id="LG" class="container">
	<div class="row">
		<div class="col-xs-12">
			<div class="login-wrapper">
				<div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">
					<div class="form-center">
						<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/company/company_toVip" method="post">
							<input type="hidden" name="id" value="${model.id}">
							<div class="form-group">
								<h1>
									VIP
									<small>You can get more authority.</small>
								</h1>
								<hr>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">Price</label>
								<div class="col-sm-9">
									<p class="form-control-static">￥${model.vipBalance} Per month</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">Your Balance</label>
								<div class="col-sm-9">
									<p class="form-control-static">${model.balance}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">Authority</label>
								<div class="col-sm-9">
									<p class="form-control-static">${model.authority}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">EndTime</label>
								<div class="col-sm-9">
									<p class="form-control-static">${model.vipEndTime}</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">Effective Day</label>
								<div class="col-sm-4">
									<select name="effectiveMonth" id="effectiveMonth" class="form-control" >
							            <option value="1">One Month</option>
							            <option value="3">Three Month</option>
							            <option value ="6">Six Month</option>
							            <option value ="12">Twelve month</option>
							        </select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-offset-3 col-md-7 form-btn-group">
									<input type="submit" class="btn btn-success btn-large pull-left" value="submit">
									<button class="btn btn-info btn-large pull-left" type="button" onClick="javascript:history.go(-1);">back</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>