   var lastID = 0;
function researchListFunction(){
    	$.ajax({
    		type : "POST",
    		url : "/ResearchListServlet",
    		success : function(data) {
    			if (data == "")
    				return;
    			var parsed = JSON.parse(data);
    			var result = parsed.result;
    			for (var i = 0; i < result.length; i++) {
    				addResearch(result[i].value)
    			}
    			lastID = Number(parsed.last);
    		}
    	});
    }

function addResearch(userLang) {
	if(userLang == "C#"){
		$('#research_list').append(
			'<span class="tag">' + userLang +
			'<img src="/img/close_white_24dp.svg" class="tag__close-btn" onclick="location.href=\'/ResearchDeleteServlet?tech=C%23\'"/>' +
			'</span>'
		);
	}else if(userLang == "C++"){
		$('#research_list').append(
			'<span class="tag">' + userLang +
			'<img src="/img/close_white_24dp.svg" class="tag__close-btn" onclick="location.href=\'/ResearchDeleteServlet?tech=C%2B%2B\'"/>' +
			'</span>'
		);
	}else{
	   $('#research_list').append(
		   '<span class="tag">' + userLang +
		   '<img src="/img/close_white_24dp.svg" class="tag__close-btn" onclick="location.href=\'/ResearchDeleteServlet?tech='+userLang+'\'"/>' +
		   '</span>'
	   );
   }
	
	$('#user_research_list').append(
		'<span class="tag">' + userLang + '</span>'
	);
}
$(document).ready(function() {
	researchListFunction();
});