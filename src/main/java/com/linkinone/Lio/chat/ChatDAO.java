package com.linkinone.Lio.chat;

import com.linkinone.Lio.data.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ChatDAO {

	
	//쳇 가져오기
	public ArrayList<ChatDTO> getChatListByID(String fromID, String toID,String chatID){
		ArrayList<ChatDTO> chatList =null;
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String SQL ="SELECT * FROM CHAT WHERE ((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?)) AND chatID > ? ORDER BY chatTime";
		try {
			conn = DBManager.connect();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, Integer.parseInt(chatID));
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while(rs.next()) {
				ChatDTO chat = new ChatDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
			
				//시간 출력
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if(chatTime > 12) {
					timeType ="오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chat);	//리스트의 추가
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return chatList; // 리스트 반환
	}//getChatList END
	
	//최근 대화 목록 몇개만 가져오는 함수
	public ArrayList<ChatDTO> getChatListByRecent(String fromID, String toID,int number){
		ArrayList<ChatDTO> chatList =null;
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String SQL ="SELECT * FROM CHAT WHERE ((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?)) AND chatID > (SELECT MAX(chatID) - ? FROM CHAT WHERE (fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?)) ORDER BY chatTime";
		try {
			conn = DBManager.connect();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, number);
			pstmt.setString(6, fromID);
			pstmt.setString(7, toID);
			pstmt.setString(8, toID);
			pstmt.setString(9, fromID);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while(rs.next()) {
				ChatDTO chat = new ChatDTO();
				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
			
				//시간 출력
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if(chatTime > 12) {
					timeType ="오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chat);	//리스트의 추가
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return chatList; // 리스트 반환
	}//getChatListRec END
	
	// 메세지함 박스
		public ArrayList<ChatDTO> getBox(String userID){
			ArrayList<ChatDTO> chatList =null;
			Connection conn = null;
			PreparedStatement pstmt =null;
			ResultSet rs = null;
			String SQL = "SELECT * FROM CHAT WHERE chatID IN(SELECT MAX(chatID) FROM CHAT WHERE toID = ? OR fromID = ? GROUP BY fromID, toID);";
			try {
				conn = DBManager.connect();
				pstmt =conn.prepareStatement(SQL);
				pstmt.setString(1, userID);
				pstmt.setString(2, userID);
				rs = pstmt.executeQuery();
				chatList = new ArrayList<ChatDTO>();
				while(rs.next()) {
					ChatDTO chat = new ChatDTO();
					chat.setChatID(rs.getInt("chatID"));
					chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
					chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
					chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt").replaceAll("<", "&gt").replaceAll("\n", "<br>"));
				
					//시간 출력
					int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
					String timeType = "오전";
					if(chatTime > 12) {
						timeType ="오후";
						chatTime -= 12;
					}
					chat.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14, 16) + "");
					chatList.add(chat);	//리스트의 추가
				}
				for(int i = 0; i < chatList.size(); i++) {
					ChatDTO x = chatList.get(i);
					for(int j = 0; j < chatList.size(); j++) {
						ChatDTO y = chatList.get(j);
						if(x.getFromID().equals(y.getToID()) && x.getToID().equals(y.getFromID())) {
							if(x.getChatID() < y.getChatID()) {
								chatList.remove(x);
								i--;
								break;
							}else {
								chatList.remove(y);
								j--;
							}
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return chatList; // 리스트 반환
		}//getChatListRec END
	
	public int submit(String fromID, String toID, String chatContent){
		ArrayList<ChatDTO> chatList =null;
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String SQL ="INSERT INTO CHAT VALUES (NULL, ?, ?, ?, NOW(), 0)";
		try {
			conn = DBManager.connect();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, chatContent);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return -1; // 데이터베이스 오류
	}//getChatListRec END
	
	//chat을 읽으면 읽음 표시
	public int readChat(String fromID, String toID) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String SQL ="UPDATE CHAT SET chatRead = 1 WHERE (fromID = ? AND toID =?)";
		try {
			conn = DBManager.connect();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, toID);
			pstmt.setString(2, fromID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return -1; // 데이터베이스 오류
	}
	
	//내가 안읽은 챗 갯수 가져오기
	public int getAllUnreadChat(String userID) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String SQL ="SELECT COUNT(chatID) FROM CHAT WHERE toID = ? AND chatRead =0";
		try {
			conn = DBManager.connect();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("COUNT(chatID)");
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return -1; // 데이터베이스 오류
	}
	
	//특정한 사용자와 얼마나 메시지 않읽었는지 카운트
	public int getUnreadChat(String fromID, String toID) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String SQL ="SELECT COUNT(chatID) FROM CHAT WHERE fromID = ? AND toID = ? AND chatRead =0";
		try {
			conn = DBManager.connect();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("COUNT(chatID)");
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return -1; // 데이터베이스 오류
	}
}//Class END
