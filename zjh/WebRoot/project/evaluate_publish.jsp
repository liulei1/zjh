<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>project evaluation</title>
<style> 
	body
	{
		/* background:url(${pageContext.request.contextPath}/images/background.jpg); */
		background-size:100% 100%;
		background-repeat:no-repeat;
	}
</style>
</head>
<!-- 引入 Bootstrap -->
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		var usertype = ${user.usertype};
		if(usertype == "1"){
			// 企业
			$("#form").attr("action","evaluate_compEvaluate");
			$("#grade").attr("name","com_grade");
			$("#text").attr("name","com_text");
		}else if(usertype == "2"){
			// 专家
			$("#form").attr("action","evaluate_profEvaluate");
			$("#grade").attr("name","prof_grade");
			$("#text").attr("name","prof_text");
		}else{
			// 其他用户无权评论
			$("#submit").attr("disabled","disabled");
			alert("您无权评价！");
		}
	});
</script>
<body>
	<div align="center">
		<h1>my evaluation</h1>
		<br>
		<s:form id="form" cssClass="form-horizontal" role="form" action="" namespace="/evaluate" method="post" enctype="multipart/form-data" theme="simple">
			<s:hidden name="id" value="%{model.id}"></s:hidden>
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3">name of project</label>
				<div class="col-sm-2">
					${model.title}
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3" for="bugdet"> grade</label>
				<div>
					<select name="" id="grade" class="col-sm-1">
						<option value="0">0 分</option>
						<option value="1">1 分</option>
						<option value="2">2 分</option>
						<option value="3">3 分</option>
						<option value="4">4 分</option>
						<option value="5">5 分</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3">content of evaluation</label>
				<div class="col-sm-2">
					<textarea id="text" rows="3" class="form-control" name=""></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-2 col-sm-offset-5">
					<input type="submit" class="btn btn-primary" value="release" id="submit">
					
					<input type="reset" class="btn btn-warning" value="cancle">
				</div>
			</div>
		</s:form>
	</div>
</body>
</html>