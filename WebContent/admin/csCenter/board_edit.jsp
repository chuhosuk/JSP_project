<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  반응형웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>관리자 - 게시판 수정 및 삭제페이지</title>

<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/admin/ad_productList.css">

<!-- 1. http://fontawesome.com/에서 인증(start for free) 
     2. 이미지 사용가능 -->
<script src="https://kit.fontawesome.com/d967f76dff.js" crossorigin="anonymous"></script>
<!-- 3-3-2. 자바스크립트 이벤트 추가 : 햄버거버튼을 클릭하면 아래쪽으로 메뉴가 나오도록 main.js에서 추가  -->
<!-- defer : 모든 html 파일을 로딩할 때까지 html이 브라우저창에 표시가 안되는 것을 방지 -->
<script src="${path}/resources/js/customer/main.js" defer></script>

<script>
$(function() { // 페이지가 로딩되면
	
	// 게시글 목록버튼 클릭시 컨트롤러의 목록으로 이동
	$("#btnList").click(function() {
		location.href="${path}/board_list.bo"
	});
	
	// 게시글 수정버튼 클릭시 컨트롤러의 수정으로 이동
	$("#btnEdit").click(function() {
		
		const password = $("#password").val();
		const title = $("#title").val();
		const content = $("#content").val();
		
		if(password==""){
			alert("비밀번호를 입력하세요");
			$("#password").focus();
			return false;
		}
		if(title==""){
			alert("게시글 제목을 입력하세요");
			$("#title").focus();
			return false;
		}
		if(content==""){
			alert("게시글 내용을 입력하세요");
			$("#content").focus();
			return false;
		}
		
		document.editForm.action="${path}/board_updateAction.bo";
		document.editForm.submit();
	});
	
	// 게시글 삭제버튼 클릭
	$("#btnDelete").click(function() {
		
		if(confirm("삭제하시겠습니까?")){
		
		document.editForm.action="${path}/board_deleteAction.bo";
		document.editForm.submit();
		}
	});

	
});

</script>

         
</head>
<body>

<div class="wrap">
   <%@ include file="/admin/common/header.jsp" %>
   <!--- 컨텐츠 시작  --->
   <div id="container">
      
      <div id="contents">
         <!---- 상단 중앙1 시작 --->
         <div id="section1">
            <h1 align="center"> 게시판 수정삭제페이지 </h1>
         </div>
         
         <!--- 상단 중앙2 시작 --->
         <div id="section2">
         
            <!--- 좌측메뉴 시작 --->
            <div id="left">
               <div class="left_inside">
               
               <!--- 좌측메뉴바 시작 --->
               <%@ include file="/admin/common/leftMenu.jsp" %>
               <!--- 좌측메뉴바 종료 --->
               
               </div>
            </div>
            <!--- 좌측 메뉴 종료 --->
            
            <!--- 우측 메뉴 시작 --->
            <div id="right">
               <div class="table_div">
               <form method="post" name="editForm">
                  <table>
                     <tr>
                        <th style="width: 200px">작성일</th>
                        <td style="width: 200px"; text-align: center>
                        	${dto.regDate}
                        	
                        </td>
                        
                        <th style="width: 200px">조회수</th>
                        <td style="width: 200px"; text-align: center>
                        	${dto.readCnt}
                        </td>
                     </tr>
                        
                     <tr>
                     	<th style="width: 200px">작성자</th>
                        <td style="width: 200px"; text-align: center>
                        	${dto.writer}
                        </td>
                        
                        <th style="width: 200px">비밀번호</th>
                        <td text-align: center>
                        	<input style="width: 200px" type="password" class="input" name="password" id="password" size="30" autofocus>
                        </td>
                     </tr>
                     
                     <tr>
                     	<th>글제목</th>
                        <td colspan="3" style="text-align: center">
                        	<input type="text" class="input" name="title" id="title" size="50"value="${dto.title}">
                        </td>
                     </tr>
                     
                     <tr>
                     	<th>글내용</th>
                        <td colspan="3" style="text-align: center">
                        	<textarea rows="5" cols="93" name="content" id="content">${dto.content}</textarea>
                        </td>
                     </tr>
                        
                     <tr>
                     	<th colspan="4">
                     		<input type="hidden" name="num" value="${dto.num}">
                     		<input type="button" class="inputButton" value="수정" id="btnEdit">   	
                     		<input type="button" class="inputButton" value="삭제" id="btnDelete">   	
                     		<input type="button" class="inputButton" value="목록" id="btnList"> 	
                     	</th>
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
   <%@ include file="/admin/common/footer.jsp" %>
   

</div>




</body>
</html>