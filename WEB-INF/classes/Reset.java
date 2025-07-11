import bean.*;
import dao.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;

@WebServlet("/reset")
public class Reset extends HttpServlet {
    // postで来た時の処理
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // リクエストの文字コード指定
        req.setCharacterEncoding("utf-8");

        // 出力のコンテンツタイプを指定
        res.setContentType("text/html;charset=utf-8");

        String message = "";
        PrintWriter out = res.getWriter();
        
        String state = req.getParameter("state");
        HttpSession session = req.getSession(true);
        int challenge = 10;
        try{
            // セッションから情報を取得
            challenge = (int)session.getAttribute("challenge");
        } catch(Exception a){}
            String userid = null;
            String question = null;
            String result = null;
            AccountSearchDao asd = null;
            switch(state){
                case "search":
                    userid = req.getParameter("userid");
                    System.out.println("ユーザーIDの探索をします(reset)");

                    // データベース参照のクラスを作る
                    asd = new AccountSearchDao();
                    question = asd.select_question(userid);

                    if(question!=null){
                        message = "true";
                        // セッションに保存
                        session.setAttribute("userid", userid);
                        session.setAttribute("question", question);
                    } else {
                        challenge--;
                        if(challenge < 1) message = "hack";
                        else message = "そのユーザーIDは存在しません";
                    }
                    System.out.println(message+"(reset)");
                    
                    // 結果を返す true or メッセージ
                    out.println(message);
                    
                    break;

                case "move1":
                    try{
                        // セッションから情報を取得
                        question = (String)session.getAttribute("question");
                    } catch(Exception e){}
                    if(question!=null){
                        // 秘密のpass用のチャレンジ回数を設定
                        challenge = 5;
                        session.removeAttribute("question");
                        req.setAttribute("question", question);
                        RequestDispatcher rd = req.getRequestDispatcher("passreset2.jsp");
                        rd.forward(req, res);
                    } else {
                        challenge=0;
                        System.out.println("不正アクセス検知(reset)");
                        RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
                        rd.forward(req, res);
                    }
                    break;

                case "verification":
                    try{
                        // セッションから情報を取得
                        userid = (String)session.getAttribute("userid");
                    } catch(Exception e){}
                    if(userid!=null){
                        // JSONデータを読み取る
                        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
                        String json = "";
                        String line;
                        while ((line = reader.readLine()) != null) {
                            json += line;
                        }

                        JsonParserBean jpb = new JsonParserBean();
                        Map<String, String> dictionary = jpb.parseJson(json);

                        String answer = dictionary.get("answer");

                        asd = new AccountSearchDao();
                        String solution = asd.select_answer(userid);

                        if(answer.equals(solution)){
                            result = "true";
                            session.setAttribute("result", result);
                        } else {
                            challenge--;
                            if(challenge < 1) result="hack";
                            else result = "答えが違います";
                        }
                        out.println(result);
                    } else {
                        challenge=0;
                        System.out.println("不正アクセス検知(reset)");
                        RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
                        rd.forward(req, res);
                    }
                    break;
                case "move2":
                    try{
                        // セッションから情報を取得
                        userid = (String)session.getAttribute("userid");
                        result = (String)session.getAttribute("result");
                    } catch(Exception e){}
                    if(userid!=null && "true".equals(result)){
                        req.setAttribute("userid", userid);
                        RequestDispatcher rd = req.getRequestDispatcher("passreset3.jsp");
                        rd.forward(req, res);
                    } else {
                        challenge=0;
                        System.out.println("不正アクセス検知(reset)");
                        RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
                        rd.forward(req, res);
                    }
                    break;

                case "reset":
                    try{
                        // セッションから情報を取得
                        result = (String)session.getAttribute("result");
                        userid = (String)session.getAttribute("userid");
                    } catch(Exception e){}

                    if(result!=null && userid!=null && "true".equals(result)){
                        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
                        String json = "";
                        String line;
                        while ((line = reader.readLine()) != null) {
                            json += line;
                        }

                        JsonParserBean jpb = new JsonParserBean();
                        Map<String, String> dictionary = jpb.parseJson(json);

                        String pass = dictionary.get("pass");
                        
                        CreateHashBean chb = new CreateHashBean();
                        String[] generatedData = chb.createhash(pass);
                        message = "エラーが発生しました<br>もう一度お試しください";

                        boolean success = false;
                        try{
                            UpdateDataDao udd = new UpdateDataDao();
                            success = udd.update_hash(userid, generatedData[0]);
                            if(!success) throw new Exception("");
                            success = udd.update_salt(userid, generatedData[1]);
                            if(!success) throw new Exception("");
                            success = udd.update_stretching(userid, Integer.parseInt(generatedData[2]));
                            if(!success) throw new Exception("");
                            // すべてうまくいった場合
                            message = "true";
                            session.removeAttribute("userid");
                            session.removeAttribute("result");
                            session.removeAttribute("challenge");
                        } catch(Exception f) {
                            System.out.println("更新中にエラーが発生しました");
                        }
                        out.println(message);
                        
                    } else {
                        challenge=0;
                        System.out.println("不正アクセス検知(reset)");
                        RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
                        rd.forward(req, res);
                    }
                    break;
                
                default:
                    System.out.println("不正アクセス検知(reset)");
                    RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
                    rd.forward(req, res);
            }
            session.setAttribute("challenge",challenge);
    }

    // getで来た時の処理
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("不正アクセス検知(reset)");
        RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
        rd.forward(req, res);
	}
}