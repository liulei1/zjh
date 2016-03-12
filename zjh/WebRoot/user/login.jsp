<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html >
<head>


    <title>登陆-专家汇</title>
    <link href="${pageContext.request.contextPath }/qing_style/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/qing_style/load.css">
    
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
        function loginCheck(){
            var usertype = $("#usertype").val();
            if("--请选择用户类型--" == usertype){
                alert("请选择用户类型");
                return false;
            }else {
                return true;
            }
        }
        
        function refreshCode(){
        	var timenow = new Date().getTime();   
        	$("#captchaImg").attr("src","${pageContext.request.contextPath}/user/user_getCode?t="+timenow);
        }
    </script>
</head>

<body class="">
<div class="navbar">
    <a class="brand" href="index.html"><span class="first">专家汇平台</span></a>
</div>
<div class="container"style="margin-top:10px">
    <form class="form-signin" action="${pageContext.request.contextPath}/user/user_login" method="post" onsubmit="return loginCheck()">
        <div class="content" style="height: 350px;">
        <div class="main">
        <div class="" >
            <p align="center">用户登录</p>
            <hr class="hr1"/>
        </div>
        <input type="text" class="input-block-level" placeholder="用户名"  name="name"><br/>
        <input type="password" class="input-block-level" placeholder="密码"  name="password"><br/>
        
        <div class="select-style">
        <select name="usertype" id="usertype" class="usertype-style" >
            <option selected="selected">--请选择用户类型--</option>
            <option value="company">企业用户</option>
            <option value="professor">专家用户</option>
            <option value ="normal">普通用户</option>
            <option value ="administer">管理员用户</option>
        </select>
		<input name="captcha" type="text" class="loginuser" placeholder="验证码" /><br>
    	<center>
    		<img id="captchaImg" src="${pageContext.request.contextPath}/user/user_getCode" onclick="refreshCode()"/>
		</center>
        </div>
        <br/>
        <button class="btn btn-warning btn1" type="submit">登陆</button>
        <button class="btn" type="reset">重置</button>
        </div>
        <div class="aside">
            <div class="passport-goto"style="text-align: center">没有账号? 免费注册
            <br>
            <br>
            <a href="${pageContext.request.contextPath }/professor/professor_register.jsp">专家注册</a><br>
            <br>
            <a href="${pageContext.request.contextPath }/company/company_register.jsp">企业注册</a><br>
            <br>
            <a href="${pageContext.request.contextPath }/user/register.jsp">普通用户注册</a>
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
<>