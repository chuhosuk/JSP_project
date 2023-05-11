<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file= "/common/setting.jsp" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  반응형웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>관리자 - 게시판 상세</title>

<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/admin/ad_productList.css">

<!-- 1. http://fontawesome.com/에서 인증(start for free) 
     2. 이미지 사용가능 -->
<script src="https://kit.fontawesome.com/d967f76dff.js" crossorigin="anonymous"></script>
<!-- 3-3-2. 자바스크립트 이벤트 추가 : 햄버거버튼을 클릭하면 아래쪽으로 메뉴가 나오도록 main.js에서 추가  -->
<!-- defer : 모든 html 파일을 로딩할 때까지 html이 브라우저창에 표시가 안되는 것을 방지 -->
<script src="${path}/resources/js/customer/main.js" defer></script>
<script src = "http://code.jquery.com/jquery-3.6.3.min.js"></script>

<script>
$(function() {   // 페이지가 로딩되면
         
      // 자동으로 댓글목록 호출
      comment_list();
      
      // 댓글 쓰기 버튼 클릭시
      $("#btnSave").click(function(){
        comment_add(); 
      });
   
   
      // 게시글 목록버튼 클릭시 컨트롤러의 목록으로 이동 
      $("#btnList").click(function() {
        // alert("목록");
         location.href="${path}/board_list.bo";
      });
      
      $("#btnEdit").click(function(){
         // alert("상세 페이지 - btnEdit");
         document.detailForm.action="${path}/password_chkAction.bo";
            document.detailForm.submit();
            
      });
      
   });
   
// 댓글쓰기

function comment_add() {
   alert("comment_add()");
   
   // 게시글 번호와 작성자와 글내용 을 파라미터로 넘김
   let param = {
      "board_num": ${dto.num},    //key: value
      "writer": $('#writer').val(),
      "content": $('#content').val()
   };
   
   $.ajax({
      url: '${path}/comment_insert.bo',
      type: "post",
      data: param,
      success: function(){ // 댓글쓰기가 완료되면 서버에서 콜백함수 호출
         $('#writer').val("");   // 작성자 내용을 지움
         $('#content').val("");   // 글 내용을 지움
         comment_list();      //댓글 목록을 새로고침
      },
      error: function(){
         alert('comment_add() 오류');
      },
   });
};

// 댓글목록

function comment_list() {
   alert("comment_list()");
   
   $.ajax({
      url: '${path}/comment_list.bo',
      type: "post",
      data: "num=${dto.num}",
      success: function(result){ // 서버에서 콜백함수 호출 : comment_list.jsp(컨트롤러에서 forward)의 결과를 result에 담아서 돌아온다.
         $('#commentList').html(result);
      },
      error: function(){
         alert('comment_list() 오류');
      },
   });
}
</script>

         
</head>
<body>

<div class="wrap">
   <%@ include file="/admin/common/header.jsp" %>
   <!---------------------------- 컨텐츠 시작  ---------------------------->
   <div id="container">
      
      <div id="contents">
         <!----------------------- 상단 중앙1 시작 ------------------------>
         <div id="section1">
            <h1 align="center"> 게시판 상세 </h1>
         </div>
         <!----------------------- 상단 중앙2 시작 ------------------------>
         <div id="section2">
            <!----------------------- 좌측메뉴 시작 ----------------------->
            <div id="left">
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
                  <form method="post" name="detailForm">
                     <table>
                        <tr>
                           <th style="width: 200px">작성일</th>
                           <td style="width: 200px; text-align: center">
                              ${dto.regDate}
                           </td>
                        
                           <th style="width: 200px">조회수</th>
                           <td style="width: 200px; text-align: center">
                              ${dto.readCnt}
                           </td>
                        </tr>
                        
                        <tr>
                           <th style="width: 200px">작성자</th>
                           <td style="width: 200px; text-align: center">
                              ${dto.writer}
                           </td>
                        
                           <th style="width: 200px">비밀번호</th>
                           <td text-align: center">
                              <input style="width: 200px"" type="password" class="input" name="password" size="30"
                               placeholder="비밀번호 입력" required autofocus> 
                               
                              <!-- 비밀번호 인증 실패시 컨트롤러에서 전달한 error를 전달받는다. -->
                              <c:if test="${param.message=='error'}">
                                 <br><span style="color:red">비밀번호 불일치 </span>
                              </c:if>
                           </td>
                        </tr>
                        
                        <tr>
                           <th>글제목</th>
                           <td colspan="3" text-align: center">
                              ${dto.title}
                           </td>
                        </tr>
                        
                        <tr>
                           <th>글내용</th>
                           <td colspan="3" text-align: center">
                              ${dto.content}
                           </td>
                        </tr>
                        
                        <tr>
                                 <th colspan="4">
                                     <input type="hidden" name="num" value="${dto.num}">
                                       <input type="button" class="inputButton" value="수정/삭제" id="btnEdit">
                                       <input type="button" class="inputButton" value="목록" id="btnList">
                                   </th>
                                </tr>   
                     </table>
                     
                     <br><br>
                     
                     <!-- 댓글 입력 코드 -->
                     <table align="center" border="1">
                        <tr>
                           <td align="center" colspan="4" style="width:100px">댓 글 </td>
                        </tr>   
                           
                        <tr>
                           <th style="width:200px">이름</th>
                           <td style="width:200px" colspan="2">
                              <input style="width: 460px" class="input" id="writer" placeholder="이름 입력">
                           </td>
                           <td style="width:20px" rowspan="2" align="right">
                              <center><button id="btnSave" class="inputButton" type="button">확인</button></center>
                           </td>
                        </tr>            
                           
                        <tr>
                           <th style="width: 200px">내용</th>
                           <td style="width: 170px">
                              <textarea rows="5" cols="60" id="content" placeholder="내용 입력"></textarea>
                           </td>
                        </tr>
                     </table>
                     
                     <br><br>
                     <div>
                     
                     </div>
                     <div id="commentList" align="center">
                       	 댓글 목록을 출력할 영역
                     </div>
                     
                  </form>
               </div>
            </div>
            <!----------------------- 우측 메뉴 종료 ------------------------->
         </div>
      </div>
   </div>
   <!---------------------------- 컨텐츠 종료  ---------------------------->
   <br><br><br>
   <%@ include file="/admin/common/footer.jsp" %>
   

</div>




</body>
</html>