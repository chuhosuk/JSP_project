<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>header</title>


</head>
<body>

	<!----------- header 시작 ---------->
	<nav class="navbar">
		<ul class="navbar_title">
			<li><img src="${path}/resources/images/main/크림로고.jpeg" width="50%"></li>
		</ul>
		
		<ul class="navbar_menu">
			<li><a href="${path}/main.do">HOME</a></li>
			<li><a href="${path}/category.do">카테고리</a></li>
			<li><a href="${path}/board_list.bo">Q&A</a></li>
		</ul>
	
		<ul class="navbar_icons">
			<li><i class="fa-brands fa-twitter"></i></li> 
			<li><i class="fa-brands fa-facebook"></i></li>
		
		<!-- 로그인 안한 경우 -->
		<c:if test="${sessionScope.sessionID == null}">
			<li><a href="${path}/login.do">LOGIN</a></li>
			<li><a href="${path}/join.do">JOIN</a></li>
			<li><a href="${path}/login.do"><i class="fa-solid fa-cart-plus"></a></i></li><!-- 장바구니 상세 -->
			<li><a href="${path}/customerInfo.do"><i class="fa-solid fa-user"></i></a></li>
		</c:if>
		
		<!-- 로그인 한경우 -->
		<c:if test="${sessionScope.sessionID != null}">
			<li><span style="color:pink">${sessionScope.sessionID}니미</span></li>
			<li><a href="${path}/logout.do">LOGOUT</a></li>
			<li><a href="${path}/cartList.do"><i class="fa-solid fa-cart-plus"></a></i></li><!-- 마이페이지 : 회원수정 -->
			<li><a href="${path}/customerInfo.do"><i class="fa-solid fa-user"></i></a></li> <!-- 회원수정 : 회원탈퇴 -->
		</c:if>
		
		</ul>
		
	
	<!-- 반응형웹 - 1. 햄버거 버튼:https://kit.fontawesome.com =>검색 : 햄버거 >bars선택 -->
	
		
		<a href="#"	class="navbar_toggleBtn">
			<i class="fa fa-bars"></i> </a>	<!--햄버거  -->
			
	</nav>
	<!-------------header 끝------------->