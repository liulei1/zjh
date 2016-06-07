<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imgRootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>专家汇平台首页</title>
    
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="${pageContext.request.contextPath}/qing_style/css/index.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/qing_style/css/app_new.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/qing_style/css/expert.css">
	 <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
</head>
<script type="text/javascript">
	if (window != top)
			top.location.href = location.href;

	// 用户下拉菜单
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
        
	$(function(){
		getRecommendProfessor();
		getRecommendConsult();
	});
	
	//获取推荐的专家
	function getRecommendProfessor(){
		$.post("${pageContext.request.contextPath}/json/recommendProfessor.action",function(data){
			var html='';
			$.each(data,function(index,professor){
				//style="background: url('<%=imgRootPath%>${model.image}') no-repeat;background-size:100% 100%;">
				html+='<li><div class="pd"><a><img src=<%=imgRootPath%>'+professor.image+' onerror="javascript:this.src=\'qing_style/img/list/04.jpg\'"></a></div>';
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
			$.each(data,function(index,consult){
				html+='<li>';
				var url = pic[i++%length];
				html+='<div class="pd"><a><img src='+url+'></a></div>';
				html+='<div class="pd_name"><a href="#">'+consult.title+'</a></div>';
				html+='<div><span class="money">Budget:¥'+consult.budget+'</span></div>';
				html+='<div><span>Release:'+consult.release_date+'</span></div>';
				var deadtime="-";
				if(consult.deadline != null){
					deadtime=consult.deadline;
				}
				html+='<div><span>End Time:'+deadtime+'</span></div>';
				html+='<div><span>Feild:'+consult.category+'</span></div>';
				html+='</li>';
			});
			$('#recommendConsult').html(html);
		});
	}
	
	function logout(){
    	var r=confirm("Confirm Logout？");
    	if(r){
    		var url="${pageContext.request.contextPath}/user/user_logout.action";
    		$.post(url,function(){
    			location.reload(true);
    		});
    	}
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
        	<%if(application.getAttribute("user")==null){%>
        		<span>Welcome to ZJH!</span>
	            <a class="linkText" href="user/login.jsp">[Login]</a>
	            <a class="linkText" href="${pageContext.request.contextPath}/index_register.jsp" target="_blank">[Regist]</a>
	        <%}else { %>
            	<span class="user_login" onmouseover="userShow()"onmouseout="userHide()">Welcome !&nbsp;${user.name}&gt;</span>
	            <ul class="user_info" id="user_info" onmouseover="userShow()" onmouseout="userHide()">
	            	<li><a href="${pageContext.request.contextPath}/user/user_toUserCenter?usertype=${user.usertype}">User Center</a></li>
	                <li ><a href="#" data-toggle="modal" data-target="#myModal">Logout</a></li>
	            </ul>
	         <%} %>
        </div>
    </div>
    <div class="navBar">
        <div class="design_class fl">
            <h3 onmouseover="show()"onmouseout="hide()">全部分类</h3>
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
            <li  role="presentation"><a href="${pageContext.request.contextPath}/professor/professor_getProfessorWithPage" target="_blank">人才大厅</a></li>
            <li  role="presentation"><a href="${pageContext.request.contextPath}/consult/consult_allowList" target="_blank">需求大厅</a></li>
            <li  role="presentation"><a href="${pageContext.request.contextPath}/user/introduction.jsp">服务介绍</a></li>
        </ul>
       </div>
</div>
<div class="mainBody">
<div class="bannerBar">
    <ul class="imgBox">
        <li><a href="#"><img src="${pageContext.request.contextPath}/qing_style/img/banner/05.jpg"></a></li>
        <li><a href="#"><img src="${pageContext.request.contextPath}/qing_style/img/banner/02.jpg"></a></li>
        <li><a href="#"><img src="${pageContext.request.contextPath}/qing_style/img/banner/06.jpg"></a></li>
    </ul>
</div>
</div>
<div>
<div class="title">
    <span style="float:left">推荐专家</span>
    <a class="more" onclick="getRecommendProfessor()" style="float:right">更多&gt;&gt;</a>
</div>
    <div class="content">
        <ul class="list" id="recommendProfessor"></ul>
    </div>
</div>
<div>
    <div class="title">
        <span style="float:left">推荐需求</span>
        <a class="more" href="getRecommendConsult()"style="float:right">更多&gt;&gt;</a>
    </div>
    <div class="content">
        <ul class="list" id="recommendConsult">
        </ul>
    </div>
</div>
<div class="footer">
     <a href="#">公司介绍</a>
    <a href="#">|联系方式</a>
    <a href="#">|成功案例</a>
    <a href="#">|隐私保护</a>
    <a href="#">|信息和资源</a>
    <a href="#">|社交媒体（微信、FaceBook, Twitter）</a>
</div>

<!-- 模态框（Modal） -->
<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title" id="myModalLabel">
              Message
            </h4>
         </div>
         <div class="modal-body">
            Confirm Logout?
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal" onclick="logout()" >logout
            </button>
            <button type="button" class="btn btn-primary" data-dismiss="modal">
             	close
            </button>
         </div>
      </div>/.modal-content
   </div>/.modal-dialog
</div>/.modal -->
</body>
</html>
