<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>관리자 - 상품상세 페이지</title>

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
            <h1 align="center"> 상품상세 페이지 </h1>
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
            			<form action="${path}/ad_product_updateAction.pd" method="post" name="ad_productAdd" enctype="multipart/form-data">
	            			<table>
	            				<!-- hidden: 직접 input 태그에서 입력받지 못한 값들을 전달할 때 사용 -->
	            				<input type="hidden" name="pageNum" value="${pageNum}">
	            				<input type="hidden" name="hiddenPdNo" value="${dto.pdNo}">
	            				<input type="hidden" name="hiddenPdImg" value="${dto.pdImg}"> <!-- 수정전 상품이미지 -->
	            				
	            				<tr>
	            					<th>* 상품번호</th>
	            					<td>${dto.pdNo}</td>
	            				</tr>
	            				
	            				<tr>
	            					<th>* 상품명</th>
	            					<td>
	            						<input type="text" class="input" id="pdName" name="pdName" size="40" value="${dto.pdName}" 
	            						placeholder="상품명 입력 " required>
	            					</td>
	            				</tr>
	            				
	            				<tr>
	            					<th>* 브랜드</th>
	            					<td>
	            						<input type="text" class="input" id="pdBrand" name="pdBrand" size="50" value="${dto.pdBrand}" 
	            						placeholder="브랜드 입력 " required autofocus>
            						</td>
	            				</tr>
	            				
	            				<tr>
	            					<th>* 상품이미지</th>
	            					<td>
	            						<img src="${dto.pdImg}" width="80px"><br>
	            						<input type="file" class="input" id="pdImg" name="pdImg" accept="image/*">
	            					</td>
	            				</tr>
	            				
	            				<tr>
	            					<th>* 상품카테고리</th>
	            					<td>
	            						<select class="input" id="pdCategory" name="pdCategory" required>
	            							<option>상품카테고리</option>
	            							<option <c:if test="${dto.pdCategory == '스니커즈'}">selected</c:if> value="스니커즈">스니커즈</option>
	            							<option <c:if test="${dto.pdCategory == '런닝화'}">selected</c:if> value="런닝화">런닝화</option>
	            							<option <c:if test="${dto.pdCategory == '구두'}">selected</c:if> value="구두">런닝화</option>
	            						</select>	
	            					</td>
	            				</tr>
	            				
	            				<tr>
	            					<th>* 상품설명</th>
	            					<td>
	            						<textarea rows="5" cols="80" id="pdContent" name="pdContent" placeholder="제품설명 입력">${dto.pdContent}</textarea>
	            					</td>
	            				</tr>
	            				
	            				<tr>
	            					<th>* 판매가격</th>
	            					<td>
	            						<input type="number" class="input" id="pdPrice" name="pdPrice" size="10" value="${dto.pdPrice}"
	            						placeholder="판매가격 입력 " required></td>
	            				</tr>
	            				
	            				<tr>
	            					<th>* 등록수량</th>
	            					<td>
	            						<input type="number" class="input" id="pdQuantity" name="pdQuantity" size="10" value="${dto.pdQuantity}" 
	            						placeholder="등록수량 입력 " required></td>
	            				</tr>
	            				
	            				<tr>
	            					<th>* 판매상태</th>
	            					<td>
	            						<select class="input" id="pdStatus" name="pdStatus" required>
	            							<option>상품 판매상태</option>
	            							<option <c:if test="${dto.pdStatus == '판매중'}">selected</c:if> value="판매중">판매중</option>
	            							<option <c:if test="${dto.pdStatus == '품절'}">selected</c:if> value="품절">품절</option>
	            						</select>	
	            					</td>
	            				</tr>
	            				
	            				<tr>
	            					<th>등록일</th>
	            					<td colspan="2">
	            						${dto.pdIndate}
	            					</td>
	            				</tr>
	            				
	            				 <tr>
		                           <td colspan="2">
		                              <br>
		                              <div align="center">
		                                 <input class="inputButton" type="submit" value="상품수정">
		                                 <input class="inputButton" type="reset" value="초기화">
		                                 <input class="inputButton" type="button" value="목록" onclick="window.location='${path}/ad_product_list.pd'">
		                                 
		                              </div>
		                           </td>
                        		</tr>
	            				
	            			</table>
            			</form>
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