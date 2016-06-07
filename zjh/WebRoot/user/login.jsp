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
    <link href="${pageContext.request.contextPath}/qing_style/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/qing_style/css/load_new.css">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
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
<body>
<div class="navBar">
    <div class="design_class">
        <h3>ZJH</h3>
    </div>
    <ul class="nav_list" >
        <li ><a href="${pageContext.request.contextPath}/index.jsp" target="_blank">首页</a></li>
        <li ><a href="${pageContext.request.contextPath}/user/introduction.jsp">服务介绍</a></li>
    </ul>
</div>
<br/>
<br/>
<div class="container">
    <form class="form-signin" action="${pageContext.request.contextPath}/user/user_login" method="post" onsubmit="return loginCheck()">
        <div class="content">
        <div class="main">
        <div>
            <h3>User Login</h3>
            <hr class="hr1"/>
            <s:actionerror/>
            <span>
			<font color="red">
				<b><%=request.getAttribute("result")==null?"":request.getAttribute("result")%></b>
			</font>
		</span>
        </div>
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
            <input name="captcha" type="text" class="input-block"placeholder="Vertification">
            <img id="captchaImg" src="${pageContext.request.contextPath}/user/user_getCode" onclick="refreshCode()">

        </div>
        <br/>
        <button class="btn btn-primary btn1" type="submit">登陆</button>
        <button class="btn" type="reset">重置</button>
        </div>
	        <div class="aside">
	            <div class="passport-goto"style="text-align: center">没有账号? 免费注册<hr>
	            <a href="${pageContext.request.contextPath }/user/register.jsp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Regist Of Common User</a><br>
	            <a href="${pageContext.request.contextPath }/professor/professor_register.jsp">Regist Of Professor</a><br>
	            <a href="${pageContext.request.contextPath }/company/company_register.jsp">Regist Of Company</a><br>
	        	</div>
	        </div>
        </div>
    </form>
</div>
<div class="footer">
    <p><strong>ZJH</strong> &copy; 2016 All Rights Reserved <a class="links" href="#">进入后台</a></p>
</div>
</body>
</html>