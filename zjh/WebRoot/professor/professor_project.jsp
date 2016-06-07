<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
   <head>
     <title>专家的项目列表</title>
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <!-- 引入 Bootstrap -->
	 <link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
	 <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	 <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
	 <script type="text/javascript">
		$(function(){
			$(".state").each(function(i){
				var tag = $(".state")[i].innerHTML;
				var para = $($(".state")[i]).next("td").next("td");
				var state;
				if(tag == 4){
					state = "进行中";
					// 禁用 评价 按钮
					para.children(".evaluate").attr("class","btn btn-info btn-xs disabled evaluate");
					// 不显示  完成  按钮
					para.children(".complete").attr("class","btn btn-danger btn-xs hidden complete");
				}else if(tag == 5){
					state = "用户评价";
					// 显示 评价
					para.children(".evaluate").attr("class","btn btn-success btn-xs evaluate");
					// 不显示 完成
					para.children(".complete").attr("class","btn btn-danger btn-xs hidden complete");
				}else if(tag == 6){
					state = "完成";
					// 不显示 评价
					para.children(".evaluate").attr("class","btn btn-success btn-xs hidden evaluate");
					// 显示 项目完成,按钮不可点击
					para.children(".complete").attr("class","btn btn-danger btn-xs disabled complete");
				}
				$(".state")[i].innerHTML = state;
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
			$.post("${pageContext.request.contextPath}/project/project_queryMyProject",{pageIndex:pageIndex},function(data){
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
					Project List
				</h3>
				<table class="table table-bordered">
					<thead>
						<tr class="warning">
							<th style="text-align:center;">
								Title
							</th>
							<th style="text-align:center;">
								Document
							</th>
							<th style="text-align:center;">
								Money
							</th>
							<th style="text-align:center;">
								Begain Time
							</th>
							<th style="text-align:center;">
								End Time
							</th>
							<th style="text-align:center;">
								State
							</th>
							<th style="text-align:center;">
								Scheme
							</th>
							<th style="text-align:center;">
								Operation
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
								<s:a action="evaluate_publishView" namespace="/evaluate" cssClass="btn btn-success btn-xs disabled evaluate">
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