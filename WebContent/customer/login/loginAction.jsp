<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file= "/common/setting.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>login</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/customer/login.css">

<script src="https://kit.fontawesome.com/b435fbc087.js"crossorigin="anonymous"></script>
<script src="${path}/resources/js/customer/main.js" defer></script>
</head>
<body>

<div class="wrap">
	<!----------- header 시작 ---------->
	<%@ include file="/common/header.jsp" %>

	<!-------------header 끝------------->
	

	<!-- 컨텐츠 시작  -->
	
<!-- 	<script type="text/javascript">
		alert('관리자 로그인');
		window.location="${path}/admin/ad_product/ad_productList.html";	
	</script>
	 -->
	
	<div id="container">
		<div id="contents">
			<!-- 상단 중앙1 시작 -->
			<div id="section1">
				<h1 align="center">로그인처리</h1>
			</div>	
			
			<!-- 상단 중앙2 시작 -->
			<div id="section2">
				<div id="s2_inner">
					<div class="login">
						<form name="loginform" action="${path}/loginAction.do" method="post">
						<!-- 세션이 없는 경우 : 로그인 실패 -->
						<c:if test="${sessionScope.sessionID==null}">
							<table>
								<tr>
									<td colspan="2" align="center">
										<h3>
											<%
												out.print("아이디나 비밀번호가 존재하지 않습니다. 다시 로그인하세요.");
											%>
										</h3>
									</td>
								</tr>
								
								<tr>
									<th>*아이디</th>
									<td>
										<input type="text" class="input" name="id" size="15" placeholder="공백없이 15자 이내로 작성" autofocus required>
									</td>
								</tr>
								
								<tr>
									<th>*비밀번호</th>
									<td colspan="2">
										<input type="password" class="input" name="password" size="15" placeholder="공백없이 15자 이내로 작성" required>
									</td>
								</tr>
								
								<!-- 버튼만들기 -->
								<tr>
									<td colspan="3" style="border-bottom: none;">
										<br>
										<div align="right">
											<input class="inputButton" type="submit" value="로그인">
											<input class="inputButton" type="reset" value="초기화">
											<input class="inputButton" type="button" value="회원가입" onclick="window.location='${path}/join.do'">
										</div>
									</td>
								</tr>
							</table>
						</c:if>	
						
						<!-- 세션이 있는 경우 : 로그인 성공 -->
						<c:if test="${sessionScope.sessionID!=null}">
							<table>
								<tr>
									<td colspan="2" align="center">
										<h3>
											<span style="color: #FF82AA;"><b>${sessionScope.sessionID}</b></span>님 반갑습니다.
										</h3>
									</td>
								</tr>
								
								
								<!-- 버튼만들기 -->
								<tr>
									<td colspan="3" style="border-bottom: none;">
										<br>
										<div align="right">
											<input class="inputButton" type="button" value="쇼핑하기" onclick="window.location='${path}/main.do'">
											<input class="inputButton" type="button" value="회원수정" onclick="window.location='${path}/modifyCustomer.do'">
											<input class="inputButton" type="button" value="회원탈퇴" onclick="window.location='${path}/deleteCustomer.do'">
											<input class="inputButton" type="button" value="로그아웃" onclick="window.location='${path}/logout.do'">
										</div>
									</td>
								</tr>
							</table>
						</c:if>
						
						
							
						</form>
					</div>
				</div>
			</div>			
		</div>
	</div>
	
	
	<!-- 컨텐츠 종료 -->
	<br><br><br><br><br>
	
	<!------------footer 시작------------->
	<%@ include file="/common/footer.jsp" %>
	<!------------footer 끝-------------->
</div>

</body>
</html>