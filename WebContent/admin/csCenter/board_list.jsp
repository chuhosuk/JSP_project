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
<link rel="stylesheet" href="${path}/resources/css/admin/ad_productList.css">

<script src="https://kit.fontawesome.com/d967f76dff.js" crossorigin="anonymous"></script>

<!-- 3-3.2. 자바스크립트 이벤트 추가: 햄버거버튼을 클릭하면 아래쪽으로 메뉴가 나오도록 main.js에서 추가-->
<!-- defer : 모든 html 파일을 로딩할 때까지 html이 브라우저창에 표시가 안되는 것을 방지 -->
<script src="${path}/resources/js/customer/main.js" defer></script>

<script>
$(function() {
	$("#btnInsert").click(function() {
		location.href="${path}/board_insert.bo"
	});
	
	
	
});


</script>


</head>
<body>

<div class="wrap">
	<!-------- header 시작 ------->
	<%@ include file="/admin/common/header.jsp" %>
	<!----------header 끝------->
	
	  <!------- 컨텐츠 시작  --------->
   <div id="container">
      
      <div id="contents">
         <!------- 상단 중앙1 시작 ------->
         <div id="section1">
            <h1 align="center"> 게시판 </h1>
         </div>
         <!------- 상단 중앙2 시작 -------->
         <div id="section2">
            <!----- 좌측메뉴 시작 -------->
            <div id="left">
            	<div class="left_inside">
    				<%@ include file="/admin/common/leftMenu.jsp" %>
    			</div>
    		</div>        
            <!------ 좌측 메뉴 종료 ------->
            
            <!-------- 우측 메뉴 시작 -------->
            <div id="right">
               <div class="table_div">
                  <table>
                     <tr>
                        <th style="width: 5%">글번호</th>
                        <th style="width: 10%">작성자</th>
                        <th style="width: 15%">글제목</th>
                        <th style="width: 10%">작성일</th>
                        <th style="width: 10%">조회수</th>
                     </tr>
                     
                     
                     <!-- 게시글이 있으면 -->
                     <c:forEach var="dto" items="${list}">
	                     <tr>
	                        <td>${dto.num}</td>
	                        <td>${dto.writer}</td>
	                        <td>
	                        	<a href="${path}/board_detailAction.bo?num=${dto.num}">${dto.title}</a>
	                        	
                                 <!-- 댓글 갯수  -->
                                 <c:if test="${dto.comment_count > 0}">
                                    <span style="color:red">[ ${dto.comment_count} ]</span>
                                 </c:if>
	                        	
	                        </td>
	                        <td>${dto.regDate}</td>
	                        <td>${dto.readCnt}</td>
	                     </tr>
                     </c:forEach>
                     
                     <tr>
                     	<td colspan="5" align="center">
                     		<!-- 페이징 처리 -->
                     		<!-- 이전 버튼 활성화 여부 -->
                     		<c:if test="${paging.startPage > 10}">
                     			<a href="${path}/board_list.bo?pageNum=${paging.prev}">[이전]</a>
                     		</c:if>
                     		
                     		<!-- 페이지번호 처리  -->
                     		<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
                     			<a href="${path}/board_list.bo?pageNum=${num}">${num}</a>
                     		</c:forEach>
                     		
                     		<!-- 다음 버튼 활성화 여부 -->
                     		<c:if test="${paging.endPage < paging.pageCount}">
                     			<a href="${path}/board_list.bo?pageNum=${paging.next}">[다음]</a>
                     		</c:if>
                     	</td>
                     </tr>
                     <tr>
                     	<td colspan="5" align="right">
                     		<input type="button" class="inputButton" value="글쓰기" id="btnInsert">
                     	</td>
                     </tr>
                     
                  </table>
               </div>
            </div>
            <!----------------------- 우측 메뉴 종료 ------------------------->
         </div>
      </div>
   </div>
   <!---------------------------- 컨텐츠 종료  ---------------------------->
   <br><br><br>

	
	<!------------footer 시작------------->
	<%@ include file="/admin/common/footer.jsp" %>
	<!------------footer 끝-------------->
	
</div>
	


</body>
</html>