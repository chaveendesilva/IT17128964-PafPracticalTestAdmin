package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Servlet implementation class docpaymentAPI
 */
@WebServlet("/AdminAPI")
public class AdminAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Admin adminObj = new Admin();
       
    /**
     * @see HttpServlet#HttpServlet()
     */

    public AdminAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Not USE
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String output = adminObj.insertAdmin(request.getParameter("adminUsername"),
				request.getParameter("adminPassword"),
				request.getParameter("adminReports"));
						
				response.getWriter().write(output); 
		//doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 Map paras = getParasMap(request);
		 String output = adminObj.updateAdmin(paras.get("hidAdminIDSave").toString(),
		 paras.get("adminUsername").toString(),
		 paras.get("adminPassword").toString(),
		 paras.get("adminReports").toString());
		
		 
		 response.getWriter().write(output);
		}
	
		
		

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		 String output = adminObj.deleteAdmin(paras.get("adminID").toString());
		response.getWriter().write(output); 
	}
	
	
	
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
	
	String[] p = param.split("=");
	 map.put(p[0], p[1]);
	 }
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}


}
