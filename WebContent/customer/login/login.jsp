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
<script src="${path}/resources/js/customer/join.js"defer></script>join.js
<script type="text/javascript">

/* $(function login() {
	

		
		
		
		location.href="${path}/loginAction.do"
	
		
}); */

</script>
</head>
<body>

<div class="wrap">
	<!----------- header 시작 ---------->
	<%@ include file="/common/header.jsp" %>

	<!-------------header 끝------------->
	
	<!-- 컨텐츠 시작 -->
	<div id="container">
		<div id="contents">
			<!-- 상단 중앙1 시작 -->
			<div id="section1">
				<h1 align="center">로그인</h1>
			</div>	
			
			<!-- 상단 중앙2 시작 -->
			<div id="section2">
				<div id="s2_inner">
					<div class="login">
						<%-- <form name="loginform" action="${path}/loginAction.do" method="post"> --%>
						
						<form name="loginform" action="${path}/loginAction.do" method="post">
							<table>
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
											<input class="inputButton" type="submit" value="로그인" >
											<input class="inputButton" type="reset" value="초기화">
											<input class="inputButton" type="button" value="회원가입" onclick="window.location='${path}/join.do'">
										</div>
									</td>
								</tr>
							</table>
						</form>
						
						
						
						
					</div>
				</div>
			</div>
			
			<div id="section3">
				<div id="s3_inner">
					<div class="login2">
						<form name="admin_loginform" action="${path}/admin_loginAction.do" method="post">
							<table>
								<tr>
									<th>*관리자</th>
									<td>
										<input type="text" class="input" name="id" size="15" placeholder="onlyAdmin" autofocus required>
									</td>
								</tr>
								<tr>
									<th>*비밀번호</th>
									<td colspan="2">
										<input type="password" class="input" name="password" size="15" placeholder="onlyAdmin" required>
									</td>
								</tr>
								
								<!-- 버튼만들기 -->
								<tr>
									<td colspan="3" style="border-bottom: none;">
										<br>
										<div align="right">
											<input class="inputButton" type="submit" value="로그인" >
											<input class="inputButton" type="reset" value="초기화">
										</div>
									</td>
								</tr>
							</table>
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