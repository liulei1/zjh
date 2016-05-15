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
			var delay=4; // 延时时间,单位秒
			$.post(url, function (message){
				alert(message.count);
				//if(message.count > 0){
				//	alert(message.count);
				//	$("#messageCount").html(message.count);
				//}
			});
			setTimeout("getMessageCount()",delay*1000);
		}
    </script>
</head>

<body>
nihao .....
</body>
</html>

