import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    // postで来た時の処理
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // リクエストの文字コード指定
        req.setCharacterEncoding("utf-8");

        // 出力のコンテンツタイプを指定
        res.setContentType("text/html;charset=utf-8");
        
        // セッションを取得
        HttpSession session = req.getSession(false);

        if (session != null) {
            String userid = null;
            try{
                userid = (String)session.getAttribute("userid");
            } catch(Exception e){}
            if(userid!=null){
                System.out.println("セッション消去:"+userid);
            }
            // セッションが存在する場合、セッションを無効にする（削除）
            session.invalidate();
        }

        PrintWriter out = res.getWriter();
        out.println("true");
    }

    // getで来た時の処理
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doPost(req, res);
	}
}