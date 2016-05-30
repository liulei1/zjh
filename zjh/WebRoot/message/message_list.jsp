<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
   <head>
     <title>消息列表</title>
     
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <!-- 引入 Bootstrap -->
	 <link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
	 <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	 <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
	 <script type="text/javascript">
	 	// 标记消息为已读
		function read(){
			var id = $("#readMessageId").val();
			$.post("${pageContext.request.contextPath}/message/message_haveReaded",{id:id},function(data){
				parent.getMessageCountNow();
				document.open("text/html","replace");
				document.writeln(data);
				document.close();
			});
		}
		
		// 将消息内容显示到模态框	
		function viewMessage(target){
			var id = $(target).children("input[name='id']").val();
			var content = $(target).children("input[name='content']").val();
			var title = $(target).children("input[name='title']").val();
			$("#modalTitle").html(title);
			$("#modalContent").html(content);
			$("#readMessageId").val(id);
		}
	 </script>
</head>
   <body>
    <div id="LG" class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="text-center">
					New Messages
				</h3>
				<table class="table table-bordered table-hover">
					<s:if test="messages.size() == 0">
						<div style="color:blue">no message now</div>
					</s:if>
					<s:iterator value="messages" var="message">
						<tr onclick="viewMessage(this)" data-toggle="modal" data-target="#myModal">
							<s:hidden name="id" value="%{id}"></s:hidden>
							<s:hidden name="content" value="%{content}"></s:hidden>
							<s:hidden name="title" value="%{title}"></s:hidden>
							<td>
								${title}
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" 
	               data-dismiss="modal" aria-hidden="true">
	                  &times;
	            </button>
	            <h4 class="modal-title" style="text-align: center" id="modalTitle">
	             	  消息标题
	            </h4>
	         </div>
	         <div class="modal-body" id="modalContent">
	           		消息内容
	         </div>
	         <s:hidden name="id" id="readMessageId" value=""></s:hidden>
	         <div class="modal-footer">
	         	<button type="button" class="btn btn-primary" data-dismiss="modal">
	              	 标记未读
	            </button>
	            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="read()">
	            	关闭
	            </button>
	         </div>
	      </div>
		</div>
	</div>
  </body>
</html>
