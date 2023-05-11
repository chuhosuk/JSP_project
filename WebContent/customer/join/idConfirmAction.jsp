<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file= "/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>중복확인</title>


<link rel="stylesheet" href="${path}/resources/css/customer/join.css">

<script src="https://kit.fontawesome.com/b435fbc087.js"crossorigin="anonymous"></script>
<script src="${path}/resources/js/customer/main.js" defer></script>

<!-- join.js -->
<script src="${path}/resources/js/customer/join.js"defer></script>

</head>
<body onload="idConfirmFocus();">

<div class="wrap">

	<!-- 컨텐츠 시작  -->
	   <div id="container">
      <div id="contents">
         <!-- 상단 중앙1 시작  -->
         <div id="section1">
            <h1 align="center"> 중복확인 페이지 </h1>
         </div>      
         
         <!-- 상단 중앙2 시작  -->
         <div id="section2">
            <div id="s2_inner">            
               <div class="join">
               		
                    <form name="confirmform" action="${path}/idConfirmAction.do" method="post" 
                    	onsubmit="return idConfirmCheck();">
					
						<!-- id 중복 -->	
						<c:if test="${selectCnt==1}">
							
								<table align="center">
									<tr>
			                           <th colspan="2" align="center"> 
											<span>${id}</span>는 사용할 수 없습니다.                           
			                           </th>
			                        </tr>
			                        
			                        <tr>
			                           <th> * 아이디 </th>
			                           <td> 
			                              <input type="text" class="input" name="id" size="20" placeholder="공백없이 15자 이내로 작성" autofocus required>
			                           </td>
			                        </tr>
			                        
			                        <tr>
			                           <td colspan="3" style="border-bottom: none;">
			                              <br>
			                              <div align="right">
			                                 <input class="inputButton" type="submit" value="확인">
			                                 <input class="inputButton" type="reset" value="취소" onclick="self.close();">
			                              </div>
			                           </td>
			                        </tr>
			                     </table>	
							</c:if>
								
							<!-- id가 중복이 아닐때 -->
							<c:if test="${selectCnt!=1}">
								<table align="center">
								 	<tr>
			                           <th colspan="2" align="center"> 
										<span>${id}</span>는 사용할 수 있습니다.                           
			                           </th>
			                        </tr>
			                        
			                        <tr>
			                        	<th>
			                        		<input class="input" type="button" value="확인" onclick="setId('${id}');">
			                        	</th>
			                        </tr>
								</table>
							</c:if>
                  </form>               
               </div>
            </div>
         </div>
      </div>
   </div>

	<!-- 컨텐츠 끝 -->
	<br><br><br>
	
</div>

</body>
</html>