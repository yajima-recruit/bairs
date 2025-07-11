import bean.*;
import dao.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;

@WebServlet("/signup")
public class SignUp extends HttpServlet {
    // postで来た時の処理
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // リクエストの文字コード指定
        req.setCharacterEncoding("utf-8");

        res.setContentType("text/html;charset=utf-8");

        // stateを取得
        String state = req.getParameter("state");

        // メッセージの生成
        String message = "不明なエラーが発生しました";

        // 1回目の場合
        if("search".equals(state)){
            System.out.println("ユーザーIDの探索をします(signup)");
            // 学籍番号の取得
            String userid = req.getParameter("userid");

            // データベース参照のクラスを作る
            AccountSearchDao asd = new AccountSearchDao();
            if(asd.select_userid(userid)==null){
                message = "true";

                // ここでセッションを作成する
                HttpSession session = req.getSession(true);

                // サインアップ用のProfileビーンの作成
                ProfileBean pb = new ProfileBean();
                pb.setUserid(userid);

                // セッションオブジェクトに格納
                session.setAttribute("pb",pb);
            } else
                message = "すでに存在するIDです";
            System.out.println(message+"(signup)");
        } else if("move".equals(state)){
            System.out.println("パスワードの保存を行います(signup)");
            // ここでセッションを取得する
            HttpSession session = req.getSession(false);
            ProfileBean pb = (ProfileBean)session.getAttribute("pb");

            BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
            String json = "";
            String line;
            while ((line = reader.readLine()) != null) {
                json += line;
            }

            JsonParserBean jpb = new JsonParserBean();
            Map<String, String> dictionary = jpb.parseJson(json);

            String pass = dictionary.get("pass");
            pb.setPass(pass);

            String work = dictionary.get("question");
            char work2;
            String question = "";
            for(int i = 0; i < work.length(); i++) {
                work2 = work.charAt(i);
                if('\n'==work2){
                    question += "<br>";
                } else {
                    question += String.valueOf(work2);
                }
            }
            pb.setQuestion(question);

            String answer = dictionary.get("answer");
            pb.setAnswer(answer);

            // セッションオブジェクトに格納
            session.setAttribute("pb",pb);

            message = "true";
        } else {
            System.out.println("不正アクセス検知(signup)");
            RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
            rd.forward(req, res);
        }

        // 結果を返す true or メッセージ
        PrintWriter out = res.getWriter();
        out.println(message);
    }

    // getで来た時の処理
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("不正アクセス検知(signup)");
        RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
        rd.forward(req, res);
	}
}