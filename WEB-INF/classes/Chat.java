import bean.*;
import dao.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/chat")
public class Chat extends HttpServlet {
    // postで来た時の処理
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // リクエストの文字コード指定
        req.setCharacterEncoding("utf-8");

        res.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession(false);
        String userid = null;
        String username = null;
        try{
            userid = (String)session.getAttribute("userid");
            username = (String)session.getAttribute("username");
        } catch(Exception e) {}
        if(userid!=null && username1=null){
            
            RequestDispatcher rd = req.getRequestDispatcher("/chat.jsp");
            rd.forward(req, res);
        } else{
            System.out.println("不正アクセス検知(chat)");
            RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
            rd.forward(req, res);
        }
    }

    // getで来た時の処理
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("不正アクセス検知(chat)");
        RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
        rd.forward(req, res);
	}
}