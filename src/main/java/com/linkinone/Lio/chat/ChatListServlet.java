package com.linkinone.Lio.chat;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ChatListServlet")
public class ChatListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("/WEB-INF/jsp/chat.jsp").forward(request, response);
	}
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String fromID = request.getParameter("fromID");
		String toID = request.getParameter("toID");
		String listType = request.getParameter("listType");
		
		
		//사용자 대상자 메시지중 하나라도 값이 없다면
		if(fromID == null || fromID.equals("") || toID ==null || toID.equals("")
				|| listType==null || listType.equals(""))
			response.getWriter().write("");
		else {
			try {
				response.getWriter().write(getID(URLDecoder.decode(fromID,"UTF-8"), URLDecoder.decode(toID,"UTF-8"), listType));
			} catch (Exception e) {
				response.getWriter().write("");
			}
		}
	}//doPost()) END
	
	//100개 가져오기
	public String getTen(String fromID, String toID) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByRecent(fromID, toID, 100);
		if(chatList.size() == 0) return "";
		for(int i = 0; i <chatList.size(); i++) {
			result.append("[{\"value\":\""+  chatList.get(i).getFromID() + "\"},");
			result.append("{\"value\":\""+  chatList.get(i).getToID() + "\"},");
			result.append("{\"value\":\""+  chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\":\""+  chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() -1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size()-1).getChatID() + "\"}");
		chatDAO.readChat(fromID, toID);
		return result.toString();
	}
	
	
	// chat id 가져오기
	public String getID(String fromID, String toID, String chatID) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ChatDAO chatDAO = new ChatDAO();
		ArrayList<ChatDTO> chatList = chatDAO.getChatListByID(fromID, toID, chatID);
		if(chatList.size() == 0) return "";
		for(int i = 0; i <chatList.size(); i++) {
			result.append("[{\"value\":\""+  chatList.get(i).getFromID() + "\"},");
			result.append("{\"value\":\""+  chatList.get(i).getToID() + "\"},");
			result.append("{\"value\":\""+  chatList.get(i).getChatContent() + "\"},");
			result.append("{\"value\":\""+  chatList.get(i).getChatTime() + "\"}]");
			if(i != chatList.size() -1) result.append(",");
		}
		result.append("], \"last\":\"" + chatList.get(chatList.size()-1).getChatID() + "\"}");
		chatDAO.readChat(fromID, toID);
		return result.toString();
	}
}//class END
