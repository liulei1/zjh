<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
   <head>
     <title>consult information</title>
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
					consult list
				</h3>
				<table class="table table-bordered">
					<thead>
						<tr class="warning">
							<th style="text-align:center;">
								title
							</th>
							<th style="text-align:center;">
								money
							</th>
							<th style="text-align:center;">
								category
							</th>
							<th style="text-align:center;">
								state
							</th>
							<th style="text-align:center;">
								review
							</th>
							<th style="text-align:center;">
								reject
							</th>
							<th style="text-align:center;">
								approve
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="consults" var="consult">
							<tr class="info">
							<td align="center">
								${title}
							</td>
							<td align="center">
								${budget}
							</td>
							<td align="center">
								${category}
							</td>
							<td align="center" class="state">
								${state}
							</td>
							<td align="center">
								<s:a action="consult_view" namespace="/consult">
									<s:param name="id" value="id" />
									<button type="button" class="btn btn-info btn-xs">review</button>
								</s:a>
								
							</td>
							<td align="center">
								<s:a action="consult_reject" namespace="/consult">
									<s:param name="id" value="id" />
									<button type="button" class="btn btn-danger btn-xs">reject</button>
								</s:a>
							</td>
							<td align="center">
								<s:a action="consult_allow" namespace="/consult">
									<s:param name="id" value="id" />
									<button type="button" class="btn btn-success btn-xs">approve</button>
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
