package com.linkinone.Lio.data;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/DataTrendServlet")
public class DataTrendServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("json", getLang("Mapping_user"));
		request.setAttribute("contentPage", "/WEB-INF/jsp/graph/userTrend.jsp");
		request.getRequestDispatcher("/WEB-INF/jsp/trends.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String a = request.getParameter("contentPage");

		String json = null;
		if(a.equals("/WEB-INF/jsp/graph/userTrend.jsp")) {
			json = getLang("Mapping_user");
		} else if(a.equals("/WEB-INF/jsp/graph/boardTrend.jsp")) {
			json = getLang("Mapping_board");
		} else if(a.equals("/WEB-INF/jsp/graph/langUserTrend.jsp")) {
			String lang = request.getParameter("languge"); 
			if(lang.equals("undefined")) {
				lang="C";
			}
			System.out.println(lang);

			
			json = getLang2("Mapping_user", lang);
			
			request.setAttribute("languge", lang);
		} else if(a.equals("/WEB-INF/jsp/graph/langBoardTrend.jsp")) {
			String lang = request.getParameter("languge"); 
			if(lang.equals("undefined")) {
				lang="C";
			}
			
			
			json = getLang2("Mapping_board", lang);
			
			request.setAttribute("languge", lang);
		}
	
		request.setAttribute("json", json);
		request.setAttribute("contentPage", a);
		request.getRequestDispatcher("/WEB-INF/jsp/trends.jsp").forward(request, response);
	}
	
	public String getLang(String type) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"cols\":[");
		result.append("{\"type\":\"string\"},{\"type\":\"number\"}],");
		result.append("\"rows\":[");
		ArrayList<DataDTO> dataList = new DataDAO().getLangList(type);
		if(dataList == null) return "";
		for(int i = 0; i <dataList.size(); i++) {
			result.append("{\"c\":[{\"v\":\""+  dataList.get(i).getLang() + "\"},");
			result.append("{\"v\":"+  dataList.get(i).getCnt() + "}]}");
			if(i != dataList.size() -1) result.append(",");
		}
		result.append("]}");
		System.out.print(result);
		return result.toString();
	}
	
	public String getLang2(String type, String lang) {
		StringBuffer result = new StringBuffer("");
		result.append("{\"cols\":[");
		result.append("{\"type\":\"string\"},{\"type\":\"number\"}],");
		result.append("\"rows\":[");
		ArrayList<LangDTO> dataList = new LangDAO().getLangList(type, lang);
		if(dataList.size() == 0) return "";
		for(int i = 0; i <dataList.size(); i++) {
			result.append("{\"c\":[{\"v\":\""+  dataList.get(i).getDate() + "\"},");
			result.append("{\"v\":"+  dataList.get(i).getData() + "}]}");
			if(i != dataList.size() -1) result.append(",");
		}
		result.append("]}");
		return result.toString();
	}

}
