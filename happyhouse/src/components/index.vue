<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="header.jsp"%>
</head>

<body>
	<!-- Navigation -->
	<%@ include file="navibar.jsp"%>

	<!-- Page Header -->
	<%@ include file="pageheader.jsp"%>
		
	<!-- Main Content -->
	<div class="container">
		<img src="${root}/img/map.png" style="width: 1100px;">
	</div>

<%@ include file="footer.jsp"%>
</body>

</html>
