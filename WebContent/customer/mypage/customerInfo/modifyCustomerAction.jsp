<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file= "/common/setting.jsp" %>    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원수정처리</title>

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
	
	<!-- 컨텐츠 시작 -->
	<div id="container">
		<div id="contents">
			<!-- 상단 중앙1 시작 -->
			<div id="section1">
				<h1 align="center">회원수정처리</h1>
			</div>	
			
			<!-- 상단 중앙2 시작 -->
			<div id="section2">
				<div id="s2_inner">
					<div class="login">
						<form name="updateform">
						
							<c:if test="${updateCnt==0}">
								<script type="text/javascript">
									setTimeout(function() {
									alert("회원수정 실패");
									window.location="${path}/modifyDetailAction.do";
									},1000);
								</script>	
							</c:if>			
							
							<c:if test="${updateCnt!=0}">
								<script type="text/javascript">
									setTimeout(function() {
									alert("회원수정 성공");
									window.location="${path}/login.do";
									},1000);
								</script>	
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