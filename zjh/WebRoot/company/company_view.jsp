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
			var html='<select name="field" onload="vocationSelect()">';
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
</script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>专家用户个人信息</title>
<link href="${pageContext.request.contextPath }/qing_style/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/qing_style/load.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container" style="margin-top:10px">
		<div align="center">
			<span id="result"></span>
			<span>
				<font color="red">
					<b><%=request.getAttribute("result")==null?"":request.getAttribute("result")%></b>
				</font>
			</span>
		</div>
		<s:form cssClass="form-signin" action="company_updateProfessorInfo" namespace="/company" theme="simple" method="post" cssStyle="max-height: 400px;"
			onsubmit="return check();">
			<div>
				<div class="main">
					<div>
						<p align="center">UserInfo</p>
						<hr class="hr1" />
					</div>
					<table>
						<tr>
							<td>Username</td>
							<td>
								<input type="text" name="name" value="${model.name}" id="add_username">
							</td>
						</tr>
						<tr>
							<td>Email</td>
							<td>
								<input type="text" name="email" value="${model.email}">
							</td>
						</tr>
						<tr>
							<td>Telephone</td>
							<td>
								<input type="text" name="telephone" value="${model.telephone}">
							</td>
						</tr>
						<tr>
							<td>Address</td>
							<td>
								<input type="text" name="address" value="${model.address}">
							</td>
						</tr>
						<tr>
							<td>Website</td>
							<td>
								<input type="text" name="website" value="${model.website}">
							</td>
						</tr>
						<tr>
							<td>Sex</td>
							<td>
								<s:radio list="{'male','female'}" name="sex" align="right" value="%{model.sex}"/>
							</td>
						</tr>
						<tr>
							<td>Vocation</td>
							<td>
								<div id="field" ></div>
							</td>
						</tr>
						<tr>
							<td>Annotation</td>
							<td>
								<textarea rows="2" name="annotation" cols="30">${model.annotation}</textarea>
							</td>
						</tr>
					</table>
					<br>
					<button class="btn btn-info" type="submit">submit</button>
					<button class="btn btn-warning" onclick="javascript:history.go(-1);">back</button>
				</div>
			</div>
		</s:form>
			<div class="aside">
				
	        </div>
	</div>
</body>
</html>