package com.linkinone.Lio.research;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ResearchAddServlet")
public class ResearchAddServlet extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		ResearchDAO DB = new ResearchDAO();
		
		if(DB.checkOverlap(request)) {
			DB.insertResearch(request, DB.getTechnum(request));
		}
		
		response.sendRedirect("/user-info-modify.html#tech-modal");
	}
}
	

