<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>관리자 - 주문승인목록</title>

<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/admin/ad_productAdd.css">

<script src="https://kit.fontawesome.com/b435fbc087.js" crossorigin="anonymous"></script>

<script src="${path}/resources/js/customer/main.js" defer></script>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

    	  var dataArray = [['Task', 'Hours per Day']];

          $("input[name=google]").each(function(i){

        	  dataArray.push([$("input[name=pdName]").eq(i).val(),parseInt($("input[name='saleTotal']").eq(i).val())]);

        	  });
        	  

        	  var data = google.visualization.arrayToDataTable(dataArray);
           var options = {
             title: '결산'
           };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>

</head>
<body>

<div class="wrap">
	<!----------- header 시작 ---------->
	<%@ include file="/admin/common/header.jsp" %>
	<!-------------header 끝------------->
	
	  <!---------------------------- 컨텐츠 시작  ---------------------------->
   <div id="container">
      
      <div id="contents">
         <!----------------------- 상단 중앙1 시작 ------------------------>
         <div id="section1">
            <h1 align="center"> 결산 </h1>
         </div>
         <!----------------------- 상단 중앙2 시작 ------------------------>
         <div id="section2">
            <!----------------------- 좌측메뉴 시작 ----------------------->
            <div id="left" >
               <div class="left_inside">
               		
               <!----------------------- 좌측메뉴바 시작 ------------------------->
               <%@ include file="/admin/common/leftMenu.jsp" %>
               <!----------------------- 좌측메뉴바 종료 ------------------------->
               </div>
            </div>
            <!----------------------- 좌측 메뉴 종료 ------------------------->
            
            <!----------------------- 우측 메뉴 시작 ------------------------->
           <div id="right">
           <section class="cart">
           <table class="cart_list">
               <div id="piechart" style="width: 900px; height: 500px;">
               		<c:forEach var = "dto" items="${sale}">
					   		<!-- 구분용 -->
					   		<input type="hidden" name = "google">
					   		<input type="hidden" name="pdName" id="pdName" value="${dto.pdName}">
					   		<input type="hidden" name="saleTotal" id="saleTotal" value="${dto.saleTotal}">
					 </c:forEach>
               </div>
            </table>
            </section>
            </div>
            <!--- 우측 메뉴 종료 --->
         </div>
      </div>
   </div>
   <!--- 컨텐츠 종료  --->
   <br><br><br>
	
	<!------------footer 시작------------->
	<%@ include file="/admin/common/footer.jsp" %>
	<!------------footer 끝-------------->
	
</div>
	


</body>
</html>