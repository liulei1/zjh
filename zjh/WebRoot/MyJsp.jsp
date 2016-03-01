<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
   <head>
      <title>Bootstrap 模板</title>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <!-- 引入 Bootstrap -->
	  <link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
	  <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	  <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
   </head>
   <body>
    <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<form>
				<fieldset>
					 <legend>表单项</legend> 
					 <label>表签名</label><input type="text" /> 
					 <span class="help-block">这里填写帮助信息.</span> 
					 <label class="checkbox"><input type="checkbox" /> 勾选同意</label> 
					 <button type="submit" class="btn">提交</button>
				</fieldset>
			</form>
		</div>
	</div>
</div>
</html>