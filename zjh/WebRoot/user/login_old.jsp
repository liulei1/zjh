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
        
		if (window != top)
			top.location.href = location.href;
    </script>
</head>

<body class="">
<div class="navbar">
    <h1>
    	<a class="brand" href="index.html"><span class="first">Collection Of Professors Platform</span></a></div>
    </h1>

<div class="container"style="margin-top:10px">
    <form class="form-signin" action="${pageContext.request.contextPath}/user/user_login" method="post" onsubmit="return loginCheck()">
        <div class="content" style="height: 350px;">
        <div class="main">
        <div class="" >
            <h2 align="center">User Login</h2>
            <hr class="hr1"/>
        
	        <input type="text" class="input-block-level" placeholder="Username"  name="name"><br/>
	        <input type="password" class="input-block-level" placeholder="Password"  name="password"><br/>
	        
	        <div class="select-style">
		        <select name="usertype" id="usertype" class="usertype-style" >
		            <option selected="selected">-- Select Role --</option>
		            <option value="company">Company User</option>
		            <option value="professor">Professor User</option>
		            <option value ="normal">Common User</option>
		            <option value ="administer">Administer</option>
		        </select>
				<input name="captcha" type="text" class="loginuser" placeholder="Vertification" /><br>
		    	<center>
		    		<img id="captchaImg" src="${pageContext.request.contextPath}/user/user_getCode" onclick="refreshCode()"/>
				</center>
	        </div>
        </div>
        <br/>
        <button class="btn btn-warning" type="submit">login</button>
        <button class="btn" type="reset">reset</button>
        </div>
        <div class="aside">
            <div class="passport-goto"style="text-align: center">Have no account? Regist with free 
            <br>
            <br>
            <a href="${pageContext.request.contextPath }/professor/professor_register.jsp">Regist Of Professor</a><br>
            <br>
            <a href="${pageContext.request.contextPath }/company/company_register.jsp">Regist Of Company</a><br>
            <br>
            <a href="${pageContext.request.contextPath }/user/register.jsp">Regist Of Common User</a>
            </div>
        </div>
        </div>
    </form>
</div>


</body>
</html>
<>