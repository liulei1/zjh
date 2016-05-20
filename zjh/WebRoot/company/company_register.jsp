<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>

<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript">
	$(function() {
		$.post("${pageContext.request.contextPath}/json/listVocation.action",function(data){
			var html='<select name="category"><option selected="selected">--请选择领域--</option>';
			$.each(data.vocationList,function(index,context){
				html+='<option value="'+context.id+'">'+context.name+'</option>';
			});
			html+='</select>';
			$('#field').html(html);
		});

		$("#add_username").blur(function(){
			$.post("${pageContext.request.contextPath}/user/checkCompanyName.action",{"name":$(this).val()},function(data){
				if(data.nameExsit){
					// 用户名已经存在
					$("#result").html("<font color='red' class='check'>用户名已经存在</font>");
				}else{
					// 用户名不存在
					$("#result").html("<font color='green' class='check'>用户名不存在，可以使用</font>");
				}
			}); 
		}); 
	});
	function check(){
		var flag=$(".check").html();
		if(flag=="用户名已经存在"){
			return false;
		}else if(flag==null){
			return false;
		}else{
			return true;
		}
	}
	
</script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>regist</title>
<link href="${pageContext.request.contextPath }/qing_style/bootstrap.min.css"
rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/qing_style/load.css">


<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</head>

<body class="">
	<div class="navbar">
		<a class="brand" href="index.html"><span class="first">Collection of professors platform</span>
		</a>
	</div>
	<div class="container" style="margin-top:10px">
		<div align="center">
			<span id="result"></span><font color="red"> <s:actionerror />
				<s:fielderror /> </font>
		</div>
		<s:form cssClass="form-signin" action="company_register"  namespace="/token" theme="simple" method="post" cssStyle="max-height: 400px;"
			onsubmit="return check();">


        </div-->
			<div class="content">
				<div class="main">
					<div class="">
				<p align="center">Regist</p>
						<hr class="hr1" />
					</div>
					<s:textfield name="name" id="add_username"
						class="input-block-level" placeholder="用户名" />

					<s:textfield name="email" class="input-block-level"
						placeholder="邮箱" />
					<s:textfield type="password" name="password"
						class="input-block-level" placeholder="密码" />
					<s:textfield type="password" name="repassword"
						class="input-block-level" placeholder="重复密码" />
					<br>
					<s:textfield name="real_name" placeholder="真实姓名"></s:textfield>
					<br>
					<s:textfield name="telephone" placeholder="联系方式"></s:textfield>
					<br>
					<s:textfield name="address" placeholder="地址"></s:textfield>
					<br>
					<s:textfield name="website" placeholder="网址"></s:textfield>
					<br>
					性别:<s:radio list="{'男','女'}" name="sex" id="female" align="right"/>
					<br>
					<div id="field"></div>
					<button class="btn btn-warning btn1" type="submit">regist</button>
					<button class="btn" type="reset">reset</button>
				</div>
				<div class="aside">
					<div class="passport-goto" style="text-align: center">
						haven a account? <a href="${pageContext.request.contextPath }/user/login.jsp ">login now</a>
					</div>
				</div>
			</div>
		</s:form>
	</div>


</body>
</html>