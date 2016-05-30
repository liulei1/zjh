<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>专家汇平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="qing_style/css/index.css" rel="stylesheet">
    <link rel="stylesheet" href="qing_style/css/app_new.css">
    <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
</head>
<script type="text/javascript">
	$(function(){
		getRecommendProfessor();
		getRecommendConsult();
	});
	
	//获取推荐的专家
	function getRecommendProfessor(){
		$.post("${pageContext.request.contextPath}/json/recommendProfessor.action",function(data){
			var html='';
			$.each(data.professors,function(index,professor){
				html+='<li><div class="pd"><a><img src="'+professor.img+'" onerror="javascript:this.src=\'qing_style/img/list/04.jpg\'"></a></div>';
				var url = "${pageContext.request.contextPath}/professor/professor_viewProfessorInfoById?id="+professor.id;
				html+='<div class="pd_name"><a href='+url+'>'+professor.name+'</a></div>';
				html+='<div><span>Points:</span>&nbsp;&nbsp;<span class="money">'+professor.points+'</span></div>';
				html+='<div><span>Feild:&nbsp;'+professor.field+'</span></div>';
				html+='<div><span>Address:'+professor.address+'</span></div>'; 
				html+='<div><span>Education:'+professor.education+'</span></div></li>';
			});
			$('#recommendProfessor').html(html);
		});
	}
	
	//获取推荐的需求
	function getRecommendConsult(){
		var pic = new Array("qing_style/img/list/05.jpg","qing_style/img/list/06.jpg","qing_style/img/list/07.jpg","qing_style/img/list/08.jpg","qing_style/img/list/09.jpg");
		var length = pic.length;
		$.post("${pageContext.request.contextPath}/json/getRecommendConsult.action",function(data){
			var html='';
			var i = 0;
			$.each(data.consults,function(index,consult){
				html+='<li>';
				var url = pic[i++%length];
				html+='<div class="pd"><a><img src='+url+'></a></div>';
				html+='<div class="pd_name"><a href="#">'+consult.title+'</a></div>';
				html+='<div><span class="money">Budget:¥'+consult.budget+'</span></div>';
				html+='<div><span>Release:'+consult.release_date+'</span></div>';
				html+='<div><span>End Time:'+consult.deadline+'</span></div>';
				html+='<div><span>Feild:'+consult.category+'</span></div>';
				html+='</li>';
			});
			$('#recommendConsult').html(html);
		});
	}
	
</script>
<body>
<div class="headerBar">
    <div class="logoBar">
        <div class="logo">
            <!-- start logo -->
            <h2>
	            <a class="logoText fl" href="${pageContext.request.contextPath}" title="专家汇平台">
	            	ZJH
	            </a>
            </h2>
        </div>
        <p class="lineText fl">行业专家和短期项目的交流平台</p>

        <div class="loginText fr">
            <span>欢迎来到zjh!</span>
            <a class="linkText" href="user/login.jsp">[登录]</a>
            <a class="linkText" href="user/register_new.html" target="_blank">[免费注册]</a>
        </div>
    </div>
    <div class="navBar">
        <div class="design_class fl">
            <h3>全部分类</h3>
            <ul class="item">
                <li class="item_list">
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
                <li  class="active" role="presentation"><a href="/">首页</a></li>
                <li  role="presentation"><a href="http://wenda.ghostchina.com">人才大厅</a></li>
                <li  role="presentation"><a href="/ghost-cheat-sheet/">需求大厅</a></li>
                <li  role="presentation"><a href="http://docs.ghostchina.com/zh/">服务介绍</a></li>
            </ul>
    </div>
</div>
<div class="mainBody">
<div class="bannerBar">
    <ul class="imgBox">
        <li><a href="#"><img src="qing_style/img/banner/05.jpg"></a></li>
        <li><a href="#"><img src="qing_style/img/banner/02.jpg"></a></li>
        <li><a href="#"><img src="qing_style/img/banner/06.jpg"></a></li>
    </ul>
</div>
</div>
<div>
<div class="title">
    <span style="float:left" onclick="getRecommendProfessor()">推荐专家</span>
    <a class="more" href="#"style="float:right">更多&gt;&gt;</a>
</div>
    <div class="content">
        <ul class="list" id="recommendProfessor"></ul>
    </div>
</div>
<div>
    <div class="title">
        <span style="float:left" onclick="getRecommendConsult()">推荐需求</span>
        <a class="more" href="#"style="float:right">更多&gt;&gt;</a>
    </div>
    <div class="content">
        <ul class="list" id="recommendConsult">
            <%-- <li>
                <div class="pd"><a><img src="qing_style/img/list/05.jpg"></a></div>
                <div><span class="money">¥8000/套</span>&nbsp;&nbsp;<span> 成交数量:0</span></div>
                <div class="pd_name"><a href="#">【APP开发】安卓|IOS|开发代做|APP设计...</a></div>
                <div><span>专业APP实体公司</span></div>
                <div><span>广东 东莞</span></div>
            </li>
            <li>
                <div class="pd"><a><img src="qing_style/img/list/06.jpg"></a></div>
                <div><span class="money">¥8000/套</span>&nbsp;&nbsp;<span> 成交数量:0</span></div>
                <div class="pd_name"><a href="#">【APP开发】安卓|IOS|开发代做|APP设计...</a></div>
                <div><span>专业APP实体公司</span></div>
                <div><span>广东 东莞</span></div>
            </li>
            <li>
            <div class="pd"><a><img src="qing_style/img/list/07.jpg"></a></div>
                <div><span class="money">¥8000/套</span>&nbsp;&nbsp;<span> 成交数量:0</span></div>
                <div class="pd_name"><a href="#">【APP开发】安卓|IOS|开发代做|APP设计...</a></div>
            <div><span>专业APP实体公司</span></div>
            <div><span>广东 东莞</span></div>
           </li>
            <li>
                <div class="pd"><a><img src="qing_style/img/list/08.jpg"></a></div>
                <div><span class="money">¥8000/套</span>&nbsp;&nbsp;<span> 成交数量:0</span></div>
                <div class="pd_name"><a href="#">【APP开发】安卓|IOS|开发代做|APP设计...</a></div>
                <div><span>专业APP实体公司</span></div>
                <div><span>广东 东莞</span></div>
            </li>
            <li>
                <div class="pd"><a><img src="qing_style/img/list/09.jpg"></a></div>
                <div><span class="money">¥8000/套</span>&nbsp;&nbsp;<span> 成交数量:0</span></div>
                <div class="pd_name"><a href="#">【APP开发】安卓|IOS|开发代做|APP设计...</a></div>
                <div><span>专业APP实体公司</span></div>
                <div><span>广东 东莞</span></div>
            </li> --%>
        </ul>
    </div>
</div>
<div class="footer">
     <a href="#">公司介绍</a>
    <a href="#">|联系方式</a>
    <a href="#">|成功案例</a>
    <a href="#">|隐私保护</a>
    <a href="#">|信息和资源</a>
    <a href="#">|社交媒体（微信、FaceBook, Twitter）</a></div>
</body>
</html>