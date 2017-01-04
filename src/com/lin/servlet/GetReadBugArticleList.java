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

import com.readbug.bean.Admins;
import com.readbug.bean.Article;
import com.readbug.bean.Articletype;
import com.readbug.linkDB.ReadBugDataBase;

/**
 * Servlet implementation class HandleArticleList
 */
@WebServlet("/GetReadBugArticleList")
public class GetReadBugArticleList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReadBugArticleList() {
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
		int type = Integer.parseInt(request.getParameter("type"));
		int artId =  Integer.parseInt(request.getParameter("artId"));
		List articleList = null;
		if (type ==0){
			articleList = ReadBugDataBase.select(Article.class, "id>"+artId+" ORDER BY lastTime DESC limit 30");// ORDER BY lastTime DESC
		}else{
			articleList = ReadBugDataBase.select(Article.class, "id>"+artId+" and type="+type+" ORDER BY lastTime DESC limit 30");
		}
		/*articleList = ReadBugDataBase.select(Article.class, "id");*/
		
		JSONArray array = new JSONArray();
		JSONObject member = new JSONObject();
		 response.setContentType("text/html;charset=utf-8");
		 int num = articleList.size();
		 if (num > 30){
			 num = 30;
		 }
		 for (int i=0; i<num; i++){
			 Article art = (Article)articleList.get(i);
			 Articletype types = (Articletype)ReadBugDataBase.load(Articletype.class, art.getType());
				member.put("id", art.getId());
				member.put("type", types.getName());
				member.put("title", art.getTitle());
				member.put("pageView", art.getPageView());
				array.add(member);
				member.clear();
		 }
		 
			PrintWriter out = response.getWriter();
			out.print(array.toString());
			out.flush();	
	}

}
