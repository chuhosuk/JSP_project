<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>장바구니</title>

<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/admin/ad_productAdd.css">

<script src="https://kit.fontawesome.com/b435fbc087.js" crossorigin="anonymous"></script>

<!-- 3-3.2. 자바스크립트 이벤트 추가: 햄버거버튼을 클릭하면 아래쪽으로 메뉴가 나오도록 main.js에서 추가-->
<!-- defer : 모든 html 파일을 로딩할 때까지 html이 브라우저창에 표시가 안되는 것을 방지 -->
<script src="${path}/resources/js/customer/main.js" defer></script>

<script type="text/javascript">
	$(function() {
		// 게시글 삭제버튼 클릭
		$(".btnDelete").click(function() {
			
			if(confirm("삭제하시겠습니까?")){
			
			document.cartForm.action="${path}/cartDeleteAction.do";
			document.cartForm.submit();
			}
		});
		
	});
</script>

</head>
<body>

<div class="wrap">
	<!----------- header 시작 ---------->
	<%@ include file="/common/header.jsp" %>
	<!-------------header 끝------------->
	
	  <!---------------------------- 컨텐츠 시작  ---------------------------->
   <div id="container">
      
      <div id="contents">
         <!----------------------- 상단 중앙1 시작 ------------------------>
         <div id="section1">
            <h1 align="center"> 나의정보 </h1>
         </div>
         <!----------------------- 상단 중앙2 시작 ------------------------>
         <div id="section2">
            <!----------------------- 좌측메뉴 시작 ----------------------->
            <div id="left" >
               <div class="left_inside">
               		
               <!----------------------- 좌측메뉴바 시작 ------------------------->
               <%@ include file="/common/leftMenu_cus.jsp" %>
               <!----------------------- 좌측메뉴바 종료 ------------------------->
               </div>
            </div>
            <!----------------------- 좌측 메뉴 종료 ------------------------->
            
            <!----------------------- 우측 메뉴 시작 ------------------------->
            <div id="right">
               <div class="table_div">
               <form method=post name="infoForm">
                  <table class="table table-bordered" id="cartList">
                  
			            <colgroup>
			                <col style="width: 10%" />
			                <col style="width: 10%" />
			                <col style="width: 10%" />
			                <col style="width: 30%" />
			                <col style="width: 20%" />
				    		<col style="width: 20%" />
			            </colgroup>
			            <tr>
			                <th>ID명</th>
				    		<th>성함</th>   
			                <th>생년월일</th>
			                <th>주소</th>
			                <th>연락처</th>
                        	<th>가입일</th>
			            </tr>
			            
			            
			            <c:forEach var="dto" items="${list}">
	                   	<tr>
	                    	<td>${dto.id}</td>
	                    	<td>${dto.name}</td>
	                    	<td>${dto.birthday}</td>
	                    	<td>${dto.address}</td>
	                    	<td>${dto.hp}</td>
	                    	<td>${dto.regDate}</td>
	                    </tr>	
                    	</c:forEach>
                     	
                  </table>
                 </form>
                 
                 
               </div>
            </div>
            <!--- 우측 메뉴 종료 --->
         </div>
      </div>
   </div>
   <!--- 컨텐츠 종료  --->
   <br><br><br>
	
	<!------------footer 시작------------->
	<%@ include file="/common/footer.jsp" %>
	<!------------footer 끝-------------->
	
</div>
	


</body>
</html>