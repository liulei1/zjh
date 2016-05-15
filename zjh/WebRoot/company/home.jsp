<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    
    <title>company user</title>
    <link href="${pageContext.request.contextPath }/qing_style/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/qing_style/app.css">
	<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script type="text/javascript">
	    $(function(){
	    	 getMessageCount();
	    });
	    function getMessageCount(){
			var url="${pageContext.request.contextPath}/json/getMyUnreadMessageCount.action";
			var delay=60; // 延时时间,单位秒
			$.post(url, function (message){
				//alert(message.count);
				if(message.count > 0){
					$("#messageCount").html(message.count);
				}
			});
			setTimeout("getMessageCount()",delay*1000);
		}
	    
	    function getMessageCountNow(){
	    	var url="${pageContext.request.contextPath}/json/getMyUnreadMessageCount.action";
			$.post(url, function (message){
				//alert(message.count);
				if(message.count > 0){
					$("#messageCount").html(message.count);
				}else if(message.count == 0){
					$("#messageCount").html("");
				}
			});
	    }
    </script>
</head>

<body class="home-template">
<!-- start header -->
<header class="main-header">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <!-- start logo -->
                <a class="branding" href="" title="专家汇平台">
                    <p>collection of professors platform</p>
                </a>
            </div>
            <div class="col-sm-4">
                <input type="text" class="input-medium search-query">
                <button type="submit" class="btn">search</button>
            </div>
            <p class="text-right">
			    <a href="#">
		          <span class="glyphicon glyphicon-user">&nbsp;${user.name}&nbsp;</span>
		        </a>
    		</p>
            <div class="col-sm-2" >
                <div class="dropdown" id="accountmenu">
                    <ul class="dropdown-menu">
                        <li><a href="#">user management</a></li>
                        <li><a href="#">change password</a></li>
                        <!--li class="divider"></li-->
                        <li><a href="#">sign out</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- end header -->
<!-- start navigation -->
<nav class="main-navigation">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="navbar-header">
                        <span class="nav-toggle-button collapsed" data-toggle="collapse" data-target="#main-menu">
                        <span class="sr-only">Toggle navigation</span>
                        <i class="fa fa-bars"></i>
                        </span>
                </div>
                <div class="collapse navbar-collapse" id="main-menu">
                    <ul class="menu">
                        <li class="nav-current" role="presentation"><a href="/">首页</a></li>
                        <li  role="presentation"><a href="http://wenda.ghostchina.com">论坛</a></li>
                        <li  role="presentation"><a href="/ghost-cheat-sheet/">成功案例</a></li>
                        <li  role="presentation"><a href="http://docs.ghostchina.com/zh/">中文文档</a></li>
                        <li  role="presentation"><a href="/download/">下载</a></li>
                        <li  role="presentation"><a href="/about/">关于</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>
<!-- end navigation -->
<section class="content-wrap">
    <div class="container">
        <div class="row">
            <main class="col-md-2 main-content">
                <div class= "post-content">
                   <ul class="nav nav-pills nav-stacked" style="max-width: 260px;">
					   <li>
					   		<a href="${pageContext.request.contextPath}/project/consult_publish.jsp" target="myframe">发布需求</a>
					   </li>
					   <li>
					   		<a href="${pageContext.request.contextPath}/consult/consult_queryMyConsult" target="myframe">我的需求</a>	
					   </li>
					   <li>
					   		<a href="${pageContext.request.contextPath}/project/project_queryMyProject" target="myframe">我的项目</a>	
					   </li>
					   <li>
					      <a href="${pageContext.request.contextPath}/message/message_queryMyUnread" target="myframe">消息 <span class="badge pull-right" id="messageCount"></span></a>
					   </li>
					   <li>
					      <a href="${pageContext.request.contextPath}/company/professor_search.jsp" target="myframe">查找专家<span class="badge pull-right" id="messageCount"></span></a>
					   </li>
					</ul>
                </div>
            </main>
            <div class="col-md-10 sidebar">
                <iframe name="myframe" id="iframepage" src=" ${pageContext.request.contextPath }/project/consult_publish.jsp" frameborder="0" height="485px" width="100%"></iframe>
            </div>
        </div>
    </div>
</section>
<footer class="main-footer">
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <div class="widget">
                    <h4 class="title">公司介绍</h4>
                    <div class="content recent-post">
                        <div class="recent-single-post">
                            <a href="/ghost-0-7-4-released/" class="post-title">联系方式</a>
                            <div class="date">中国科学技术大学苏州研究院</div>
                        </div>
                        <div class="recent-single-post">
                            <a href="/ghost-0-7-0-released/" class="post-title">隐私保护</a>
                            <div class="date">未经许可不可转载</div>
                        </div>
                        <div class="recent-single-post">
                            <a href="/no-more-jquery-in-ghost_foot-helper-of-ghost-0-7/" class="post-title">信息和资源</a>
                            <div class="date">www.baidu.com</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="widget">
                    <h4 class="title">成功案例</h4>
                    <div class="content tag-cloud">
                        <a href="/tag/release/">新版本发布</a>
                        <a href="/tag/javascript/">JavaScript</a>
                        <a href="/tag/promise/">Promise</a>
                        <a href="/tag/zhuti/">主题</a>
                        <a href="/tag/nodejs/">Node.js</a>
                        <a href="/tag/nginx/">Nginx</a>
                        <a href="/tag/ghost-in-depth/">深度玩转 Ghost</a>
                        <a href="/tag/theme/">Theme</a>
                        <a href="/tag/markdown/">Markdown</a>
                        <a href="/tag/proxy/">反向代理</a>
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="widget">
                    <h4 class="title">社交媒体</h4>
                    <div class="content tag-cloud friend-links">
                        <a href="http://www.bootcss.com" title="Bootstrap中文网" onclick="_hmt.push(['_trackEvent', 'link', 'click', 'bootcsscom'])" target="_blank">微信</a>
                        <hr>
                        <a href="http://www.bootcdn.cn" title="开放CDN服务" onclick="_hmt.push(['_trackEvent', 'link', 'click', 'bootcdncn'])" target="_blank">Facebook</a>
                        <hr>
                        <a href="http://www.jquery123.com/" title="jQuery中文文档" onclick="_hmt.push(['_trackEvent', 'link', 'click', 'jquery123com'])" target="_blank">Twitter</a>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<script type="text/javascript">
    $(document).ready(function () {
        $('.dropdown-toggle').dropdown();
    });
</script>
</body>
</html>

