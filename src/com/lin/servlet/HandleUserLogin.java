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

import com.readbug.bean.Article;
import com.readbug.bean.Users;
import com.readbug.linkDB.ReadBugDataBase;

/**
 * Servlet implementation class HandleUserLogin
 */
@WebServlet("/HandleUserLogin")
public class HandleUserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleUserLogin() {
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
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		List  list = ReadBugDataBase.select(Users.class, "iphone="+account);
		
		if (list.size() != 0){
			Users user = (Users) list.get(0);
			if (user.getIsactive() != 0){
				if (password.equals(user.getPassword())){
					member.put("iphone", user.getIphone());
					array.add(member);
				}
			}
		}
		
		PrintWriter out = response.getWriter();
		out.print(array.toString());
		out.flush();	
	}

}
