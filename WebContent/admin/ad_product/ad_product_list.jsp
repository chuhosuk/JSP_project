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
	$(function() {
		// 게시글 삭제버튼 클릭
		$(".btnDelete").click(function() {
			
			if(confirm("삭제하시겠습니까?")){
			
			document.productListForm.action="${path}/ad_product_deleteAction.pd";
			document.productListForm.submit();
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
            <h1 align="center"> 상품목록 </h1>
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
               <form method=post name="productListForm">
                  <table>
                     <tr>
                        <th style="width: 5%">번호</th>
                        <th style="width: 8%">브랜드</th>
                        <th style="width: 15%">상품명</th>
                        <th style="width: 10%">상품이미지</th>
                        <th style="width: 10%">카테고리</th>
                        <th style="width: 10%">상품가격</th>
                        <th style="width: 10%">등록수량</th>
                        <th style="width: 10%">상태</th>
                        <th style="width: 10%">등록일</th>
                        <th style="width: 5%">수정</th>
                        <th style="width: 5%">삭제</th>
                     </tr>
                     
                     
                     <!-- 상품이 있으면 -->
                     <c:forEach var="dto" items="${list}">
	                     <tr>
	                        <td>${dto.pdNo}</td>
	                        <td>${dto.pdBrand}</td>
	                        <td>${dto.pdName}</td>
	                        <td><img src="${dto.pdImg}" width="100"></td><!-- upload 폴더를 새로고침해야 깨진 이미지가 보임 -->
	                        <td>${dto.pdCategory}</td>
	                        <td>${dto.pdPrice}원</td>
	                        <td>${dto.pdQuantity}</td>
	                        <td>${dto.pdStatus}</td>
	                        <td>${dto.pdIndate}</td>
	                        <td>
	                        	<center>
	                        	<input type="button" value="수정" onclick="window.location='${path}/ad_product_detailAction.pd?pdNo=${dto.pdNo}&pageNum=${paging.pageNum}'">
	                        	</center>
	                        </td>
	                        <td>
	                        	<center>
	                        	<input type="hidden" name="pdNo" value="${dto.pdNo}" >
	                        	<%-- <input type="button" value="삭제" onclick="window.location='${path}/ad_product_deleteAction.pd?pdNo=${dto.pdNo}'"> --%>
	                        	<input type="button" value="삭제" class="btnDelete">
	                        	</center>
	                        </td>
	                     </tr>
                     </c:forEach>
                     
                     <tr>
                     	<td colspan="11" align="center">
                     		<!-- 페이징 처리 -->
                     		<!-- 이전 버튼 활성화 여부 -->
                     		<c:if test="${paging.startPage > 10}">
                     			<a href="${path}/ad_product_list.pd?pageNum=${paging.prev}">[이전]</a>
                     		</c:if>
                     		
                     		<!-- 페이지번호 처리  -->
                     		<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
                     			<a href="${path}/ad_product_list.pd?pageNum=${num}">${num}</a>
                     		</c:forEach>
                     		
                     		<!-- 다음 버튼 활성화 여부 -->
                     		<c:if test="${paging.endPage < paging.pageCount}">
                     			<a href="${path}/ad_product_list.pd?pageNum=${paging.next}">[다음]</a>
                     		</c:if>
                     	</td>
                     </tr>
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
	<%@ include file="/admin/common/footer.jsp" %>
	<!------------footer 끝-------------->
	
</div>
	


</body>
</html>