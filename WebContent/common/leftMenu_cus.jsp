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
                  <li><h3> 고객 메뉴 </h3></li>
                  <hr>
                 <ul> 
                  <li><h4><a href="${path}/customerInfo.do"> 나의정보 </a></h4></li>
                  <li><h4><a href="${path}/board_list.bo"> 게시판 </a></h4></li>
                  <li><h4><a href="${path}/main.do"> 쇼핑하기 </a></li>
                  <li><h4><a href="${path}/cartList.do"> my카트 </a></h4></li>
                  <li><h4><a href="${path}/myOrder.do">my주문</a></li>
                  <li><h4><a href="${path}/logout.do"> 로그아웃 </a></h4></li>                  
               </ul>   
               
</body>
</html>