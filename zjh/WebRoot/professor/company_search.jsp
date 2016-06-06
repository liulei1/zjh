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
<style>
    .order-btn-group form {
        display: inline-block;
        margin-bottom: 10px;
        padding-top: 30px;
        padding-bottom: 10px;
        text-align: center;
        border-bottom: 1px solid #eee;
        border-top:  1px solid #eee;
    }
</style>

</head>
<body>
<div id="LG" class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <h1 class="text-center">
                <strong>Query Company</strong>
            </h1>
			<div class="order-btn-group">
                <form  class="form-inline col-sm-6 f1" action="${pageContext.request.contextPath }/personSearch/companySearch" method="post">
                    <label class="control-label">FindByVocation:</label>
                    <select class="form-control" name="category" style="width:180px;" id="field"></select>
                    <input type="submit" class="btn btn-info btn-large" value="search">
                </form>
                <form class="form-inline col-sm-6 f2" action="${pageContext.request.contextPath }/personSearch/companySearchByName" method="post">
                  <label class="control-label">FindByName:</label>
                  <input type="text"class="form-control" name="findByName" >
                  <input type="submit"class="btn btn-success btn-large"  value="search">
                </form>
			</div>

            <table class="table table-striped">
                <thead>
	                <tr class="warning">
						<th style="text-align:center;">
							Name
						</th>
						<th style="text-align:center;">
							Email
						</th>
						<th style="text-align:center;">
							Telephone
						</th>
						<th style="text-align:center;">
							Address
						</th>
						<th style="text-align:center;">
							Website
						</th>
						<th style="text-align:center;">
							Points
						</th>
					</tr>
                </thead>
                <tbody>
					<s:iterator value="companys" var="company">
						<tr class="info">
						<td align="center">
							${name}
						</td>
						<td align="center">
							${email}
						</td>
						<td align="center">
							${telephone}
						</td>
						<td align="center">
							${address }
						</td>
						<td align="center">
							<a href="http://${website}" target="_blank">${website}</a>
						</td>
						<td align="center">
							${points}
						</td>
					</tr>
				</s:iterator>
			</tbody>
           </table>
        </div>
    </div>
</div>   
</html>
