<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
   <head>
     <title>余额信息</title>
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <!-- 引入 Bootstrap -->
	 <link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
	 <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	 <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
	 
   </head>
   <body>
	<h3 class="text-center">
		Recharge
	</h3>
	<div>
		<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/company/company_addBalance.action" method="post">
		  <input type="hidden" name="id" value="${model.id}">
		  <div class="form-group">
		    <label class="col-sm-2 control-label">ID</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${model.id}</p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Name</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${model.name}</p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-sm-2 control-label">Balance</label>
		    <div class="col-sm-10">
		      <p class="form-control-static">${model.balance}</p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword" class="col-sm-2 control-label">Pay</label>
		    <div class="col-sm-2">
		      <input type="text" class="form-control" id="inputPassword" name="balance"
		         placeholder="please input balance">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword" class="col-sm-2 control-label">Bank</label>
		    <div class="col-sm-2">
			    <select class="form-control" >
			       <option>支付宝</option>
			       <option>中国工商银行</option>
			       <option>中国人民银行</option>
			       <option>中国农业银行</option>
			       <option>中国建设银行</option>
			       <option>中国交通银行</option>
			       <option>more</option>
			    </select>
		    </div>
		  </div>
		  <div class="form-group">
		  	<div class="col-sm-2" align="right">
  				<input type="submit" class="btn btn-primary btn-large" value="submit">
  			</div>
  			<div class="col-sm-2" align="right">
  				<button class="btn btn-primary btn-large" type="button" onclick="javascript:history.go(-1);">back</button>
  			</div>
		  </div>
		</form>
	</div>
</body>
</html>