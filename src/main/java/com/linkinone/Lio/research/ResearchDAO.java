package com.linkinone.Lio.research;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


import com.linkinone.Lio.data.DataDTO;

public class ResearchDAO {
	DataSource dataSource;
	

	public ResearchDAO() {
		try {
			InitialContext initContext = new InitialContext();
			Context evnContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) evnContext.lookup("jdbc//UserChat");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Integer>  getUserLang(HttpServletRequest request) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		HttpSession hs = request.getSession();
		String userID =(String) hs.getAttribute("userID");
		
		String SQL ="select techID from Mapping_user where userID = ?";
		ArrayList<Integer> userLang = new ArrayList<Integer>();
		
		try {
			conn = dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			
			
			while(rs.next())  {
				userLang.add(rs.getInt("techID"));
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
		return userLang;
	}//getChatList END
	
	public void insertResearch(HttpServletRequest request, int techID) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		HttpSession hs = request.getSession();
		String userID =(String) hs.getAttribute("userID");
		
		String SQL ="insert into Mapping_user value(?, ?, now())";
		try {
			conn = dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setInt(2, techID);
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("db 등록 성공");
			}else {
				System.out.println("db 등록 실패");
			}		
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("db 에러");

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
	}//getChatList END
	
	public void deleteResearch(HttpServletRequest request, int no) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		HttpSession hs = request.getSession();
		String userID =(String) hs.getAttribute("userID");
		
		String SQL ="delete from Mapping_user where userID = ? and techID = ?";
		ArrayList<String> userLang = new ArrayList<String>();
		try {
			conn = dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setInt(2, no);
			
			if(pstmt.executeUpdate()==1) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}

			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("db 에러");
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
	}

	public boolean checkOverlap(HttpServletRequest request) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		HttpSession hs = request.getSession();
		String userID =(String) hs.getAttribute("userID");

		
		String SQL ="select userID,techName from Mapping_user m join tech t on m.techID = t.techID  where userID = ? and techName = ?";
		ArrayList<Integer> userLang = new ArrayList<Integer>();
		
		try {
			conn = dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, request.getParameter("tech"));
			rs = pstmt.executeQuery();
			
			
			if(rs.next())  {
				return false;
			}else {
				return true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return true;
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
	}

	public int getTechnum(HttpServletRequest request) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		HttpSession hs = request.getSession();
		String userID =(String) hs.getAttribute("userID");
		
		String SQL ="select techID from tech where techname = ?";
		ArrayList<Integer> userLang = new ArrayList<Integer>();
		
		try {
			conn = dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, request.getParameter("tech"));
			rs = pstmt.executeQuery();
			
			
			if(rs.next())  {
				return rs.getInt("techID");
			}else {
				return 0;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
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
	}
	
	public ArrayList<String> getResearchListByID(String userID) {
		ArrayList<String> ResearchList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String SQL ="select techName from Mapping_user m join tech t on m.techID = t.techID  where userID = ? order by t.techID";
		try {
			conn = dataSource.getConnection();
			pstmt =conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			while(rs.next()) {			
				ResearchList.add(rs.getString("techName"));	//리스트의 추가
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
		return ResearchList; // 리스트 반환
	}
}
