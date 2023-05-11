<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file= "/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>main</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="#">

<script src="https://kit.fontawesome.com/b435fbc087.js"crossorigin="anonymous"></script>
<script src="${path}/resources/js/customer/main.js" defer></script>

<script>
$(function() {
	// 게시글 삭제버튼 클릭
	$("button[name='order']").click(function() {
		confirm("바로 구매하시겠습니까?");
		
		if(${sessionScope.sessionID == null}){
			alert("로그인이 필요합니다");
			window.location="${path}/login.do";
		}
		
		else{
			var dtoPdno = ${dto.pdno}
			alert("구매해주셔서 감사합니다.");
			document.mainForm.action="${path}/buyAction.do?pdNo=dtoPdno";
			document.mainForm.submit();
		}
	});
	
}); 
</script>
<!-- $("input[name='product']:checked"); -->
</head>
<body>

<div class="wrap">
	<!----------- header 시작 ---------->
	<%@ include file="/common/header.jsp" %>

	<!-------------header 끝------------->
	
	<!-- 컨텐츠 시작  -->
	<br><br><br>

		<main class="mt-3"> <!-- margin top=3 -->
		
		<div class="container"> <!-- 가운데로 컨텐츠를 몰아준다. -->
		<h3 style="font-weight:bold">카테고리</h3>	
		<form method=post name="mainForm">
		<input type="hidden" name="hiddenPdNo" value="${dto.pdNo}">
			<div class="row "> <!-- row: 한행을 추가한다는 의미, 12개 그리드가 만들어진다. g-3 : 글로벌 3의 여백-->
			<c:forEach var="dto" items="${list}">
			<input type="hidden">
				<div class="col-xl-3 col-lg-4 col-md-5 g-3">
					<div class="card" style="width: 18rem;">
					
					  <img src="${dto.pdImg}" class="card-img-top"><!-- components > card -->
					  	<div class="card-body">
						    <h5 class="card-title" style="text-decoration:underline; font-weight:bold">${dto.pdBrand}</h5>
						    <h5 class="text" style="color:grey">${dto.pdName}</h5>
						    <h5 class="text-dark" style="font-weight:bold">${dto.pdPrice}원</h5>
						    <div class="d-flex justify-content-between align-items-center">
						    
						    	<div class="btn-group" role="group">
								<%-- <button type="button" class="btn btn-sm btn-outline-secondary" onclick="location.href='${path}/customer_detail.do'">장바구니 담기</button> --%>
								<button type="button" class="btn btn-sm btn-outline-secondary" onclick="window.location='${path}/customer_detail.do?pdNo=${dto.pdNo}&pageNum=${paging.pageNum}'">제품상세</button>
								<input type="hidden" name="hiddenPdNo" id=hiddenPdNo" value="${dto.pdNo}">
							<%-- <button type="button" class="btn btn-sm btn-outline-secondary" name="order" value="${dto.pdNo}" onclick="function()">구매하기</button> --%>
								</div>
							</div>	
						</div>
	  				</div>
	  				
				</div>
			
				</c:forEach>
			</div> <!-- 카드1개 -->
			</form>
				
			<div class="container" >
				<div class="col-xl-3 col-lg-4 col-md-5 g-3">
					<table>
						<tr>
							<td>
						      	<c:if test="${paging.startPage > 10}">
						   			<a href="${path}/ad_product_list.pd?pageNum=${paging.prev}">[이전]</a>
						   		</c:if>
						   		<!-- 페이지번호 처리  -->
						   		<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
						   			<a href="${path}/ad_product_list.pd?pageNum=${num}" style="color:black; align:center">${num}</a>
						   		</c:forEach>
						   		<!-- 다음 버튼 활성화 여부 -->
						   		<c:if test="${paging.endPage < paging.pageCount}">
						   			<a href="${path}/ad_product_list.pd?pageNum=${paging.next}">[다음]</a>
						   		</c:if>
						   	</td>
					   	</tr>	
				    </table>
			    </div>
    		</div>
    
    
		</div>
	</main>	
</div>

 


 	
	<!-- 컨텐츠 끝 -->
	<br><br><br><br><br>
	
	<!------------footer 시작------------->
	<%@ include file="/common/footer.jsp" %>
	<!------------footer 끝-------------->


</body>
</html>