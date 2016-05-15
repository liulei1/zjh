<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
   <head>
     <title>my project</title>
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <!-- 引入 Bootstrap -->
	 <link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
	 <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	 <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
	 <script type="text/javascript">
		$(function(){
			$(".state").each(function(i){
				var tag = $(".state")[i].innerHTML;
				//var para = $($(".state")[i]).next("td").children("a");
				var para = $($(".state")[i]).next("td");
				var state;
				if(tag == 4){
					state = "进行中";
					// 不显示 评价 按钮
					para.children(".evaluate").attr("class","btn btn-info btn-xs hidden evaluate");
					// 显示  完成  按钮
					para.children(".complete").attr("class","btn btn-danger btn-xs complete");
				}else if(tag == 5){
					state = "企业评价";
					// 显示 评价
					para.children(".evaluate").attr("class","btn btn-success btn-xs evaluate");
					// 不显示 完成
					para.children(".complete").attr("class","btn btn-danger btn-xs hidden complete");
				}else if(tag == 6){
					state = "专家评价";
					// 显示 评价
					para.children(".evaluate").attr("class","btn btn-success btn-xs evaluate");
					// 不显示 完成
					para.children(".complete").attr("class","btn btn-danger btn-xs hidden complete");
				}else if(tag == 7){
					state = "完成";
					// 不显示 评价
					para.children(".evaluate").attr("class","btn btn-success btn-xs hidden evaluate");
					// 显示 项目完成,按钮不可点击
					para.children(".complete").attr("class","btn btn-danger btn-xs disabled complete");
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
					project list
				</h3>
				<table class="table table-bordered">
					<thead>
						<tr class="warning">
							<th style="text-align:center;">
								title
							</th>
							<th style="text-align:center;">
								consult document
							</th>
							<th style="text-align:center;">
								money
							</th>
							<th style="text-align:center;">
								begain time
							</th>
							<th style="text-align:center;">
								end time
							</th>
							<th style="text-align:center;">
								speed
							</th>
							<th style="text-align:center;">
								view project
							</th>
							<th style="text-align:center;">
								operation
							</th>
						</tr>
					</thead>
				<tbody>
					<s:iterator value="projects" var="project">
						<tr class="info">
							<td align="center" id="1">
								${consult.title}
							</td>
							<td align="center">
								${consult.fileName}
							</td>
							<td align="center">
								${consult.budget}
							</td>
							<td align="center">
								${start_date}
							</td>
							<td align="center">
								${end_date}
							</td>
							<td align="center" class="state">
								${current_state}
							</td>
							<td align="center">
								<s:a action="scheme_findProjectScheme" namespace="/scheme" cssClass="btn btn-primary btn-xs">
									<s:param name="id" value="scm_id"></s:param>
									view
								</s:a>
							</td>
							<td align="center">
								<s:a action="project_complete" namespace="/project" cssClass="btn btn-success btn-xs hidden complete">
									<s:param name="id" value="id"></s:param>
									<!-- <button type="button" class="btn btn-success btn-xs hidden complete">完成</button> -->
									complicated
								</s:a>
								<s:a action="evaluate_publishView" namespace="/evaluate" cssClass="btn btn-success btn-xs disabled  evaluate">
									<s:param name="proj_id" value="id"></s:param>
									evaluate
									<!-- <button type="button" class="btn btn-success btn-xs disabled hidden evaluate">评价</button> -->
								</s:a>
								<div class="tag"></div>
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