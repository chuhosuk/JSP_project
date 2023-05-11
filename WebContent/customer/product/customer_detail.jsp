<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
<link rel="stylesheet" href="${path}/resources/css/common/main.css">

<script src="https://kit.fontawesome.com/b435fbc087.js"crossorigin="anonymous"></script>
<script src="${path}/resources/js/customer/main.js" defer></script>
<script>
$(function() {
	// 게시글 삭제버튼 클릭
	$("#order").click(function() {
		confirm("바로 구매하시겠습니까?");
		
		if(${sessionScope.sessionID == null}){
			alert("로그인이 필요합니다");
			window.location="${path}/login.do";
		}
		
		else{
			alert("구매해주셔서 감사합니다.");
			document.cartForm.action="${path}/buyAction.do?pdNo=${dto.pdNo}";
			document.cartForm.submit();
		}
	});
	
	 $("#cartAdd").click(function() {
	      
	     const ctQuantity = $("#ctQuantity").val();
	          
	      if(ctQuantity==""){
	          alert("주문수량을 입력하세요");
	          $("#ctQuantity").focus();
	          return false;
	       }
	      
	      document.cartForm.action="${path}/cartAddAction.do?pdNo=${dto.pdNo}&ctQuantity="+ctQuantity;
	      document.cartForm.submit();
	   });
	
	
});

</script>


</head>
<body>

<div class="wrap">
	<!----------- header 시작 ---------->
	<%@ include file="/common/header.jsp" %>

	<!-------------header 끝------------->
	
	<!-- 컨텐츠 시작  -->
	
 	<main class="mt-3">
	<h1 align="center">상품정보</h1>
	<div class="container">
	<!-- ../../resources/images/product/product1.jpg -->
	<form method="post" name="cartForm">
		<div class="row">
				<div class="col-md-5">  <!-- 왼쪽에 5개의 그리드 => 상품이미지 --> <!-- Components > Carousel > With indicators-->
					<div class="row" style="width:500px; align:center; height:500px"><img src="${dto.pdImg}"></div>
				</div>  <!-- 왼쪽 그리드 -->
				
				<div class="col-md-7">  <!-- 오른쪽에 7개의 그리드 => 상품정보 -->
				    <!-- product-list.html의 <div class="card">~</div> 복사해서 붙여넣기 -->
				 	<div class="card shadow-sm">   <!-- shadow-sm 추가, style 삭제 -->
					  <div class="card-body">
					    <h5 class="card-title">${dto.pdName}</h5>
					    <h5 class="card-title pt-3 pb-3 border-top">${dto.pdPrice}</h5> <!-- 추가 -->
					    <p class="card-text pt-3 border-top">
					    	<span class="badge bg-dark">${dto.pdBrand}</span>
					    </p>					    
					    <p class="card-text pb-3">
					    	배송비 2,500원 | 도서산간(제주도) 배송비 추가 5,000원 | 택배배송 | 5일 이내 출고(주말, 공휴일 제외)				    	
					    </p>
					    
					    <p class="card-text pb-3 border-top">
	                       <div class="row">
	                           <div>주문수량
	                           		<input type="number" class="input" id="ctQuantity" name="ctQuantity"  size="10" placeholder="주문수량 입력 " required>
	                           </div>
	                       </div>
		                </p>
		                  
					    <div class="row pt-3 pb-3 border-top">
					    	<div class="col-6">
					     		<h3>총 상품 금액</h3>
					     	</div>		
					     	<div class="col-6" style="text-align: right">
					     		<h3 style="text-decoration:underline; font-weight:bold">${dto.pdPrice}</h3>
					     	</div>
					     </div>
					    
					    <div class="d-flex justify-content-between align-items-center">
					    	<div class="col-6">
					    	<input type="hidden" name="hiddenPdNo" id=hiddenPdNo" value="${dto.pdNo}">
							<button type="button" class="btn btn-lg btn-dark" id="cartAdd" style="width:350px">
							<%-- onclick="window.location='${path}/cartAddAction.do?pdNo=${dto.pdNo}'"> --%>
							장바구니 담기</button>
							</div>
							
							<div class="col-6">
							<input type="hidden" name="hiddenPdNo" id=hiddenPdNo" value="${dto.pdNo}">
							<button type="button" class="btn btn-lg btn-dark" id="order" style="width:350px" 
							onclick="function()">
							<%-- onclick="window.location='${path}/buyAction.do?pdNo=${dto.pdNo}'" --%>주문하기</button>
							</div>								
					    </div>
					  </div>
					</div>
				</div>  <!-- 오른쪽 그리드 -->
			</div>
			</form>	
			<!-- 아래쪽 상세이미지 추가 -->
   	</div>
   	</main>
 	
	<!-- 컨텐츠 끝 -->
	<br><br><br><br><br>
	
	<!------------footer 시작------------->
	<%@ include file="/common/footer.jsp" %>
	<!------------footer 끝-------------->
</div>

</body>
</html>