<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<c:choose>
	<c:when test="${userinfo eq null }">
		<div class="container">
			<div class="header-top">
				<!-- Navigation -->
				<nav class="navbar navbar-expand-lg navbar-light fixed-top"
					id="mainNav">
					<div class="container">
						<a class="navbar-brand" href="${root}/">MAIN</a>
						<button class="navbar-toggler navbar-toggler-right" type="button"
							data-toggle="collapse" data-target="#navbarResponsive"
							aria-controls="navbarResponsive" aria-expanded="false"
							aria-label="Toggle navigation">
							Menu <i class="fas fa-bars"></i>
						</button>
						<div class="collapse navbar-collapse" id="navbarResponsive">
							<ul class="navbar-nav ml-auto">
								<li class="nav-item"><a class="nav-link" href="#">공지사항</a></li>
								<li class="nav-item"><a class="nav-link"
									href="${root }/house/listHouseDeal">아파트 검색</a></li>
							</ul>
							<ul class="navbar-nav ml-auto">
								<li class="nav-item"><a class="nav-link"
									href="${root}/user/registerForm">SIGN UP</a></li>
								<li class="nav-item"><a class="nav-link" href="${root}/user/loginForm">LOGIN</a>
								</li>
								
							</ul>
						</div>
					</div>
				</nav>
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<nav class="navbar navbar-expand-lg navbar-light fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="${root}/">MAIN</a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link" href="#">공지사항</a></li>
					<li class="nav-item"><a class="nav-link" href="${root}/house/listHouseDeal">아파트 검색</a></li>
					<li class="nav-item"><a class="nav-link" href="#">주변 탐방</a></li>
					<li class="nav-item"><a class="nav-link" href="#">관심 지역 설정</a></li>
					<li class="nav-item"><a class="nav-link" href="#">관심 지역
							둘러보기</a></li>
					<li class="nav-item"><a class="nav-link" href="${root }/qna/moveqna">Q&A</a></li>
				</ul>
				<ul class="navbar-nav ml-auto">

					<li class="nav-item"><a class="nav-link" href="${root}/user/memberinfo">마이페이지</a></li>
					<li class="nav-item"><a class="nav-link" href="#" data-toggle="modal"
						data-target="#myModal">LOGOUT</a></li>
					<c:if test="${userinfo.userId eq 'admin' }">
						<li class="nav-item"><a class="nav-link" href="${root}/user/admin">회원 관리</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<!-- The Modal -->
	<div class="modal fade" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal body -->
				<div class="modal-body text-center"><br><br>로그아웃 하시겠습니까?<br><br></div>
				<!-- Modal footer -->
				<div class="modal-footer">
				<a href="${root}/user/logout" class="btn btn-danger" role="button">LOGOUT</a>
					</div>

			</div>
		</div>
	</div>
	</c:otherwise>
</c:choose>
