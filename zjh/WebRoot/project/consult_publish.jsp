<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>发布需求</title>
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
		$.post("${pageContext.request.contextPath}/listVocation.action",function(data){
			//alert(data.vocationList[0].name);
			var html = '<select name="category"><option selected="selected">--请选择领域--</option>';
			$.each(data.vocationList, function(index, context){
  				html += '<option value="' + context.name +'">' + context.name +'</option>';
             });
             html += '</select>';
              $('#field').html(html);
		});
	});
</script>
<body>
	<div align="center">
		<h1>需求发布</h1>
		<br>
		<s:form cssClass="form-horizontal" role="form" action="consult_publish" namespace="/consult" method="post" enctype="multipart/form-data" theme="simple">
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3">标题</label>
				<div class="col-sm-2">
				<s:textfield cssClass="form-control" id="title" name="title"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3" for="bugdet"> 酬金(元)</label>
				<div class="col-sm-2">
					<s:textfield cssClass="form-control" id="budget" name="budget"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3">描述</label>
				<div class="col-sm-2">
					<s:textarea id="details" name="details" rows="3" cssClass="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3">文档</label>
				<div class="col-sm-2">
					<s:file name="file"></s:file>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3">领域</label>
				<div class="col-sm-1" id="field">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3">备注</label>
				<div class="col-sm-2">
					<s:textarea name="remark" rows="2" cssClass="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-2 col-sm-offset-5">
					<input type="submit" class="btn btn-primary" value="发布">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" class="btn btn-warning" value="取消">
				</div>
			</div>
		</s:form>
	</div>
</body>
</html>