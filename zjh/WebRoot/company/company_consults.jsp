<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
   <head>
     <title>My consult</title>
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <!-- 引入 Bootstrap -->
	 <link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
	 <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	 <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
	 
	 <script type="text/javascript">
		$(function(){
			$(".state").each(function(i){
				var tag = $(".state")[i].innerHTML;
				var state;
				if(tag == 0){
					state = "待审核";
				}else if(tag == 1){
					state = "通过";
				}else if(tag == 2){
					state = "拒绝";
				}else {
					state = "已立项";
				}
				$(".state")[i].innerHTML = state;
			});
			
			$(".pagination").jBootstrapPage({
	            pageSize : "${pageSize}", // 每页显示的数据条数
	            total : "${total}", //总共数据条数
	            maxPageButton: 2, // 显示多少个按钮
	            onPageClicked: function(obj, pageIndex) {
					
					$.post("${pageContext.request.contextPath}/json/queryMyConsultReturnJson",{pageIndex:pageIndex+1},function(data){
							var html = "<thead><tr class='warning'><th style='text-align:center;'>标题</th><th style='text-align:center;'>酬金</th><th style='text-align:center;'>类别</th><th style='text-align:center;'>状态</th><th style='text-align:center;'>查看</th><th style='text-align:center;'>查看方案</th></tr></thead>";
							html += "<tbody>";
							$.each(data.consults, function(index, consult){
					        	var state;
					        	if(consult.state == 0){
					        		state = "待审核";
								}else if(consult.state == 1){
									state = "通过";
								}else if(consult.state == 2){
									state = "拒绝";
								}
								html +='<tr class="info"><td align="center"><a href="/zjh/consult/consult_view.action?id="'+ consult.id +'">'+consult.title+'</a></td><td align="center">'+consult.budget+'</td><td align="center">'+consult.category+'</td><td align="center" class="state">'+state+'</td><td align="center"><a href="/zjh/consult/consult_view.action?id="><button type="button" class="btn btn-info btn-xs">查看</button></a></td><td align="center"><a href="/zjh/scheme/scheme_findConsultSchemes.action?cons_id="><button type="button" class="btn btn-success btn-xs">方案</button></a></td></tr>';
					        });
				             html += '</tbody>';
				             $('#consult_table').html(html);
					});
				}
        	});
		});
		
		//翻页
		function changePage(operate){
			var pageIndex = "${pageIndex}";
			var pageCount = "${pageCount}";
			if(operate == "last"){
				pageIndex = parseInt(pageIndex) - 1;
			}else if(operate == "next"){
				pageIndex = parseInt(pageIndex) + 1;
			}
			if(pageIndex < 1){
				pageIndex = 1;
			}else if(pageIndex > pageCount){
				pageIndex = pageCount;
			}
			$.post("${pageContext.request.contextPath}/consult/consult_queryMyConsult",{pageIndex:pageIndex},function(data){
					document.open("text/html","replace");
					document.writeln(data);
					document.close();
			});
			
		}
	</script>
   </head>
   <body>
    <div id="LG" class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="text-center">
					My Consults
				</h3>
				<table class="table table-bordered" id="consult_table">
					<thead>
						<tr class="warning">
							<th style="text-align:center;">
								Title
							</th>
							<th style="text-align:center;">
								Money
							</th>
							<th style="text-align:center;">
								Vocation
							</th>
							<th style="text-align:center;">
								State
							</th>
							<th style="text-align:center;">
								View
							</th>
							<th style="text-align:center;">
								Scheme View
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="consults" var="consult">
							<tr class="info">
							<td align="center">
								<s:a action="consult_view" namespace="/consult">
									<s:param name="id" value="id" />
									${title}
								</s:a>
							</td>
							<td align="center">
								${budget}
							</td>
							<td align="center">
								${category}
								<%-- <s:if test="category.equals('')">
									---
								</s:if>
								<s:else>
									${category}
								</s:else> --%>
							</td>
							<td align="center" class="state">
								${state}
							</td>
							<td align="center">
								<s:a action="consult_view" namespace="/consult">
									<s:param name="id" value="id" />
									<button type="button" class="btn btn-info btn-xs">details</button>
								</s:a>
								
							</td>
							<td align="center">
								<s:a action="scheme_findConsultSchemes" namespace="/scheme">
									<s:param name="cons_id" value="id" />
									<button type="button" class="btn btn-success btn-xs">schemes</button>
								</s:a>
							</td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
				<p id="pageIndex" style="font-size:20px;font-weight:bold;color:blue;margin-left:150px;"></p>
				<!-- 分页 -->
				<ul class="pager">
			      <li id="lastpage"><a href="#" onclick="changePage('last')">上一页</a></li>
			      <li id="lastpage"><a href="#" onclick="changePage('next')">下一页</a></li>
			    </ul>
			    <span>第&nbsp;${pageIndex}/${pageCount}&nbsp;页&nbsp;</span>
			</div>
		</div>
	</div>
   </body>
</html>
