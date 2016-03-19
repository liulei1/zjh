<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>注册-专家汇</title>
<link
	href="${pageContext.request.contextPath }/qing_style/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/qing_style/load.css">


<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</head>

<body class="">
	<div class="navbar">
		<a class="brand" href="index.html"><span class="first">专家汇平台</span>
		</a>
	</div>
	<div class="container" style="margin-top:10px">
		<div align="center">
			<span id="result"></span><font color="red"> <s:actionerror />
				<s:fielderror /> </font>
		</div>
		<form class="form-signin" action="user_register" namespace="/token"
			theme="simple" method="post" style="max-height: 400px;"
			onsubmit="return check();">

			<!--div class="page-header">
            <ul>
                <li class="active">登录</li>
                <li>免注册登录</li>
            </ul>
        </div-->
			<div class="content">
				<div class="main">
					<div class="">
						<p>注册</p>
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
					<s:radio list="{'男','女'}" name="sex" id="female" />
					<br>
					<button class="btn btn-warning btn1" type="submit">注册</button>
					<button class="btn" type="reset">重置</button>
				</div>
				<div class="aside">
					<div class="passport-goto" style="text-align: center">
						已有账号? <a
							href="${pageContext.request.contextPath }/user/login.jsp ">立即登录</a>
					</div>
				</div>
			</div>
		</form>
		<!--footer class="main-footer">
        <hr/>
        <p class="pull-right">&copy; 2013.8 <a href="#" target="_blank"> shun_fzll</a></p>
    </footer-->
	</div>


</body>
</html>