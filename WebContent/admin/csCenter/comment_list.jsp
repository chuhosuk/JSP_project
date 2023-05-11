<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 반응형웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>관리자 - 게시판목록</title>

<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/admin/ad_productList.css">

<script src="https://kit.fontawesome.com/d967f76dff.js" crossorigin="anonymous"></script>

<!-- 3-3-2. 자바스크립트 이벤트 추가 : 햄버거버튼을 클릭하면 아래쪽으로 메뉴가 나오도록 main.js에서 추가 -->
<!-- defer : 모든 html 파일을 로딩할 때까지 html이 브라우저창에 표시가 안되는 것을 방지 -->
<script src="${path}/resources/js/customer/main.js" defer></script>

</head>
<body>

		        
     <!-- 우측 메뉴 시작 -->
     <div id="right">
     	<div class="table_div">
     		<table border="1" width ="1000px">
     			<!-- 게시글이 있으면 -->
     			<c:forEach var = "dto" items="${list}">
      			<tr>
      				<td>
      					${dto.writer}<br>
      					${dto.content}<br>
      				</td>
      			</tr>
     			</c:forEach>
     		</table>
     	</div>
     </div>
     <!-- 우측 메뉴 종료 -->
		        
		        


</body>
</html>