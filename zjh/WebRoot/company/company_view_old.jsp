<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>专家用户个人信息</title>

<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript">
	$(function() {
		$.post("${pageContext.request.contextPath}/json/listVocation.action",function(data){
			var html='<select name="field" onload="vocationSelect()">';
			$.each(data.vocationList,function(index,context){
				html+='<option value="'+context.id+'">'+context.name+'</option>';
			});
			html+='</select>';
			$('#field').html(html);
		});

		function vocationSelect(){
			var vocation = "${model.field}";
			$("select [name='category']").val(vocation);
		}

		$("#add_username").blur(function(){
			var name = "${model.name}";
			if(name != $(this).val()){
				$.post("${pageContext.request.contextPath}/json/checkCompanyName.action",{"name":$(this).val()},function(data){
					if(data.nameExsit){
						// 用户名已经存在
						$("#result").html("<font color='red' class='check'>用户名已经存在</font>");
					}else{
						// 用户名不存在
						$("#result").html("<font color='green' class='check'>用户名不存在，可以使用</font>");
					}
				});
			}
		}); 
	}); 
	function check(){
		var flag=$(".check").html();
		if(flag=="用户名已经存在"){
			return false;
		}else {
			return true;
		}
	}
</script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="../qing_style/css/bootstrap.min.css" rel="stylesheet">
	<link href="../qing_style/css/navtop_new.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="/zjh/bootstrap3/js/bootstrap.min.js"></script>
</head>
<body>
<div id="myTabContent" class="tab-content">
<div class="container tab-pane fade in active" style="margin-top:10px" id="home">
		<div class="line">
			<div class="col-xs-12">
				<div class="login-wrapper">
					<div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">
						<div class="form-center">
		<s:form class="form-horizontal" action="company_updateProfessorInfo" namespace="/company" theme="simple" method="post" cssStyle="max-height: 400px;"
			onsubmit="return check();">
				<div class="form-group">
					<h1 align="center">Change User Information</h1>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Username</label>
					<div class="col-sm-4">
						<input type="text"  class="form-control" name="name" value="${model.name}" id="add_username">
					</div>
					<div class="col-sm-5">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Email</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="email" value="${model.email}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Telephone</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="telephone" value="${model.telephone}">
					</div>
				</div>
				<div class="form-group">
					<label  class="col-sm-4 control-label">Address</label>
					<div class="col-sm-4">
						<input type="text"class="form-control"  name="address" value="${model.address}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Website</label>
					<div class="col-sm-4">
						<input type="text" class="form-control"  name="website" value="${model.website}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Sex</label>
					<div class="col-sm-4">
						<s:radio list="{'male','female'}" name="sex" align="right" value="%{model.sex}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Vocation</label>
					<div id="field" class="col-sm-4"></div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Annotation</label>
					<div class="col-sm-4">
						<textarea rows="2" class="form-control" name="annotation" cols="30">${model.annotation}</textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-7 form-btn-group">
						<s:a action="consult_allow" namespace="/consult">
							<s:param name="id" value="id"/>
							<button class="btn btn-info pull-left" type="submit">submit</button>
						</s:a>
						<s:a action="consult_reject" namespace="/consult">
							<s:param name="id" value="id"/>
							<button class="btn btn-success pull-left" onclick="javascript:history.go(-1);">back</button>
						</s:a>
						<!--a href="#" onClick="javascript:history.go(-1)" ><span class="glyphicon glyphicon-circle-arrow-left">返回</span></a-->                        </div>
				</div>
		</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>