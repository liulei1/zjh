<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
   <head>
     <title>个人消息</title>
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <!-- 引入 Bootstrap -->
	 <link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
	 <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	 <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
	 <script type="text/javascript">
		$(function() {
			/* $(".view").bind("click", function(event) {
				var r = confirm("是将这条消息设为已读吗？");
				if(r){
					var td = event.target;
					var id = $(td).prev("input[name='id']").val();
					$.post("${pageContext.request.contextPath}/message/message_haveReaded",{id:id},function(data){
						document.write(data);
						//parent.getMessageCountNow();
					});
				}
			}); */
			
		});
		function read(target){
				var r = confirm("是将这条消息设为已读吗？");
				if(r){
					var td = $(target).children("td");
					var id = $(td).prev("input[name='id']").val();
					$.post("${pageContext.request.contextPath}/message/message_haveReaded",{id:id},function(data){
						parent.getMessageCountNow();
						document.open("text/html","replace");
						document.writeln(data);
						document.close();
					});
				}
			}
	 </script>
</head>
   <body>
    <div id="LG" class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="text-center">
					我的消息
				</h3>
				<table class="table table-hover">
					<s:iterator value="messages" var="message">
						<tr onclick="read(this)">
							<s:hidden name="id" value="%{id}"></s:hidden>
							<td>
								${content}
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
	</div>
   </body>
</html>
