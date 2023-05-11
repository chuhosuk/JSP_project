<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>고객 - 환불요청</title>

<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/admin/ad_productAdd.css">

<script src="https://kit.fontawesome.com/b435fbc087.js" crossorigin="anonymous"></script>

<!-- 3-3.2. 자바스크립트 이벤트 추가: 햄버거버튼을 클릭하면 아래쪽으로 메뉴가 나오도록 main.js에서 추가-->
<!-- defer : 모든 html 파일을 로딩할 때까지 html이 브라우저창에 표시가 안되는 것을 방지 -->
<script src="${path}/resources/js/customer/main.js" defer></script>

</head>
<body>

<div class="wrap">
	<!----------- header 시작 ---------->
	<%@ include file="/admin/common/header.jsp" %>
	<!-------------header 끝------------->
	
	  <!---- 컨텐츠 시작  ---->
   <div id="container">
      
      <div id="contents">
         <!---- 상단 중앙1 시작 --->
         <div id="section1">
            <h1 align="center"> 구매추가 </h1>
         </div>
         <!---- 상단 중앙2 시작 --->
         <div id="section2">
            <!--- 좌측메뉴 시작 --->
            <div id="left">
               <div class="left_inside">
               	
               <!---- 좌측메뉴바 시작 ---->
               	<%@ include file="/admin/common/leftMenu.jsp" %>
               <!--- 좌측메뉴바 종료 --->
               </div>
            </div>
            <!--- 좌측 메뉴 종료 --->
            
            <!-- 우측 내용 시작 -->
            	<div id="right">
            		<div class="table_div">
            		
            			<!-- insert 실패 -->
            			<c:if test="${refundCnt == 0}">
            				<script type="text/javascript">
            					alert("환불요청실패.");
            					window.location="${path}/myOrder.do";
            				</script>
            			</c:if>
            			
            			<!-- insert 성공 -->
            			<c:if test="${refundCnt != 0}">
            				<script type="text/javascript">
            					alert("주문취소완료. 환불은 결제일로부터 3~7일정도 소요됩니다.");
            					window.location="${path}/myOrder.do";
            				</script>
            			</c:if>
            			
            		</div>
            	</div>
            <!-- 우측 메뉴 종료 -->
            
         </div>
      </div>
   </div>
   <!---- 컨텐츠 종료  ---->
   <br><br><br>
	
	<!------------footer 시작------------->
	<%@ include file="/admin/common/footer.jsp" %>
	<!------------footer 끝-------------->
	
</div>
	


</body>
</html>