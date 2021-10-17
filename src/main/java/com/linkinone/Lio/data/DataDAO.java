package com.linkinone.Lio.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;




public class DataDAO {
	Connection conn = null;

	public DataDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/liodb?serverTimezone=UTC&characterEncoding=UTF-8";
			conn = DriverManager.getConnection(url, "root", "lio1232");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<DataDTO> getLangList(String type){
		ArrayList<DataDTO> dataList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL ="select techName, count(*) from "+type+" join tech on "+type+".techID = tech.techID group by tech.techID;";
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			dataList = new ArrayList<DataDTO>();
			while(rs.next())  {
				DataDTO data = new DataDTO();
				data.setLang(rs.getString("techName"));
				data.setCnt(rs.getInt("count(*)"));
				dataList.add(data);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				
				if (pstmt != null)
					pstmt.close();
				
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dataList; 
	}//getChatList END
	
}
