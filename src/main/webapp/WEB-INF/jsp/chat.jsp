<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<%
	//SESSION 작업
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String userID = auth.getName();

	String toID = null;
	if (request.getParameter("toID") != null) {
		toID = (String) request.getParameter("toID");
	}

%>





<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<title>Link In One</title>
<link rel="stylesheet" href="/CSS/reset.css" />
<link rel="stylesheet" href="/CSS/style.css" />
<script src="/JS/bootstrap.js"></script>
<script type="text/javascript">


	function autoClosingAlert(selector, delay) {
		var alert =$(selector).alert();
		alert.show();
		window.setTimeout(function() { alert.hide() }, delay)
	}


	function submitFunction() {
		var fromID = '<%=userID%>';
		var toID = '<%=toID%>';
		var chatContent = $('#chatContent').val();

		$.ajax({
				type: "POST",
				url: "/ChatSubmitServlet",
				data: {
					fromID: encodeURIComponent(fromID),
					toID: encodeURIComponent(toID),
					chatContent: encodeURIComponent(chatContent)
				},
				success: function (result) {
					if(result == 1) {
						autoClosingAlert('#successMessage', 2000);
					} else if (result == 0) {
						autoClosingAlert('#dangerMessage', 2000);
					} else {
						autoClosingAlert('#warningMessage', 2000);
					}
				}
		});
		//챗 쓰고나서 빈칸으로 만들어 줌
		$('#chatContent').val('');
	}
	
	var lastID = 0;
	function chatListFunction(type){
		var fromID = '<%=userID%>';
		var toID = '<%=toID%>';
		$.ajax({
			type : "POST",
			url : "/ChatListServlet",
			data : {
				fromID : encodeURIComponent(fromID),
				toID : encodeURIComponent(toID),
				listType : type
			},
			success : function(data) {
				if (data == "")
					return;
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for (var i = 0; i < result.length; i++) {
					if (result[i][0].value == fromID) {
						result[i][0].value = '나';
					}
					addChat(result[i][0].value, result[i][2].value,
							result[i][3].value)
				}
				lastID = Number(parsed.last);
			}
		});
	}

	function addChat(chatName, chatContent, chatTime) {
		if(chatName == "나" ){
			$('#chatList').append(
					'<div href="" class="message">' +
					'<div class="message__user-name message__user-name_right">' + chatName +"</div>" +
					'<div class="message__content">' +
					'<span class="message__content-date">' + chatTime + '</span>' +
					'<span class="message__content-content">' + chatContent + '</span>' +
					'</div>' +
					'</div>'
			);
		}else{
			$('#chatList').append(
					'<div href="" class="message">' +
					'<div class="message__user-name message__user-name">' + chatName +"</div>" +
					'<div class="message__content">' +
					'<span class="message__content-content">' + chatContent + '</span>' +
					'<span class="message__content-date">' + chatTime + '</span>' +
					'</div>' +
					'</div>'
			);
		}	
		$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
	}

	function getInfiniteChat() {
		setInterval(function() {
			chatListFunction(lastID);
		}, 1000);
	}
	
	//메시지 온거 있는지 확인해주는 함수
	function getUnread() {
		$.ajax({
			type: "POST",
			url: "/ChatUnreadServlet",
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
        <!-- 채팅 내용 표현 -->
        <div id="chatList" >
        </div>
        <form action="" class="message-input">
			<sec:csrfInput />
          <input type="text" class="message-input__text" id="chatContent"></input>
          <input type="button" value="전송" class="message-input__submit" onclick="submitFunction();"/>
        </form>
      </div>
    </div>
    <!-- end main -->
	
	
	<div class="alert alert-success" id="successMessage"
		style="display: none;">
		<strong>메시지 전송에 성공했습니다.</strong>
	</div>
	
	<div class="alert alert-warning" id="warningMessage"
		style="display: none;">
		<strong>데이터베이스 오류가 발생했습니다</strong>
	</div>
	


	<script type="text/javascript">
		$(document).ready(function() {
			getUnread();
			chatListFunction('0');
			getInfiniteChat();
			getInfiniteUnread();
		});
	</script>
	 <!-- start footer -->
    <script src="/JS/footer.js"></script>
    <!-- end footer -->
</body>
</html>