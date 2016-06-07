<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String imgRootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>专家用户个人信息</title>
<link href="${pageContext.request.contextPath}/qing_style/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/qing_style/css/navtop_new.css" rel="stylesheet">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery/ajaxfileupload.js"></script>

<script type="text/javascript">
	$(function() {
		$.post("${pageContext.request.contextPath}/json/listVocation.action",function(data){
			var html='<select name="field" class="form-control" onload="vocationSelect()">';
			$.each(data.vocationList,function(index,context){
				html+='<option value="'+context.id+'">'+context.name+'</option>';
			});
			html+='</select>';
			$('#field').html(html);
		});

		function vocationSelect(){
			var vocation = "${model.field}";
			$("select [name='category']").val(vocation);
		}

		$("#add_username").blur(function(){
			var name = "${model.name}";
			if(name != $(this).val()){
				$.post("${pageContext.request.contextPath}/json/checkCompanyName.action",{"name":$(this).val()},function(data){
					if(data.nameExsit){
						// 用户名已经存在
						$("#result").html("<font color='red' class='check'>用户名已经存在</font>");
					}else{
						// 用户名不存在
						$("#result").html("<font color='green' class='check'>用户名不存在，可以使用</font>");
					}
				});
			}
		}); 
	}); 
	function check(){
		var flag=$(".check").html();
		if(flag=="用户名已经存在"){
			return false;
		}else {
			return true;
		}
	}
	function passwordConfirm(){
		var newPwd = $("#newPassword").val();
		var rpt = $("#repeatPassword").val();
		if(rpt == newPwd){
			return true;
		}else{
			alert("两次输入不一致");
			return false;
		}
	}
</script>
<style>
 .fileInputContainer{
        height:256px;
        background:url(${pageContext.request.contextPath}/qing_style/img/list/04.jpg) no-repeat;
        background-size:100% 100%;
        position:relative;
        width: 256px;
        border-style:solid;
		border-width:1px;
    }
    .fileInput{
        height:256px;
        overflow: hidden;
        font-size: 300px;
        position:absolute;
        outline: medium none;
        right:0;
        top:0;
        opacity: 0;
        filter:alpha(opacity=0);
        cursor:pointer;
    }
</style>
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
	<span>欢迎您,${user.name}用户</span>
</div>
<h1>用户信息管理</h1>
<ul id="myTab" class="nav nav-tabs">
	<li class="active"><a href="#home" data-toggle="tab">用户管理</a></li>
	<li><a href="#password" data-toggle="tab">修改密码</a></li>
	<li><a href="#image" data-toggle="tab">上传头像</a></li>
</ul>
<div id="myTabContent" class="tab-content">
	<div class="container tab-pane fade in active" style="margin-top:10px" id="home">
		<div class="row">
			<div class="col-xs-12">
				<div class="login-wrapper">
					<div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">
						<div class="form-center">
		<s:form cssClass="form-horizontal" action="company_updateProfessorInfo" namespace="/company" theme="simple" method="post" cssStyle="max-height: 400px;"
			onsubmit="return check();">
				<div class="form-group">
					<h1 align="center">Change User Information</h1>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Username</label>
					<div class="col-sm-4">
						<input type="text"  class="form-control" name="name" value="${model.name}" id="add_username">
					</div>
					<div class="col-sm-5">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Email</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="email" value="${model.email}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Telephone</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="telephone" value="${model.telephone}">
					</div>
				</div>
				<div class="form-group">
					<label  class="col-sm-4 control-label">Address</label>
					<div class="col-sm-4">
						<input type="text"class="form-control"  name="address" value="${model.address}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Website</label>
					<div class="col-sm-4">
						<input type="text" class="form-control"  name="website" value="${model.website}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Sex</label>
					<div class="col-sm-4">
						<s:radio list="{'male','female'}" name="sex" align="right" value="%{model.sex}"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Vocation</label>
					<div class="col-sm-4" id="field"></div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Annotation</label>
					<div class="col-sm-4">
						<textarea rows="2" class="form-control" name="annotation" cols="30">${model.annotation}</textarea>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-7 form-btn-group">
						<s:a action="consult_allow" namespace="/consult">
							<s:param name="id" value="id"/>
							<button class="btn btn-info pull-left" type="submit">submit</button>
						</s:a>
						<s:a action="consult_reject" namespace="/consult">
							<s:param name="id" value="id"/>
							<button class="btn btn-success pull-left" onclick="javascript:history.go(-1);">back</button>
						</s:a>
						<!--a href="#" onClick="javascript:history.go(-1)" ><span class="glyphicon glyphicon-circle-arrow-left">返回</span></a-->                        </div>
				</div>
		</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改密码页面 -->
	<div class=" container tab-pane fade" id="password">
		<h1 align="center">Change Password</h1>
		<div class="row">
			<div class="col-xs-12">
				<div class="login-wrapper">
					<div class="col-md-offset-2 col-xs-12 col-sm-10 col-md-8">
						<div class="form-center">

			<!--span>
                <font color="red">
                    <b><%=request.getAttribute("result")==null?"":request.getAttribute("result")%></b>
                </font>
            </span-->
			<s:form cssClass="form-horizontal" onsubmit="return passwordConfirm();" action="company_changePassword" namespace="/company" theme="simple" method="post">
				<div class="form-group">
					<label class="col-sm-4 control-label">Password</label>
					<div class="col-sm-4">
						<input type="password" class="form-control"  name="password" id="oldPassword" required="required">
					</div>
				</div>
				<div class="form-group">
					<label  class="col-sm-4 control-label">New Password</label>
					<div class="col-sm-4">
						<input type="password" class="form-control" name="newPassword" id="newPassword" required="required">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Repeat Password</label>
					<div class="col-sm-4">
						<input type="password" class="form-control"  id="repeatPassword" required="required">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-7 form-btn-group">
						<button class="btn btn-success btn-large pull-left" type="submit">submit</button>
						<button class="btn btn-info btn-large pull-left" onclick="javascript:history.go(-1);">back</button>
					</div>
				</div>
			</s:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<script type="text/javascript">
	//上传图片
	function uploadFile(id){
		//判断图片格式是否正确
		var fileName = $("#img").val();
		fileName = fileName.toLowerCase();
		var fileType = fileName.substring(fileName.lastIndexOf(".")+1);
		if(fileType != "jpg" && fileType != "png" && fileType!="jpeg" && fileType != "gif" && fileType != "bmp"){
			alert('上传文件格式错误，全部附件为.jpg、.png、.jpeg、.gif 或 .bmp格式的图片！');
       	 	return false;
		}
	
		$.ajaxFileUpload(
        {
            url: '${pageContext.request.contextPath}/json/uploadCompanyImage', //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: id, //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            data: {"fileSize":"10"}, //将图片的上限大小传入后台 
            success: function (data)  //服务器成功响应处理函数
            {
            	if(data.imgPath != null){
                	$("#imgView").css("background","url(" +"<%=imgRootPath%>"+data.imgPath+") no-repeat ");
					$("#imgView").css("background-size","100% 100%");
            	}else{
            		alert("Upload Fail, Please try again!");
            	}
            },
            error: function (data)//服务器响应失败处理函数
            {
                alert("Upload Fail");
            }
        });
        return false;
	}
</script>
	
	<div class="tab-pane fade" id="image">
		<div class="fileInputContainer" id="imgView"><input class="fileInput" id="img" type="file" name="file" onChange="uploadFile('img')">
			<p style="margin-top: 235px;text-align: center;">点击上传头像<p>
		</div>
	</div>
</div>
</body>
</html>