<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <jsp:forward page="/user/login.jsp"></jsp:forward> --%>
<a href="${pageContext.request.contextPath}/user/login.jsp">登录</a><br>
<a href="${pageContext.request.contextPath}/project/consult_publish.jsp">发布需求</a><br>
<a href="${pageContext.request.contextPath}/consult/consult_list.action">需求列表</a><br>
<a href="${pageContext.request.contextPath}/consult/consult_unCheckList.action">未审核的需求</a><br>
<a href="${pageContext.request.contextPath}/consult/consult_allowList.action">审核通过的需求</a><br>
