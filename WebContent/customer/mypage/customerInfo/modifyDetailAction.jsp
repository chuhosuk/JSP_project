<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file= "/common/setting.jsp" %>
<%@ page import="pj.mvc.jsp.dto.CustomerDTO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원수정 상세페이지</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/customer/join.css">

<script src="https://kit.fontawesome.com/b435fbc087.js"crossorigin="anonymous"></script>
<script src="${path}/resources/js/customer/main.js" defer></script>

<!-- join.js -->
<script src="${path}/resources/js/customer/join.js"defer></script>




</head>
<body>

<div class="wrap">
	<!----------- header 시작 ---------->
	<%@ include file="/common/header.jsp" %>

	<!-------------header 끝------------->
	
	<!-- 컨텐츠 시작  -->
	   <div id="container">
      <div id="contents">
         <!-- 상단 중앙1 시작  -->
         <div id="section1">
            <h1 align="center"> 회원수정 상세페이지 </h1>
         </div>      
         
         <!-- 상단 중앙2 시작  -->
         <div id="section2">
            <div id="s2_inner">            
               <div class="join">
                  <form name="joinform" action="${path}/modifyCustomerAction.do" method="post">
                  
	                <!-- 인증성공 --> 	
                  	<c:if test="${selectCnt ==1}">
                     <table>
	                        <tr>
	                           <th> * 아이디 </th>
	                           <td> 
	                           	${dto.getId()}
	                           </td>
	                        </tr>
	                        
	                        <tr>
	                           <th> * 비밀번호  </th>
	                           <td colspan="2"> 
	                              <input type="password" class="input" name="password" size="20" placeholder="공백없이 15자 이내로 작성" required value="${dto.getPassword()}">
	                           </td>
	                        </tr>
	                        
	                        <tr>
	                           <th> * 비밀번호(확인)  </th>
	                           <td colspan="2"> 
	                              <input type="password" class="input" name="repassword" size="20" placeholder="비밀번호 확인" required value="${dto.getPassword()}">
	                           </td>
	                        </tr>
	                        
	                        <tr>
	                           <th> * 이름  </th>
	                           <td colspan="2"> 
	                              <input type="text" class="input" name="name" size="20" placeholder="이름 작성" required value="${dto.getName()}">
	                           </td>
	                        </tr>
	                        
	                        <tr>
	                           <th> * 생년월일  </th>
	                           <td colspan="2"> 
	                              <input type="date" class="input" name="birthday" size="8" placeholder="생년월일8자리" required value="${dto.getBirthday()}">
	                           </td>
	                        </tr>
	                        
	                        
	                        <tr>
	                           <th> * 주소  </th>
	                           <td colspan="2"> 
	                              <input type="text" class="input" name="address" size="50" placeholder="주소 작성" required value="${dto.getAddress()}">
	                           </td>
	                        </tr>
	                        
	                        <tr>
	                           <th> * 핸드폰  </th>
	                           <td> 
	                           		<c:if test="${dto.getHp()==null}">
	                           		  <input type="text" class="input" name="hp1" size="3" style="width:50px">
	  	                           	  -
	  	                              <input type="text" class="input" name="hp2" size="4" style="width:70px">
	  	                              -
	  	                              <input type="text" class="input" name="hp3" size="4" style="width:70px">
	  	                        	</c:if>      
	  	                        	
	  	                        	<!-- String hp = dto.getHp();
	                           		String[] hpArr =hp.split("-"); -->
	  	                             
	                           		<c:if test="${dto.getHp()!=null}">
	                           		<c:set var="hpArr" value="${fn:split(dto.getHp(),'-')}" />
	                           		  <input type="text" class="input" name="hp1" size="3" style="width:50px" value="${hpArr[0]}">
	  	                           	  -
	  	                              <input type="text" class="input" name="hp2" size="4" style="width:70px" value="${hpArr[1]}">
	  	                              -
	  	                              <input type="text" class="input" name="hp3" size="4" style="width:70px" value="${hpArr[2]}">
	                           		</c:if>
	                           
	                              
	                           </td>
	                          
	                        </tr>
	                        
	                        <tr>
	                           <th> * 이메일  </th>
	                           <td> 
		                         	<%--   
	                           		String email = dto.getEmail();
	                           		String[] emailArr =email.split("@");
		                            --%>
	                              <c:set var="emailArr" value="${fn:split(dto.getEmail(),'@')}" />
	                              <input type="text" class="input" name="email1" maxlength="20" style="width:100px" required value="${emailArr[0]}">
	                           	  @
	                              <input type="text" class="input" name="email2" maxlength="20" style="width:100px" required value="${emailArr[1]}">
	                              <select class="input" name="email3" style="width:100px" onchange="selectEmailChk();">
	                              		<option value="0">직접입력</option>
	                              		<option value="naver.com">네이버</option>
	                              		<option value="google.com">구글</option>
	                              		<option value="daum.net">다음</option>
	                              		<option value="nate.com">네이트</option>
	                              </select>
	                           </td>
	                          
	                        </tr>
	                        
	                        <tr>
	                           <td colspan="3" style="border-bottom: none;">
	                              <br>
	                              <div align="right">
	                                 <input class="inputButton" type="submit" value="회원정보 수정">
	                                 <input class="inputButton" type="reset" value="초기화">
	                                 <input class="inputButton" type="button" class="button" value="수정취소" onclick="window.location='${path}/login/login.do'">
	                              </div>
	                           </td>
	                        </tr>
                    	 </table>
                    </c:if>
                    
	                 <!--인증실패-->
                 	<c:if test="${selectCnt !=1}">
	                 	<script type="text/javascript">
	                 		setTimeout(function() {
	                 	 	alert("인증실패");
	                 	 	window.location ="${path}/modifyCustomer.do"
	                 		},1000);
	                 	</script>	
	                </c:if>
                  </form>               
               </div>
            </div>
         </div>
      </div>
   </div>

	<!-- 컨텐츠 끝 -->
	<br><br><br>
	
	<!------------footer 시작------------->
	<%@ include file="/common/footer.jsp" %>
	<!------------footer 끝-------------->
</div>

</body>
</html>