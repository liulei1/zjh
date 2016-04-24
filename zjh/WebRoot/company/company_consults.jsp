<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
   <head>
     <title>我的需求</title>
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
				}
				$(".state")[i].innerHTML = state;
			});
		});
	</script>
   </head>
   <body>
    <div id="LG" class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="text-center">
					需求列表
				</h3>
				<table class="table table-bordered">
					<thead>
						<tr class="warning">
							<th style="text-align:center;">
								标题
							</th>
							<th style="text-align:center;">
								酬金
							</th>
							<th style="text-align:center;">
								类别
							</th>
							<th style="text-align:center;">
								状态
							</th>
							<th style="text-align:center;">
								查看
							</th>
							<th style="text-align:center;">
								查看方案
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
									<button type="button" class="btn btn-info btn-xs">查看</button>
								</s:a>
								
							</td>
							<td align="center">
								<s:a action="scheme_findConsultSchemes" namespace="/scheme">
									<s:param name="cons_id" value="id" />
									<button type="button" class="btn btn-success btn-xs">方案</button>
								</s:a>
							</td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
				<div align="center">
					<ul class="pagination pagination-sm pagination-centered">
					  <li><a href="#">&laquo;</a></li>
					  <li><a href="#">1</a></li>
					  <li><a href="#">2</a></li>
					  <li><a href="#">3</a></li>
					  <li><a href="#">4</a></li>
					  <li><a href="#">5</a></li>
					  <li><a href="#">&raquo;</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
   </body>
</html>