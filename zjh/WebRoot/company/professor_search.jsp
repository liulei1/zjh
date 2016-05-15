<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'professor_search.jsp' starting page</title>
   
    <!-- 引入 Bootstrap -->
	 <link href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
	 <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>
	 <script src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/jquery/jquery-1.4.2.js"></script>
   
<script type="text/javascript">
	$(function() {
		$.post("${pageContext.request.contextPath}/json/listVocation.action",function(data){
			var html='<select name="category"><option selected="selected">--请选择领域--</option>';
			$.each(data.vocationList,function(index,context){
				html+='<option value="'+context.id+'">'+context.name+'</option>';
			});
			html+='</select>';
			$('#field').html(html);
		});

	});
	
</script>


  </head>
   
  <body>
  <form action="${pageContext.request.contextPath }/personSearch/professorSearch" method="post">
   find by vocation:<div id="field"></div>
  <input type="submit" value="search" >
  </form>
  
  find by name:<form action="${pageContext.request.contextPath }/personSearch/professorSearchByName" method="post">
  <input type="text" name="findByName" >
  <input type="submit" value="search">
  </form>
  
 <div id="LG" class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="text-center">
					professor list
				</h3>
				<table class="table table-bordered">
					<thead>
						<tr class="warning">
							<th style="text-align:center;">
								name
							</th>
							<th style="text-align:center;">
								sex
							</th>
							<th style="text-align:center;">
								mail
							</th>
							<th style="text-align:center;">
								telephone
							</th>
							<th style="text-align:center;">
								scheme view
							</th>
							<th style="text-align:center;">
								project view
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="professors" var="professor">
							<tr class="info">
							<td align="center">
								${name}
							</td>
							<td align="center">
								${sex}
							</td>
							<td align="center">
								${email}
							</td>
							<td align="center" class="state">
								${telephone}
							</td>
							<td align="center">
								<s:a action="scheme_queryMyScheme" namespace="/scheme">
									<s:param name="professor.id" value="id" />
									<button type="button" class="btn btn-info btn-xs">scheme view</button>
								</s:a>
								
							</td>
							<td align="center">
								<s:a action="project_queryMyProject" namespace="/project">
									<s:param name="professor_id" value="id" />
									<button type="button" class="btn btn-success btn-xs">project view</button>
								</s:a>
							</td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
  </form>
  </body>
</html>
