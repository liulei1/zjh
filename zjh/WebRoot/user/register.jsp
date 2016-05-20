<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>注册-专家汇</title>
    <link href="../qing_style/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../qing_style/css/load_new.css">
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
        <li ><a href="index.html" target="_blank">首页</a></li>
        <li ><a href="#">服务介绍</a></li>
    </ul>
</div>
<br/>
<br/>
<div class="container">
    <form class="form-signin" action="user_register" namespace="/token" theme="simple" method="post">
        <div class="content">
        <div class="main">
        <div>
            <h3>注册</h3>
            <hr class="hr1"/>
        </div>
            <input type="text" class="input-block-level" placeholder="用户名"  name="uname"><br/>
            <input type="text" class="input-block-level" placeholder="邮箱"  name="password"><br/>
            <input type="password" class="input-block-level" placeholder="密码"  name="password"><br/>
            <input type="password" class="input-block-level" placeholder="重复密码"  name="repassword"><br/>
            <label for="male">男</label>
            <input type="radio" name="sex" id="male" />&nbsp;&nbsp;&nbsp;&nbsp;
            <label for="female">女</label>
            <input type="radio" name="sex" id="female" />
            <button class="btn btn-primary btn1" type="submit">注册</button>
            <button class="btn" type="reset">重置</button>
        </div>
        <div class="aside">
            <div class="passport-goto">已有账号? <a href="load.html">立即登录</a></div>
        </div>
        </div>
    </form>
</div>
<div class="footer">
    <p><strong>ZJH</strong> &copy; 2016 All Rights Reserved <a class="links" href="#">进入后台</a></p>
</div>
</body>
</html>