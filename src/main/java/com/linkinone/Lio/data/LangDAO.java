package com.linkinone.Lio.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LangDAO {
		//언어이름과 count수 가져오기
		public ArrayList<LangDTO> getLangList(String type, String lang){
			ArrayList<LangDTO> dataList =null;
			Connection conn = null;
			PreparedStatement pstmt =null;
		    ResultSet rs = null;
			String SQL ="select b.date, ifnull(cnt,0) cnt "
					+ "from("
					+ "        select DATE_FORMAT(created_Date,\"%Y-%m\") as date, count(*) as cnt"
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
				conn = DBManager.connect();
				pstmt = conn.prepareStatement(SQL);
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
				DBManager.close(conn, pstmt, rs);
			}
			return dataList; // 리스트 반환
		}//getChatList END
}
