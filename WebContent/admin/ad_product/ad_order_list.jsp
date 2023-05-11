<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>관리자 - 상품목록</title>

<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/admin/ad_productAdd.css">

<script src="https://kit.fontawesome.com/b435fbc087.js" crossorigin="anonymous"></script>

<!-- 3-3.2. 자바스크립트 이벤트 추가: 햄버거버튼을 클릭하면 아래쪽으로 메뉴가 나오도록 main.js에서 추가-->
<!-- defer : 모든 html 파일을 로딩할 때까지 html이 브라우저창에 표시가 안되는 것을 방지 -->
<script src="${path}/resources/js/customer/main.js" defer></script>

<script type="text/javascript">
<script src="${path}/resources/js/customer/main.js" defer></script>

<script type="text/javascript">
 $(function(){
	  /* 전체 체크 */
	 $("#all_check").change(function() {
     let is_checked = $("#all_check").is(":checked");
     console.log("is_checked" + is_checked);
	
	 $(".season_check").prop("checked", is_checked);
	 });
 });
 
 // 체크박스 상품 구매
 $(function(){
    $("#chkBuy").click(function(){
       if(confirm("해당주문을 승인하시겠습니까?")){
          if($('.season_check').is(':checked')){
             // 체크된 상품번호 담는 배열
             var shopNo = [];
             
             // 체크된 박스 값을 배열에 담는다.
             $("input[name=order]:checked").each(function(i){
            	 shopNo.push($(this).val());
             })
             
             // 담은 체크박스 값들을 해당 경로로 보낸다.
             window.location = "${path}/orderConfirmAction.do?shopNo="+shopNo;
          }
          else{
             alert('승인할 주문을 체크해주세요');
          }
       }
    });
 });
 // 체크박스 상품 삭제
 $(function(){
    $("#chkDelete").click(function(){
       if(confirm("해당주문을 취소하시겠습니까?")){
          if($('.season_check').is(':checked')){
             // 체크된 상품번호 담는 배열
             var shopNo = [];
             
             // 체크된 박스 값을 배열에 담는다.
             $("input[name=order]:checked").each(function(i){
            	 shopNo.push($(this).val());
             })
             
             // 담은 체크박스 값들을 해당 경로로 보낸다.
             window.location = "${path}/orderCancel.do?shopNo="+shopNo;
          }
          else{
             alert('삭제할 주문을 선택하세요');
          }
       }
    });
 });
				

</script>

</head>
<body>

<div class="wrap">
	<!----------- header 시작 ---------->
	<%@ include file="/admin/common/header.jsp" %>
	<!-------------header 끝------------->
	
	  <!---------------------------- 컨텐츠 시작  ---------------------------->
   <div id="container">
      
      <div id="contents">
         <!----------------------- 상단 중앙1 시작 ------------------------>
         <div id="section1">
            <h1 align="center"> 주문목록 </h1>
         </div>
         <!----------------------- 상단 중앙2 시작 ------------------------>
         <div id="section2">
            <!----------------------- 좌측메뉴 시작 ----------------------->
            <div id="left" >
               <div class="left_inside">
               		
               <!----------------------- 좌측메뉴바 시작 ------------------------->
               <%@ include file="/admin/common/leftMenu.jsp" %>
               <!----------------------- 좌측메뉴바 종료 ------------------------->
               </div>
            </div>
            <!----------------------- 좌측 메뉴 종료 ------------------------->
            
            <!----------------------- 우측 메뉴 시작 ------------------------->
           <div id="right">
               <div class="table_div">
               <form method=post name="cartForm">
                 <table>
                    <colgroup>
			                <col style="width: 5%" />
			                <col style="width: 5%" />
			                <col style="width: 10%" />
			                <col style="width: 25%" />
			                <col style="width: 10%" />
			                <col style="width: 5%" />
			                <col style="width: 15%" />
			                <col style="width: 15%" />
			        </colgroup>
			            <tr>          
			               	<th><input type="checkbox" name="product" id="all_check">전체체크</th>   
			                <th>번호</th>
				    		<th>고객ID</th>   
			                <th>상품명</th>
			                <th>상품가격</th>
			                <th>수량</th>
			                <th>주문일</th>
			                <th>주문상태</th>
			            </tr>
			            
			            
			            <c:forEach var="dto" items="${list}">
	                   	<tr class="cart__list__detail">
	                   		<td><input type="checkbox" name="order" class="season_check" value="${dto.shopNo}"></td>
	                    	<td>${dto.shopNo}</td>
	                    	<td>${dto.id}</td>
	                    	<td>${dto.pdName}</td>
	                    	<td>${dto.pdPrice}원</td>
	                    	<td>${dto.buyQuantity}</td>
	                    	<td>${dto.orderDate}</td>
	                    	<td>${dto.deliverySt}</td>
	                    </tr>	
                    	</c:forEach>
                     <tr>
                     	<td colspan="11" align="center">
                     		<!-- 페이징 처리 -->
                     		<!-- 이전 버튼 활성화 여부 -->
                     		<c:if test="${paging.startPage > 10}">
                     			<a href="${path}/orderList.do?pageNum=${paging.prev}">[이전]</a>
                     		</c:if>
                     		
                     		<!-- 페이지번호 처리  -->
                     		<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
                     			<a href="${path}/orderList.do?pageNum=${num}">${num}</a>
                     		</c:forEach>
                     		
                     		<!-- 다음 버튼 활성화 여부 -->
                     		<c:if test="${paging.endPage < paging.pageCount}">
                     			<a href="${path}/orderList.do?pageNum=${paging.next}">[다음]</a>
                     		</c:if>
                     	</td>
                     </tr>
                  </table>
                <!--  <input type="button" value="구매하기" class="btnBuy"  style="display:right; width:100%; text-align:center"> -->
                 </form>
                 <div class="cart__mainbtns" align="center" >
			         <input type="button" id="chkDelete"  class="cartDelete" value="주문취소"> 
			         <input type="button" id="chkBuy"  class="cartBuy" value="주문승인">
			      </div>
               </div>
            </div>
            <!--- 우측 메뉴 종료 --->
         </div>
      </div>
   </div>
   <!--- 컨텐츠 종료  --->
   <br><br><br>
	
	<!------------footer 시작------------->
	<%@ include file="/admin/common/footer.jsp" %>
	<!------------footer 끝-------------->
	
</div>
	


</body>
</html>