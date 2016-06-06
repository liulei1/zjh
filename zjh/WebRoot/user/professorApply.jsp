<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<link href="../qing_style/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="../qing_style/css/navtop_new01.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
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
	});
	
</script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Apply to be Professor</title>
<link href="${pageContext.request.contextPath }/qing_style/bootstrap.min.css"
rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/qing_style/load.css">


<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</head>

<body class="changeColor">
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
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Username</label>
				<div class="col-sm-4">
					<s:textfield name="name" id="add_username"
						   cssClass="form-control" placeholder="用户名" readonly="true" value="%{model.name}"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Email</label>
				<div class="col-sm-4">
					<s:textfield name="email" cssClass="form-control"
						   placeholder="邮箱" readonly="true" value="%{model.email}"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Password</label>
				<div class="col-sm-4">
					<s:textfield type="password" name="password"
								 cssClass="form-control" placeholder="输入密码" readonly="true" value="%{model.password}"/>
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
					<s:radio list="{'male','female'}" name="sex" align="right" readonly="true" value="%{model.sex}"/>
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
			<!--div class="content">
				<div class="main">
					<div class="">
						<p align="center">Register</p>
						<hr class="hr1" />
					</div>
					<s:token></s:token>
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
					<s:textfield name="identity" placeholder="身份证号"></s:textfield>
					<br>
					<s:textfield name="introduction" placeholder="个人简介"></s:textfield>
					<br>
					性别:<s:radio list="{'male','female'}" name="sex" align="right"/>
					<br>
					<div id="field"></div>
					<button class="btn btn-warning btn1" type="submit">regist</button>
					<button class="btn" type="reset">reset</button>
				
				</div>
				<div class="aside">
					<div class="passport-goto" style="text-align: center">
						haven a  account?
					<a href="${pageContext.request.contextPath }/user/login.jsp ">login now</a>
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


----------------------------------------
	<div class="navbar">
		<a class="brand" href="index.html"><span class="first">collection of professors platform</span>
		</a>
	</div>
	<div class="container" style="margin-top:10px">
		<div align="center">
			<span id="result"></span><font color="red"> <s:actionerror />
				<s:fielderror /> </font>
		</div>
		<s:form action="professorRegister" namespace="/token" theme="simple" method="post">
			<div class="content">
				<div class="main">
					<div class="">
						<p align="center">Apply to be Professor</p>
						<hr class="hr1" />
					</div>
					
					<s:textfield name="name" id="add_username"
						class="input-block-level" placeholder="用户名" readonly="true" value="%{model.name}"/>
					<s:textfield name="email" class="input-block-level" id="email"
						placeholder="邮箱" readonly="true" value="%{model.email}"/>
					<s:textfield type="password" name="password" id="password"
						class="input-block-level" placeholder="密码" readonly="true" value="%{model.password}"/>
					<s:textfield name="real_name" placeholder="真实姓名"></s:textfield>
					<br>
					<s:textfield name="telephone" placeholder="联系方式"></s:textfield>
					<br>
					<s:textfield name="address" placeholder="地址"></s:textfield>
					<br>
					<s:textfield name="website" placeholder="网址"></s:textfield>
					<br>
					<s:textfield name="identity" placeholder="身份证号"></s:textfield>
					<br>
					<s:textfield name="introduction" placeholder="个人简介"></s:textfield>
					<br>
					性别:<s:radio list="{'male','female'}" name="sex" align="right" readonly="true" id="sex" value="%{model.sex}"/>
					<br>
					<div id="field"></div>
					<button class="btn btn-warning btn1" type="submit">regist</button>
					<button class="btn" type="reset">reset</button>
				</div>
				<div class="aside">
					<div class="passport-goto" style="text-align: center">
						haven a  account?
					<a href="${pageContext.request.contextPath }/user/login.jsp ">login now</a>
					</div>
				</div>
			</div>
		</s:form>
	</div>
</body>
</html>