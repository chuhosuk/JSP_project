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
$(function() {
	//writer,password, title, content
	$("#btnSave").click(function() {
		const writer = $("#writer").val();	
		const password = $("#password").val();	
		const title = $("#title").val();	
		const content = $("#content").val();	
		
		if(writer==""){
			alert("작성자를 입력하세요");
			$("#writer").focus();
			return false;
		}
		if(password==""){
			alert("비밀번호를 입력하세요");
			$("#password").focus();
			return false;
		}
		if(title==""){
			alert("제목을 입력하세요");
			$("#title").focus();
			return false;
		}
		if(content==""){
			alert("글내용을 등록하세요");
			$("#content").focus();
			return false;
		}
		
		document.insertForm.action="${path}/board_insertAction.bo";
		document.insertForm.submit();
	
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
            <h1 align="center"> 게시판 글쓰기페이지 </h1>
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
               <form method="post" name="insertForm">
                  <table>
                     <tr>
                        <th style="width: 200px">작성자</th>
                        <td style="width: 200px; text-align: center">
                        	<input style="width:250px" type="text" class="input" name="writer" id="writer" size="30" autofocus>
                        </td>
                        
                        <th style="width: 200px">비밀번호</th>
                        <td style="width: 200px; text-align: center">
                        	<input style="width:250px" type="password" class="input" name="password" id="password" size="30" autofocus>
                        </td>
                     </tr>
                        
                     
                     <tr>
                     	<th>글제목</th>
                        <td colspan="3" >
                        	<input type="text" style="text-align: center; width: 750px" class="input" name="title" id="title" size="50">
                        </td>
                     </tr>
                     
                     <tr>
                     	<th>글내용</th>
                        <td colspan="3" style="text-align: center">
                        	<textarea rows="5" cols="93" name="content" id="content"></textarea>
                        </td>
                     </tr>
                        
                     <tr>
                     	<th colspan="4">
                     		<input type="hidden" name="num" value="${dto.num}">
                     		<input type="button" class="inputButton" value="작성" id="btnSave">   	
                     		<input type="button" class="inputButton" value="초기화" >   	
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