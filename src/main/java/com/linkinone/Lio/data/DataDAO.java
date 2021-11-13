package com.linkinone.Lio.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;




public class DataDAO {


	public ArrayList<DataDTO> getLangList(String type){
		ArrayList<DataDTO> dataList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL ="select techName, count(*) from "+type+" join tech on "+type+".techID = tech.techID group by tech.techID;";
		try {
			conn = DBManager.connect();
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
			DBManager.close(conn, pstmt, rs);
			}
		return dataList; 
	}//getChatList END

	public ArrayList<DataDTO> getLangList2(){
		ArrayList<DataDTO> dataList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL ="select tech, count(*) from board group by tech";
		try {
			conn = DBManager.connect();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();

			dataList = new ArrayList<DataDTO>();
			while(rs.next())  {
				DataDTO data = new DataDTO();
				data.setLang(rs.getString("tech"));
				data.setCnt(rs.getInt("count(*)"));
				dataList.add(data);
			}

		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return dataList;
	}//getChatList END


}
