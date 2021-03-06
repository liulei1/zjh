<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>services</title>
    <!--link href="qing_style/css/bootstrap.min.css" rel="stylesheet">
    <link href="qing_style/css/navtop_new.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script-->
    <link href="${pageContext.request.contextPath}/qing_style/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/qing_style/css/navtop_new.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
    <style type="text/css">
        /* Custom Styles */
        ul.nav-tabs{
            width: 200px;
            margin-top: 20px;
            border-radius: 4px;
            border: 1px solid #ddd;
            box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
        }
        ul.nav-tabs li{
            margin: 0;
            border-top: 1px solid #ddd;
        }
        ul.nav-tabs li:first-child{
            border-top: none;
        }
        ul.nav-tabs li a{
            margin: 0;
            padding: 8px 16px;
            border-radius: 0;
        }
        ul.nav-tabs li.active a, ul.nav-tabs li.active a:hover{
            color: #fff;
            background: #0088cc;
            border: 1px solid #0088cc;
        }
        ul.nav-tabs li:first-child a{
            border-radius: 4px 4px 0 0;
        }
        ul.nav-tabs li:last-child a{
            border-radius: 0 0 4px 4px;
        }
        ul.nav-tabs.affix{
            top: 30px; /* Set the top position of pinned element */
        }
        .col-xs-9  h3{color:#0088cc;}
        p{ text-indent:2em;}
        .footer{margin-top:50px;}
    </style>
</head>
<body data-spy="scroll" data-target="#myScrollspy">
<div class="navBar">
    <div class="design_class">
        <h3>ZJH</h3>
    </div>
    <ul class="nav_list" >
        <li ><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
        <li ><a href="${pageContext.request.contextPath}/user/introduction.jsp">Services</a></li>
    </ul>
</div>
<div class="container">
    <!--div class="jumbotron">
        <h1>Bootstrap Affix</h1>
    </div-->
    <div class="row">
        <div class="col-xs-12">
        <div class="col-xs-3" id="myScrollspy">
            <ul class="nav nav-tabs nav-stacked" data-spy="affix" data-offset-top="125">
                <li><a href="#section-1">Services</a></li>
                <li><a href="#section-2">Company</a></li>
                <li><a href="#section-3">Telephone</a></li>
                <li><a href="#section-4">Privacy Protection</a></li>
                <li><a href="#section-5">Information and Resources</a></li>
                <li><a href="#section-6">Social Media</a></li>
            </ul>
        </div>
        <div class="col-xs-9">
            <h3 id="section-1">Services</h3>
            <p>Registering a new user. Introduction to business users. Introduction to expert users...</p>
             <!--p>注册新用户. 企业用户入门. 专家用户入门...</p-->
            <hr>
            <h3 id="section-2">Company</h3>
            <p>Nam eget purus nec est consectetur vehicula. Nullam ultrices nisl risus, in viverra libero egestas sit amet. Etiam porttitor dolor non eros pulvinar malesuada. Vestibulum sit amet est mollis nulla tempus aliquet. Praesent luctus hendrerit arcu non laoreet. Morbi consequat placerat magna, ac ornare odio sagittis sed. Donec vitae ullamcorper purus. Vivamus non metus ac justo porta volutpat.</p>
            <hr>
            <h3 id="section-3">Telephone</h3>
            <p>  Phone: 010-87654321.</p>
            <p>  Telephone: 13646189229.</p>
            <hr>
            <h3 id="section-4">Privacy Protection</h3>
            <p>Users of personal information. Personal information. Information security. Users' rights.
                The principle of limited use. Disclosure of personal information. Cookies, liability...</p>
            <!--p>使用者非个人化信息. 个人资料. 信息安全. 用户权利. 限制利用原则. 个人资料之披露. Cookies.免责...</p-->
            <hr>
            <h3 id="section-5">Information and Resources</h3>
            <p>Nam eget purus nec est consectetur vehicula. Nullam ultrices nisl risus, in viverra libero egestas sit amet. Etiam porttitor dolor non eros pulvinar malesuada. Vestibulum sit amet est mollis nulla tempus aliquet. Praesent luctus hendrerit arcu non laoreet. Morbi consequat placerat magna, ac ornare odio sagittis sed. Donec vitae ullamcorper purus. Vivamus non metus ac justo porta volutpat.</p>
            <hr>
            <h3 id="section-6">Social Media</h3>
            <p> WeChat: ZJH_WeChat</p>
            <p> FaceBook: ZJH_FaceBook</p>
            <p> Twitter: ZJH_Twitter</p>
        </div>
        </div>
    </div>
</div>
<div class="footer">
    <p><strong>ZJH</strong> &copy; 2016 All Rights Reserved <a class="links" href="#">进入后台</a></p>
</div>
</body>
</html>