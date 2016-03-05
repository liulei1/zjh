<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专家用户注册</title>

</head>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
<script type="text/javascript">
	$(function() {
		$.post("${pageContext.request.contextPath}/listVocation.action",function(data){
			var html='<select name="category"><option selected="selected">--请选择领域--</option>';
			$.each(data.vocationList,function(index,context){
				html+='<option value="'+context.id+'">'+context.name+'</option>';
			});
			html+='</select>';
			$('#field').html(html);
		});

		$("#add_username").blur(function(){
			$.post("${pageContext.request.contextPath}/user/checkProfessorName.action",{"name":$(this).val()},function(data){
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


<body>
	<h1 align="center">专家用户注册</h1>
	<div align="center">
	<font color="red">
	<s:actionerror/>
	
	</font>
	
	<s:form action="professor_register" namespace="/token" theme="simple" method="post" onsubmit="return check()" >
	<table>
		<s:token></s:token>
		<s:fielderror></s:fielderror>
		<tr>
			<td align="right">用户名</td>
			<td><s:textfield name="name" id="add_username"></s:textfield> </td>
			<td><span id="result"></span></td>
		</tr>
		<tr>
			<td>邮箱</td>
			<td><s:textfield name="email" ></s:textfield> </td>
			
		</tr>
		<tr>
			<td>密码</td>
			<td><s:textfield name="password" ></s:textfield> </td>
			<td></td>
		</tr>
		<tr>
			<td>重复密码</td>
			<td><s:textfield name="repassword" ></s:textfield> </td>
			<td></td>
		</tr>
		<tr>
			<td>真实姓名</td>
			<td><s:textfield name="real_name" ></s:textfield> </td>
			<td></td>
		</tr>
		<tr>
			<td>联系方式</td>
			<td><s:textfield name="telephone" ></s:textfield> </td>
			<td></td>
		</tr>
		<tr>
			<td>地址</td>
			<td><s:textfield name="address" ></s:textfield> </td>
			<td></td>
		</tr>
		<tr>
			<td>所属领域</td>
			<td><div id="field"></div> </td>
			<td></td>
		</tr>
		<tr>
			<td>网址</td>
			<td><s:textfield name="website" ></s:textfield> </td>
			<td></td>
		</tr>
		<tr>
			<td>地址</td>
			<td><s:textfield name="address" ></s:textfield> </td>
			<td></td>
		</tr>
		<tr>
			<td>身份证号</td>
			<td><s:textfield name="identity" ></s:textfield> </td>
			<td></td>
		</tr>
		<tr>
			<td>个人简介</td>
			<td><s:textfield name="introduction" ></s:textfield> </td>
			<td></td>
		</tr>
		<tr>
			<td>成就</td>
			<td><s:textfield name="identity" ></s:textfield> </td>
			<td></td>
		</tr>
		<tr>
			<td align="center"><input type="submit" value="提交"></td>
			<td align="center"><input type="reset" value="重置"></td>
			<td></td>
		</tr>
	</table>	
	</s:form>
	</div>
</body>
</html>