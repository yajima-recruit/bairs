import bean.*;
import dao.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;

@WebServlet("/login")
public class Login extends HttpServlet {
    // postで来た時の処理
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // リクエストの文字コード指定
        req.setCharacterEncoding("utf-8");

        // 出力のコンテンツタイプを指定
        res.setContentType("text/html;charset=utf-8");
        
        String state = req.getParameter("state");
        String message = null;

        // セッションを作る
        HttpSession session = req.getSession(true);
        int challenge = 5;
        try{
            challenge = (int)session.getAttribute("challenge");
        } catch(Exception e) {}
        if("search".equals(state)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
            String json = "";
            String line;
            while ((line = reader.readLine()) != null) {
                json += line;
            }

            JsonParserBean jpb = new JsonParserBean();
            Map<String, String> dictionary = jpb.parseJson(json);

            // ユーザーID、パスワードの取得
            String userid = dictionary.get("userid");
            String pass = dictionary.get("pass");

            System.out.println("ユーザーIDの探索をします(login)");

            // データベース参照のクラスを作る
            AccountSearchDao asd = new AccountSearchDao();
            String hash = asd.select_userid(userid);

            if(hash!=null){
                // 学籍番号の検索に成功した場合
                boolean bool = true;

                // GetSoltDaoクラスからsoltを取得する
                GetSaltDao getsalt = new GetSaltDao();
                String salt = getsalt.GetSalt(userid);

                // GetStretchingDaoクラスからstretchingを取得する
                GetStretchingDao getstretching = new GetStretchingDao();
                int stretching = getstretching.GetStretching(userid);

                if(salt==null || stretching < 10000){
                    bool = false;
                }

                // パスワードの検証を開始
                VerificationBean vb = new VerificationBean();
                if(bool && vb.verification(pass, hash, salt, stretching)){
                    message = "true";

                    String username = asd.select_username(userid);

                    // セッションに保存
                    session.setAttribute("userid", userid);
                    session.setAttribute("username", username);
                } else {
                    System.out.println("パスワードが一致しませんでした(login)");
                    challenge--;
                    if(challenge<1) message = "hack";
                    else message = "ユーザーIDもしくはパスワードが<br>正しくありません";
                }
            } else {
                if(challenge<1) message = "hack";
                else message = "ユーザーIDもしくはパスワードが<br>正しくありません";
            }

            session.setAttribute("challenge",challenge);
            
            System.out.println(message+"(login)");

            // 結果を返す true or メッセージ
            PrintWriter out = res.getWriter();
            out.println(message);
        } else if("move".equals(state)) {
            message = "false";
            String userid = null;
            try{
                // セッションから情報を取得
                userid = (String)session.getAttribute("userid");
            } catch(Exception e){}
            if(userid!=null){
                // home画面行きのメッセージを送信
                message = "true";
                PrintWriter out = res.getWriter();
                out.println(message);
            } else {
                System.out.println("不正アクセス検知(login)");
                RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
                rd.forward(req, res);
            }
        } else {
            System.out.println("不正アクセス検知(login)");
            RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
            rd.forward(req, res);
        }
    }

    // getで来た時の処理
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("不正アクセス検知(login)");
        RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
        rd.forward(req, res);
	}
}