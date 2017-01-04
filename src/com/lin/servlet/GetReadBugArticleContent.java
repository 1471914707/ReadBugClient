package com.lin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.readbug.bean.Admins;
import com.readbug.bean.Article;
import com.readbug.linkDB.ReadBugDataBase;

/**
 * Servlet implementation class GetReadBugArticleContent
 */
@WebServlet("/GetReadBugArticleContent")
public class GetReadBugArticleContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReadBugArticleContent() {
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
		// TODO Auto-generated method stu
		int artId = Integer.parseInt(request.getParameter("artId"));
		Article art = (Article)ReadBugDataBase.load(Article.class, artId);
		JSONArray array = new JSONArray();
		JSONObject member = new JSONObject();
		 response.setContentType("text/html;charset=utf-8");
		 art.setPageView(art.getPageView()+1);
		 ReadBugDataBase.update((art));
		 if (art !=null){
			 member.put("title", art.getTitle());
			 member.put("content",art.getContent());
			 member.put("time", art.getLastTime());
			 member.put("name", ((Admins)ReadBugDataBase.load(Admins.class, art.getUserId())).getName());
			 array.add(member);
		 }
		 
		PrintWriter out = response.getWriter();
		out.print(array.toString());
		out.flush();	
	
	}

}
