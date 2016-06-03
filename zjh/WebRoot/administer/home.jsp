<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>Administrator Center</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="${pageContext.request.contextPath}/qing_style/css/index.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/qing_style/css/expert.css">
	<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
    
    <script>
        function show(){
            document.getElementById("hi").style.display="block";
        }
        function hide(){
            document.getElementById("hi").style.display="none";
        }
        function userShow(){
            document.getElementById("user_info").style.display="block";
        }
        function userHide(){
            document.getElementById("user_info").style.display="none";
        }
    </script>
</head>
<body>
<div class="headerBar">
    <div class="logoBar">
        <div class="logo">
            <!-- start logo -->
            <a class="logoText fl" href="#" title="专家汇平台">
                <h2>ZJH</h2>
            </a>
        </div>
        <p class="lineText fl">行业专家和短期项目的交流平台</p>
        <div class="loginText fr">
            <span>欢迎您!&nbsp;&nbsp;</span>
            <span class="user_login" onmouseover="userShow()" onmouseout="userHide()">${user.name}&nbsp;&gt</span>
            <ul class="user_info" id="user_info" onmouseover="userShow()"onmouseout="userHide()">
                <li><a href="#">用户管理</a></li>
                <li><a href="#">修改密码</a></li>
                <li><a href="#" onclick="logout()">退出</a></li>
            </ul>
            <div class="message">
                <span class="m_title">消息:</span>
                <a class="m_digital" href="${pageContext.request.contextPath}/message/message_queryMyUnread" id="messageCount"></a>
            </div>
        </div>
    </div>
    </div>
    <div class="navBar">
        <div class="design_class fl">
            <h3 onmouseover="show()"onmouseout="hide()">全部分类&gt</h3>
            <ul class="item" id="hi" onmouseover="show()"onmouseout="hide()">
                <li class="item_list" >
                    <a href="#" target="_blank">logo</a>
                    <a href="#" target="_blank">包装</a>
                    <a href="#" target="_blank">广告</a>
                    <div class="item_detail">
                        <dl>
                            <dt>LOGO设计</dt>
                            <dd><a href="#" target="_blank">企业logo</a>
                                <a href="#" target="_blank">地产logo</a>
                                <a href="#" target="_blank">餐饮logo</a></dd>
                            <dd><a href="#" target="_blank">企业logo</a>
                                <a href="#" target="_blank">地产logo</a>
                                <a href="#" target="_blank">餐饮logo</a></dd>
                        </dl>
                    </div>
                </li >
                <li class="item_list">
                    <a href="#" target="_blank">app</a>
                    <a href="#" target="_blank">微信</a>
                    <a href="#" target="_blank">软件开发</a>
                    <div class="item_detail second">
                        <dl>
                            <dt>APP开发</dt>
                            <dd><a href="#" target="_blank">安卓系统</a>
                                <a href="#" target="_blank">IOS系统</a>
                                <a href="#" target="_blank">餐饮app</a></dd>
                            <dd><a href="#" target="_blank">安卓系统</a>
                                <a href="#" target="_blank">IOS系统</a>
                                <a href="#" target="_blank">餐饮app</a></dd>
                        </dl>
                    </div>
                </li>
                <li class="item_list">
                    <a href="#" target="_blank">logo</a>
                    <a href="#" target="_blank">包装</a>
                    <a href="#" target="_blank">广告</a>
                </li >
                <li class="item_list">
                    <a href="#" target="_blank">app</a>
                    <a href="#" target="_blank">微信</a>
                    <a href="#" target="_blank">软件开发</a>
                </li>
                <li class="item_list">
                    <a href="#" target="_blank">logo</a>
                    <a href="#" target="_blank">包装</a>
                    <a href="#" target="_blank">广告</a>
                </li >
                <li class="item_list">
                    <a href="#" target="_blank">app</a>
                    <a href="#" target="_blank">微信</a>
                    <a href="#" target="_blank">软件开发</a>
                </li>
                <li class="item_list">
                    <a href="#" target="_blank">logo</a>
                    <a href="#" target="_blank">包装</a>
                    <a href="#" target="_blank">广告</a>
                </li >
                <li class="item_list">
                    <a href="#" target="_blank">app</a>
                    <a href="#" target="_blank">微信</a>
                    <a href="#" target="_blank">软件开发</a>
                </li>

            </ul>
        </div>
        <ul class="nav">
            <li  role="presentation"><a href="../index_new.html" target="_blank">首页</a></li>
            <li  role="presentation"><a href="${pageContext.request.contextPath}/consult/consult_unCheckList.action" target="myframe">ConsultCheck</a></li>
            <li  role="presentation"><a href="${pageContext.request.contextPath}/user/user_list" target="myframe">UserList</a></li>
            <li  role="presentation"><a href="${pageContext.request.contextPath}/manage/management.jsp" target="myframe">Viewuser</a></li><%--<%--
            <%--professor审核，列出所有状态为0的professor，审核通过，professor改成1，user之前的状态改成1--%>
            <li  role="presentation"><a href="${pageContext.request.contextPath}/professor/professor_unauditlist" target="myframe">Audit for Professor</a></li>
            <li  role="presentation"><a href="${pageContext.request.contextPath}/company/company_unauditlist" target="myframe">Audit for Company</a></li>
        </ul>
    </div>
</div>
<div>
    <iframe name="myframe" id="iframepage" src="${pageContext.request.contextPath}/welcome.jsp"width="100%" height="500px" frameborder="0"scrolling="no"></iframe>
</div>
</body>
</html>