<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>LIO 채팅 서비스</title>
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<!-- 구글 차트 -->
	<script type="text/javascript">
		// 시각화 API 및 원형 차트 패키지를 로드합니다.
		google.charts.load('current', {'packages':['corechart']});
		     
		// Google Visualization API가 로드될 때 실행할 콜백을 설정합니다.
		google.charts.setOnLoadCallback(drawChart);
		     
		function drawChart() { 
			// 서버에서 로드된 JSON 데이터에서 데이터 테이블을 만듭니다.
			var data = new google.visualization.DataTable(${json});
			
			var options = {
				title: '이용자들이 필요하는 언어',
				sliceVisibilityThreshold: .05, //5%이하의 언어는 other로 포함시킴
				width:'100%',
				height: '100%',
			};
			// 몇 가지 옵션을 전달하면서 차트를 인스턴스화하고 그립니다.
			var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
			chart.draw(data, options);
		}
	</script>
</head>
<body>
	<div id="chart_div" style="width:100%; height: 100%;"></div>
</body>
</html>