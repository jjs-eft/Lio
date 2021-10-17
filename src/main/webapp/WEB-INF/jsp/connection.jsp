<html>
<head>
<%@ page import="java.sql.* "%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="java.io.*"%>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//DB 연결 되어 있는지 확인하는 페이지
		Connection conn = null;
		InitialContext initCtx = new InitialContext();
		Context envContext = (Context) initCtx.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/liodb");
		conn =  ds.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT VERSION();");
		while(rset.next()){
			out.println("MySQL Version :" + rset.getString("version()"));
		}
		
		rset.close();
		stmt.close();
		conn.close();
		initCtx.close();
	%>
</body>
</html>