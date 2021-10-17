package com.linkinone.Lio.research;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.linkinone.Lio.chat.ChatDAO;
import com.linkinone.Lio.chat.ChatDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@WebServlet("/ResearchListServlet")
public class ResearchListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userID = auth.getName();
		
		//사용자 대상자 메시지중 하나라도 값이 없다면
		if(userID == null || userID.equals(""))
			response.getWriter().write("");
		else {
			try {
				response.getWriter().write(getResearch(userID));
			} catch (Exception e) {
				response.getWriter().write("");
			}
		}
	}
	
	// chat id 가져오기
	public String getResearch(String userID) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		ResearchDAO ResearchDAO = new ResearchDAO();
		ArrayList<String> researchList = ResearchDAO.getResearchListByID(userID);
		if(researchList.size() == 0) return "";
		for(int i = 0; i <researchList.size(); i++) {
			result.append("{\"value\":\""+  researchList.get(i) + "\"}");
			if(i != researchList.size() -1) result.append(",");
		}
		result.append("], \"last\":\"" + researchList.get(researchList.size()-1) + "\"}");
		
		
		return result.toString();
	}

}
