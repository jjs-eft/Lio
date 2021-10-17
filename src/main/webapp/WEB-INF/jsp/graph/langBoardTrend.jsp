<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ColumnChart1</title>
<style>
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		packages : [ 'corechart', 'bar' ]
	});
	google.charts.setOnLoadCallback(drawBasic);
	function drawBasic() {
		var data = new google.visualization.DataTable(${json});
		var options = {
			title : '${languge} 월별 필요수',
			width:'100%',
			height: '100%',
			hAxis : {
				title : '년도-월',
				viewWindow: {
					min: [7, 30, 0],
					max: [17, 30, 0]
				}
			},
			vAxis : {
				title : '인원수'
			}
		};
		var chart = new google.visualization.ColumnChart(
		document.getElementById('chart_div'));
		chart.draw(data, options);
	}

</script>
</head>
<body>

	
	<table border="1" style="width: 100%; height: 100%;">
		<tr>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','C')" style="cursor:pointer; width: 20%;">C</td>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','C++')" style="cursor:pointer; width: 20%;">C++</td>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','C#')" style="cursor:pointer; width: 20%;">C#</td>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','Python')" style="cursor:pointer; width: 20%;">Python</td>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','Java')" style="cursor:pointer; width: 20%;">Java</td>
		</tr>
		<tr>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','JavaScript')" style="cursor:pointer;">JavaScript</td>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','Visual_Basic')" style="cursor:pointer;">Visual_Basic</td>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','PHP')" style="cursor:pointer;">PHP</td>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','R')" style="cursor:pointer;">R</td>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','SQL')" style="cursor:pointer;">SQL</td>
		</tr>
		<tr>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','Groovy')" style="cursor:pointer;">Groovy</td>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','Perl')" style="cursor:pointer;">Perl</td>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','Go')" style="cursor:pointer;">Go</td>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','Swift')" style="cursor:pointer;">Swift</td>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','Ruby')" style="cursor:pointer;">Ruby</td>
		</tr>
		<tr>
			<td align="center" onClick="javascript:mySubmit('graph/langBoardTrend.jsp','Kotlin')" style="cursor:pointer;">Kotlin</td>
		</tr>
		<tr>
			<td colspan="5"><div id="chart_div" style="width:100%; height: 100%;"></div></td>
		</tr>
	</table>
</body>
</html>