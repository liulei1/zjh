<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html >
<head>


    <title>login collection of professors</title>
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
    <a class="brand" href="index.html"><span class="first">Collection Of Professors Platform</span></a>
</div>
<div class="container"style="margin-top:10px">
    <form class="form-signin" action="${pageContext.request.contextPath}/user/user_login" method="post" onsubmit="return loginCheck()">
        <div class="content" style="height: 350px;">
        <div class="main">
        <div class="" >
            <p align="center">user login</p>
            <hr class="hr1"/>
        </div>
        <input type="text" class="input-block-level" placeholder="user name"  name="name"><br/>
        <input type="password" class="input-block-level" placeholder="password"  name="password"><br/>
        
        <div class="select-style">
        <select name="usertype" id="usertype" class="usertype-style" >
            <option selected="selected"> category of user</option>
            <option value="company">company</option>
            <option value="professor">professor</option>
            <option value ="normal">common user</option>
            <option value ="administer">administer</option>
        </select>
		<input name="captcha" type="text" class="loginuser" placeholder="vertification code" /><br>
    	<center>
    		<img id="captchaImg" src="${pageContext.request.contextPath}/user/user_getCode" onclick="refreshCode()"/>
		</center>
        </div>
        <br/>
        <button class="btn btn-warning btn1" type="submit">login</button>
        <button class="btn" type="reset">reset</button>
        </div>
        <div class="aside">
            <div class="passport-goto"style="text-align: center">have no account?regist with free 
            <br>
            <br>
            <a href="${pageContext.request.contextPath }/professor/professor_register.jsp">regist of professor</a><br>
            <br>
            <a href="${pageContext.request.contextPath }/company/company_register.jsp">regist of company</a><br>
            <br>
            <a href="${pageContext.request.contextPath }/user/register.jsp">regist of common user</a>
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