<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<ul class="menubarLeft">
                  <li><h3> 관리자 메뉴 </h3></li>
                  <hr>
                  <li>
                     <h4> 상품관리 </h4>
                     <ul class="product_menu">
                        <li><a href="${path}/ad_product_list.pd"> 상품목록 </a></li>
                        <li><a href="${path}/ad_product_add.pd"> 상품등록 </a></li>
                        <li><a href="ad_productCategori.html"> 카테고리 </a></li>
                     </ul>
                  </li> <!-- 상품관리 -->
                  
                  <li>
                     <h4> 주문관리 </h4>
                     <ul class="product_menu">
                        <li><a href="${path}/orderList.do"> 주문목록 </a></li>
                        <li><a href="${path}/orderConfirmList.do"> 주문승인 </a></li>
                     </ul>
                  </li> <!-- 주문관리 -->
                  
                  <li><h4><a href="${path}/admin_customerInfo.do "> 회원정보 </a></h4></li>
                  <li><h4><a href="${path}/board_list.bo"> 게시판 </a></h4></li>
                  <li><h4><a href="${path}/sale.do"> 결산 </a></h4></li>
                  <li><h4><a href="${path}/logout.do"> 로그아웃 </a></h4></li>                  
               </ul>   
               
</body>
</html>