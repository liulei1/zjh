<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>普通用户注册</title>
    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/qing_style/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/qing_style/css/navtop_new.css">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
	$(function(){
		$("#add_username").blur(function(){
			$.post("${pageContext.request.contextPath}/json/checkUserName.action",{"name":$(this).val()},function(data){
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
		var flag = $(".check").html();
		if(flag == null){
			alert("没有校验");
			return false;
		}
		if (flag=="用户名已经存在"){
			return false;
		}else{
			return true;
		}
	}
</script>
<body>
<div class="navBar">
    <div class="design_class">
        <h3>ZJH</h3>
    </div>
    <ul class="nav_list" >
        <li ><a href="${pageContext.request.contextPath}" target="_blank">首页</a></li>
        <li ><a href="${pageContext.request.contextPath}/user/introduction.jsp">服务介绍</a></li>
    </ul>
</div>
<br/>
<br/>
<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<div class="register-wrapper">
				<div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">
    <s:form cssClass="form-horizontal" action="user_register"  namespace="/token" theme="simple" method="post">
    	<div class="form-group">
				<h1>
					Regist
					<small>Welcome to ZJH, Common users registered in this interface.</small>
				</h1>
				<hr>
				<span id="result"></span>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Username</label>
				<div class="col-sm-4">
				<input type="text" class="form-control" placeholder="Username"  name="name">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Email</label>
				<div class="col-sm-4">
				<input type="text" class="form-control" placeholder="Email"  name="email">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Password</label>
				<div class="col-sm-4">
				<input type="password" class="form-control" placeholder="Password"  name="password">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">RepeatPassword</label>
				<div class="col-sm-4">
				<input type="password" class="form-control" placeholder="Repassword"  name="repassword">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">Sex</label>
				<div class="col-sm-4">
					<s:radio list="{'male','female'}" name="sex" align="right" value="%{model.sex}"/>
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
        <div>
            <h3>User Register</h3>
            <hr class="hr1"/>
        </div>
            <input type="text" class="input-block-level" placeholder="Username"  name="name"><br/>
            <input type="text" class="input-block-level" placeholder="Email"  name="email"><br/>
            <input type="password" class="input-block-level" placeholder="Password"  name="password"><br/>
            <input type="password" class="input-block-level" placeholder="Repassword"  name="repassword"><br/>
            <label for="male">male</label>
            <input type="radio" name="sex" id="male" />&nbsp;&nbsp;&nbsp;&nbsp;
            <label for="female">female</label>
            <input type="radio" name="sex" id="female" />
            <button class="btn btn-primary btn1" type="submit">register</button>
            <button class="btn" type="reset">reset</button>
        </div>
        <div class="aside">
            <div class="passport-goto">已有账号? <a href="load.html">立即登录</a></div>
        </div>
        </div-->
    </s:form>
    </div>
    </div>
    </div>
    </div>
</div>
<div class="footer">
    <p><strong>ZJH</strong> &copy; 2016 All Rights Reserved By <a class="links" href="#">USTC</a></p>
</div>
</body>
</html>