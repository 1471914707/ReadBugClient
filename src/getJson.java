

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
import com.readbug.linkDB.ReadBugDataBase;

/**
 * Servlet implementation class getJson
 */
@WebServlet("/getJson")
public class getJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject member = new JSONObject();
		 response.setContentType("text/html;charset=utf-8");
		List list = ReadBugDataBase.list(Article.class);
		for (int i=0; i<list.size(); i++){
			Article article = (Article)list.get(i);
			member.put("title", article.getTitle());
			member.put("content", article.getContent());
			member.put("lastTime", article.getLastTime());
			array.add(member);
			member.clear();
		}
		//json.put( "1", array);
		PrintWriter out = response.getWriter();
		out.print(array.toString());
		out.flush();		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
