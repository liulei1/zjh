<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>consult release</title>
</head>
<!-- 引入 Bootstrap -->
<link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$.post("${pageContext.request.contextPath}/json/listVocation.action",function(data){
			//alert(data.vocationList[0].name);
			var html = '<select name="category"><option selected="selected" value="">--请选择领域--</option>';
			$.each(data.vocationList, function(index, context){
  				html += '<option value="' + context.name +'">' + context.name +'</option>';
             });
             html += '</select>';
             $('#field').html(html);
		});
	});
	
	function publishCheck(){
            var category = $("select[name='category']").val();
            if("" == category){
                alert("请选择需求所属领域");
                return false;
            }else {
                return true;
            }
        }
</script>
<body>
	<div align="center">
		<h1>consult release</h1>
		<br>
		<s:form cssClass="form-horizontal" role="form" action="consult_publish" namespace="/consult" method="post" enctype="multipart/form-data" theme="simple" onsubmit="return publishCheck()">
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3">title</label>
				<div class="col-sm-2">
				<s:textfield cssClass="form-control" id="title" name="title"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3" for="bugdet">money</label>
				<div class="col-sm-2">
					<s:textfield cssClass="form-control" id="budget" name="budget"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3">describe</label>
				<div class="col-sm-2">
					<s:textarea id="details" name="details" rows="3" cssClass="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3">document</label>
				<div class="col-sm-2">
					<s:file name="file"></s:file>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3">vocation</label>
				<div class="col-sm-1" id="field"></div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label col-sm-offset-3">remarks</label>
				<div class="col-sm-2">
					<s:textarea name="remark" rows="2" cssClass="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-2 col-sm-offset-5">
					<input type="submit" class="btn btn-primary" value="release">
					<input type="reset" class="btn btn-warning" value="cancle">
				</div>
			</div>
		</s:form>
	</div>
</body>
</html>
