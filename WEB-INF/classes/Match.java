import bean.*;
import dao.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/match")
public class Match extends HttpServlet {
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
            // セッションから情報を取得
            userid = (String)session.getAttribute("userid");
        } catch(Exception e){}
        if(userid!=null){
            // MatchDaoのインスタンスを作成
            MatchDao matchDao = new MatchDao();

            // DAOのmatchingメソッドを呼び出し
            String[][] matchResult = matchDao.matching(userid);

            // リクエストオブジェクトに保存する
            req.setAttribute("matchResult", matchResult);

            // match画面をjspで作り転送
            RequestDispatcher rd = req.getRequestDispatcher("/match.jsp");
            rd.forward(req, res);
        } else {
            System.out.println("不正アクセス検知(match)");
            RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
            rd.forward(req, res);
        }
        
    }

    // getで来た時の処理
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doPost(req, res);
	}
}