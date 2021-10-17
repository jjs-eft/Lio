package com.linkinone.Lio.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LangDAO {
	// 유저 데이터베이스
		DataSource dataSource;

		public LangDAO() {
			try {
				InitialContext initContext = new InitialContext();
				Context evnContext = (Context) initContext.lookup("java:/comp/env");
				dataSource = (DataSource) evnContext.lookup("jdbc//UserChat");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//언어이름과 count수 가져오기
		public ArrayList<LangDTO> getLangList(String type, String lang){
			ArrayList<LangDTO> dataList =null;
			Connection conn = null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			String SQL ="select b.date, ifnull(cnt,0) cnt "
					+ "from("
					+ "        select DATE_FORMAT(mapping_Date,\"%Y-%m\") as date, count(*) as cnt"
					+ "        from "+type+" join tech"
					+ "        on "+type+".techID = tech.techID"
					+ "        where tech.techName =  \""+lang+"\""
					+ "        group by date"
					+ ")a right join("
					+ "    select date_format( temp_Date,'%Y-%m') as date"
					+ "    from temp"
					+ ")b "
					+ "on b.date = a.date "
					+ "where b.date <= DATE_FORMAT(now(),\"%Y-%m\") and "
					+ " b.date > DATE_FORMAT(DATE_SUB(now(), INTERVAL 6 MONTH),\"%Y-%m\")";
			try {
				conn = dataSource.getConnection();
				pstmt =conn.prepareStatement(SQL);
				rs = pstmt.executeQuery();
				
				dataList = new ArrayList<LangDTO>();
				while(rs.next())  {
					LangDTO data = new LangDTO();
					data.setDate(rs.getString("date"));
					data.setData(rs.getInt("cnt"));					
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
			return dataList; // 리스트 반환
		}//getChatList END
}
