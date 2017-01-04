package com.lin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.readbug.bean.Users;
import com.readbug.linkDB.ReadBugDataBase;

/**
 * Servlet implementation class HandleUserReg
 */
@WebServlet("/HandleUserReg")
public class HandleUserReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleUserReg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONArray array = new JSONArray();
		JSONObject member = new JSONObject();
		 response.setContentType("text/html;charset=utf-8");
		
		String iphone = request.getParameter("iphone");
		String password = request.getParameter("password");
		
		List list = (List)ReadBugDataBase.select(Users.class, "iphone="+iphone);
		
		if (list.size() == 0){
			Users user = new Users();
			user.setIphone(iphone);
			user.setPassword(password);
			user.setIsactive(1);
			ReadBugDataBase.save(user);
			member.put("iphone", iphone);
			array.add(member);
		}
		
		PrintWriter out = response.getWriter();
		out.print(array.toString());
		out.flush();	
	}

}
