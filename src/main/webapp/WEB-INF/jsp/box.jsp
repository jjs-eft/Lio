<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import = "org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%
	//SESSION 작업
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String userID = auth.getName();
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<title>Link In One</title>
<link rel="stylesheet" href="/CSS/reset.css" />
<link rel="stylesheet" href="/CSS/style.css" />
<script src="/JS/bootstrap.js"></script>
<script type="text/javascript">
	
	//메시지 온거 있는지 확인해주는 함수
	function getUnread() {

		$.ajax({
			type: "POST",
			url: "ChatUnreadServlet",
			data: {
  				userID: encodeURIComponent('<%= userID %>'),
			},
			success: function (result) {
				if(result >= 1){
					showUnread(result);
				} else {
					showUnread('');
				}
			}
		});
	}
	
	//4초마다 메시지 왔는지 책크
	function getInfiniteUnread() {
		setInterval(function () {
			getUnread();	
		}, 4000);
	}
	
	function showUnread(result) {
		$('#unread').html(result);
	}
	
	//
	function chatBoxFunction() {
		var userID = '<%= userID %>'
		$.ajax({
			type: "POST",
			url: "/ChatBoxServlet",
			data: {
				userID: encodeURIComponent(userID),
			},
			success: function (data) {
				if(data == "") return;
				$('#boxTable').html('');
				var parsed =JSON.parse(data);
				var result = parsed.result;
				for(var i = 0; i < result.length; i++){
					if(result[i][0].value == userID){
						result[i][0].value = result[i][1].value;
					}else{
						result[i][1].value = result[i][0].value;
					}
					addBox(result[i][0].value, result[i][1].value, result[i][2].value, result[i][3].value, result[i][4].value);
				}
			}
		});
	}
	//메세지함 출력
	function addBox(lastID, toID, chatContent, chatTime , unread) {
		$('#boxTable').append('<a href=\'/ChatListServlet?toID='+encodeURIComponent(toID) + '\'" class="message">' +
				'<div class="message__user-name">' + toID + '</div>'+
				'<div class="message__content">' +
				'<div>' +
				'<span class="message__content-content">' + chatContent + '</span>' +
				'<span class="label label-info">' + unread + '</span>' +
				'</div>' +
				'<span class="message__content-date">' +chatTime + '</span>' +
				'</div>' +
				'</a>');
	}
	// 특정 시간마다 메세지함 확인
	function getInfiniteBox() {
		setInterval(function(){
			chatBoxFunction();
		}, 3000);
	}
	//a태그를 post 방식으로 전송
	  	function mySubmit(val){
	  var f = document.myForm;
	  f.contentPage.value = val;
	  f.submit();
	}
	</script>
</head>
<body>
   <!-- start login-modal -->
    <script src="/JS/login-modal.js"></script>
    <!-- end login-modal -->

    <!-- start header -->
   <sec:authorize access="isAnonymous()"> <script src="../JS/header.js"></script> </sec:authorize>
   <sec:authorize access="isAuthenticated()"> <script src="../JS/header-logined.js"></script> </sec:authorize>

    <!-- end header -->
    
    <!-- start main -->
    <div class="main">
      <div class="main-preview">
        <div class="main-group-head group-head">
          <h2 class="group-head-title underline underline_size-1">메시지</h2>
	        </div>
	        <div id="boxTable">
	        </div>

      </div>
    </div>
    <!-- end main -->
    
    <%
		if(userID != null){
	%>
			<script type="text/javascript">
				$(document).ready(function () {
					getUnread();
					getInfiniteUnread();
					chatBoxFunction();
					getInfiniteBox();
				});
			</script>
	<%
		}
	%>
    
    <!-- start footer -->
    <script src="/JS/footer.js"></script>
    <!-- end footer -->
</body>
</html>