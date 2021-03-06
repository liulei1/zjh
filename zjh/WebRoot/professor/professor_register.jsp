<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>注册为专家用户界面</title>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/qing_style/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/qing_style/css/navtop_new.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<style>
html,body {
	color: #505050;
	background-color: #f9f9f9;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-size: 14px;
	line-height: 1.8;
}

hr {
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

.footer {
	height: 30px;
	border-top: 1px dashed #999999;
	margin-top: 10px;
}

.footer p {
	text-align: center
}
</style>
</head>
<script type="text/javascript">
	
	$(function() {
		$.post("${pageContext.request.contextPath}/json/listVocation.action",function(data){
			var html='<select name="field" class="form-control"><option selected="selected" value="0">--请选择领域--</option>';
			$.each(data.vocationList,function(index,context){
				html+='<option value="'+context.id+'">'+context.name+'</option>';
			});
			html+='</select>';
			$('#field').html(html);
		});

		$("#add_username").blur(function(){
			$.post("${pageContext.request.contextPath}/json/checkProfessorName.action",{"name":$(this).val()},function(data){
				if(data.nameExsit){
					// 用户名已经存在
					$("#result").html("<font color='red' class='check'>用户名已经存在</font>");
				}else{
					// 用户名不存在
					$("#result").html("<font color='green' class='check'>用户名不存在，可以使用</font>");
				}
			}); 
		}); 
	});
	function check(){
		var flag=$(".check").html();
		var flag2=$("#field option:selected").attr("value");
		if(flag2=="0"){
			alert("选择的领域不能为空!");
			return false;
		}
		if(flag=="用户名已经存在"){
			return false;
		}else if(flag==null){
			return false;
		}else{
			return true;
		}
	}

</script>
<body class="changeColor">
	<div class="navBar">
	<div class="design_class">
		<h3>ZJH</h3>
	</div>
	<ul class="nav_list" >
		<li ><a href="${pageContext.request.contextPath}/index.jsp" target="_blank">首页</a></li>
		<li ><a href="${pageContext.request.contextPath}/user/introduction.jsp">服务介绍</a></li>
	</ul>
	<a href="${pageContext.request.contextPath}/user/login.jsp" target="_parent"><span>登录</span></a>
</div>
<br/>
<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<div class="register-wrapper">
				<div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">
		<s:form cssClass="form-horizontal" action="professorRegister" namespace="/token" theme="simple" method="post">
			<div class="form-group">
				<h1>
					Regist
					<small>Welcome to ZJH, Expert users registered in this interface.</small>
				</h1>
				<hr>
				<span id="result"></span>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Username</label>
				<div class="col-sm-4">
					<s:textfield name="name" id="add_username"
						   cssClass="form-control" placeholder="用户名" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Email</label>
				<div class="col-sm-4">
					<s:textfield name="email" cssClass="form-control"
						   placeholder="邮箱"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Password</label>
				<div class="col-sm-4">
					<s:textfield type="password" name="password"
						   cssClass="form-control" placeholder="输入密码"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">RepeatPassword</label>
				<div class="col-sm-4">
					<s:textfield type="password" name="repassword"
						   cssClass="form-control" placeholder="重复密码"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Realname</label>
				<div class="col-sm-4">
					<s:textfield name="real_name"  cssClass="form-control" placeholder="真实姓名"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Telephone</label>
				<div class="col-sm-4">
					<s:textfield name="telephone" cssClass="form-control" placeholder="联系方式"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">address</label>
				<div class="col-sm-4">
					<s:textfield name="address" cssClass="form-control"  placeholder="地址"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">website</label>
				<div class="col-sm-4">
					<s:textfield name="website" cssClass="form-control" placeholder="网址"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">identityNumber</label>
				<div class="col-sm-4">
					<s:textfield name="identity" cssClass="form-control" placeholder="身份证号"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">introduction</label>
				<div class="col-sm-4">

					<s:textarea rows="2" cssClass="form-control" name="annotation" cols="30" placeholder="个人简介"/>

				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Sex</label>
				<div class="col-sm-4">
					<s:radio list="{'male','female'}" name="sex" align="right" value="%{model.sex}"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Field</label>
				<div class="col-sm-4" id="field">
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-4 col-md-7 form-btn-group">
					<input type="submit" class="btn btn-success btn-large pull-left" value="regist">
					<button class="btn btn-info btn-large pull-left" type="reset">reset</button>
				</div>
			</div>
		</s:form>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="footer">
	<p><strong>ZJH</strong> &copy; 2016 All Rights Reserved By <a href="#">USTC</a></p>
</div>
</body>
</html>