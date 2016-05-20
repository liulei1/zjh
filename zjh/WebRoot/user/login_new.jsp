<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登陆-专家汇</title>
    <link href="../qing_style/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../qing_style/css/load_new.css">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
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
    </script>
</head>
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
    <form class="form-signin" action="/zjh/user/user_login" method="post" onsubmit="return loginCheck()">
        <div class="content">
        <div class="main">
        <div>
            <h3>用户登录</h3>
            <hr class="hr1"/>
        </div>
        <input type="text" class="input-block-level" placeholder="用户名"  name="uname"><br/>
        <input type="password" class="input-block-level" placeholder="密码"  name="password"><br/>
        <div class="select-style">
        <select name="usertype" id="usertype" class="usertype-style" >
            <option selected="selected">--请选择用户类型--</option>
            <option value="company">企业用户</option>
            <option value="professor">专家用户</option>
            <option value ="normal">普通用户</option>
        </select>
            <!--input type="text" name="validateCode" class="input-block" placeholder="请输入验证码" >
            <img src="ValidateCodeServlet"  class="img-rounded" onclick="this.src='ValidateCodeServlet?'+Math.random();" /><br/-->
            <input name="captcha" type="text" class="input-block"placeholder="请输入验证码">
            <img id="captchaImg" src="${pageContext.request.contextPath}/user/user_getCode" onclick="refreshCode()">

        </div>
        <br/>
        <button class="btn btn-primary btn1" type="submit">登陆</button>
        <button class="btn" type="reset">重置</button>
        </div>
        <div class="aside">
            <div class="passport-goto"style="text-align: center">没有账号? <a href="register.html" >免费注册</a></div>
        </div>
        </div>
    </form>
</div>
<div class="footer">
    <p><strong>ZJH</strong> &copy; 2016 All Rights Reserved <a class="links" href="#">进入后台</a></p>
</div>
</body>
</html>