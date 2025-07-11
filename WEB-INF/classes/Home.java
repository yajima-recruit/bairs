import dao.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/home")
public class Home extends HttpServlet {
    // postで来た時の処理
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // リクエストの文字コード指定
        req.setCharacterEncoding("utf-8");

        // 出力のコンテンツタイプを指定
        res.setContentType("text/html;charset=utf-8");

        // セッションオブジェクトから学籍番号を取得
        HttpSession session = req.getSession(false);
        String userid = null;
        try{
            userid = (String)session.getAttribute("userid");
        } catch(Exception e) {}
        if(userid!=null){
            GetUserDao gud = new GetUserDao();
            String[][] data = null;
            try{
                data = gud.get_random_users(userid);
            } catch (Exception f){}
            // home画面をjspで作り転送
            req.setAttribute("data", data);
            RequestDispatcher rd = req.getRequestDispatcher("/home.jsp");
            rd.forward(req, res);
        } else {
            System.out.println("不正アクセス検知(home)");
            RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
            rd.forward(req, res);
        }
    }

    // getで来た時の処理
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doPost(req, res);
	}
}