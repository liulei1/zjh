<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript">
	$(function() {
		$.post("${pageContext.request.contextPath}/json/listVocation.action",function(data){
			var html='<select name="field"><option selected="selected">--请选择领域--</option>';
			$.each(data.vocationList,function(index,context){
				html+='<option value="'+context.id+'">'+context.name+'</option>';
			});
			html+='</select>';
			$('#field').html(html);
		});

		$("#add_username").blur(function(){
			$.post("${pageContext.request.contextPath}/user/checkCompanyName.action",{"name":$(this).val()},function(data){
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
		if(flag=="用户名已经存在"){
			return false;
		}else if(flag==null){
			return false;
		}else{
			return true;
		}
	}
	
</script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>regist</title>
<link href="../qing_style/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="../qing_style/css/navtop_new01.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/bootstrap3/js/bootstrap.min.js"></script>
	<style>
		html,body{
			color:#505050;
			background-color:#f9f9f9;
			font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
			font-size: 14px;
			line-height: 1.8;
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
		.footer{
			height:30px;
			border-top: 1px dashed #999999;
			margin-top:10px;}
		.footer p{text-align: center}
	</style>
</head>

<body class="changeColor">
	<div class="navBar">
	<div class="design_class">
		<h3>ZJH</h3>
	</div>
	<ul class="nav_list" >
		<li ><a href="index.html" target="_blank">首页</a></li>
		<li ><a href="#">服务介绍</a></li>
	</ul>
	<a href="../user/login_new.html" target="_parent"><span>登录</span></a>
</div>
<br/>
<!--span>Collection of professors platform</span-->
	<div class="container">
		<div align="center">
			<span id="result"></span><font color="red"> <s:actionerror />
				<s:fielderror /> </font>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div class="register-wrapper">
					<div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">
		<s:form cssClass="form-horizontal" action="company_register"  namespace="/token" theme="simple" method="post" cssStyle="max-height: 400px;"
			onsubmit="return check();">
			<div class="form-group">
				<h1>
					Regist
					<small>Welcome to ZJH, Enterprise users registered in this interface.</small>
				</h1>
				<hr>
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
					<s:textfield type="repassword" name="repassword"
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
				<label class="col-sm-4 control-label">Sex</label>
				<div class="col-sm-4">
					<s:radio list="{'male','female'}" name="sex" align="right" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Field</label>
				<div class="col-sm-4">
					<div id="field" ></div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-4 col-md-7 form-btn-group">
					<input type="submit" class="btn btn-success btn-large pull-left" value="regist">
					<button class="btn btn-info btn-large pull-left" type="reset">reset</button>
				</div>
			</div>
			
			<!--div class="content">
				<div class="main">
					<div class="">
				<p align="center">Regist</p>
						<hr class="hr1" />
					</div>
					<s:textfield name="name" id="add_username"
						class="input-block-level" placeholder="用户名" />

					<s:textfield name="email" class="input-block-level"
						placeholder="邮箱" />
					<s:textfield type="password" name="password"
						class="input-block-level" placeholder="密码" />
					<s:textfield type="password" name="repassword"
						class="input-block-level" placeholder="重复密码" />
					<br>
					<s:textfield name="real_name" placeholder="真实姓名"></s:textfield>
					<br>
					<s:textfield name="telephone" placeholder="联系方式"></s:textfield>
					<br>
					<s:textfield name="address" placeholder="地址"></s:textfield>
					<br>
					<s:textfield name="website" placeholder="网址"></s:textfield>
					<br>
					性别:<s:radio list="{'male','female'}" name="sex" align="right"/>
					<br>
					<div id="field"></div>
					<button class="btn btn-warning btn1" type="submit">regist</button>
					<button class="btn" type="reset">reset</button>
				</div>
				<div class="aside">
					<div class="passport-goto" style="text-align: center">
						haven a account? <a href="${pageContext.request.contextPath }/user/login.jsp ">login now</a>
					</div>
				</div>
			</div-->
		</s:form>

					</div>
				</div>
			</div>
		</div>
	</div>
<div class="footer">
	<p><strong>ZJH</strong> &copy; 2016 All Rights Reserved <a class="links" href="#">进入后台</a></p>
</div>
</body>
</html>