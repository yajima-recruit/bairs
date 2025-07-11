import bean.*;
import dao.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/register")
public class SignUpRegister extends HttpServlet {
    // postで来た時の処理
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // リクエストの文字コード指定
        req.setCharacterEncoding("utf-8");

        res.setContentType("text/html;charset=utf-8");

        // ここでセッションを取得する
        HttpSession session = req.getSession(false);
        ProfileBean pb = null;
        try{
            // セッションから情報を取得
            pb = (ProfileBean)session.getAttribute("pb");
        } catch(Exception e){
        }

        if(pb!=null){
            // Beanから情報を取得
            String userid = pb.getUserid();
            String pass = pb.getPass();
            String question = pb.getQuestion();
            String answer = pb.getAnswer();
            String icon = pb.getIcon();
            String color = pb.getColor();
            String name = pb.getName();
            long snum = pb.getSnum();
            boolean snumpublic = pb.getSnumpublic();
            String gender = pb.getGender();
            String bloodtype = pb.getBloodtype();
            String[] hobby = pb.getHobby();
            String introduction = pb.getIntroduction();
            String instagram = pb.getInstagram();
            String x = pb.getX();
            String tiktok = pb.getTiktok();

            // 変数定義
            boolean result = false; 
            String message = "false";

            // ハッシュ値を生成するクラスを作る
            CreateHashBean chb = new CreateHashBean();
            String[] generatedData = chb.createhash(pass);
            // nullが来た時の処理を追加する

            // データベース登録のクラスを作る
            try{
                StoreInStudentDao studentDao = new StoreInStudentDao();
                result = studentDao.registerUser(userid, generatedData[0], question, icon, color, name, snum, snumpublic, gender, bloodtype);
                if(!result) throw new Exception("");
                result = studentDao.registerUser(userid, introduction, instagram, x, tiktok);
                if(!result) throw new Exception("");

                StoreInSecretDao secretDao = new StoreInSecretDao();
                result = secretDao.registerSecret(userid, answer);
                if(!result) throw new Exception("");

                StoreInHobbyDao hobbyDao = new StoreInHobbyDao();
                result = hobbyDao.registerHobby(userid, hobby);
                if(!result) throw new Exception("");

                StoreInSaltDao saltDao = new StoreInSaltDao();
                result = saltDao.registerSalt(userid, generatedData[1]);
                if(!result) throw new Exception("");

                StoreInStretchingDao stretchingDao = new StoreInStretchingDao();
                result = stretchingDao.registerStretching(userid, Integer.parseInt(generatedData[2]));
                if(!result) throw new Exception("");

                // ユーザーIDをセッションに保存
                session.setAttribute("userid", userid);
                session.setAttribute("username", name);

                // いらないprofileのセッションを削除
                session.removeAttribute("pb");

                System.out.println("登録が完了しました(register)");

                // 全てがうまくいった場合
                message = "true";
            } catch(Exception e){
                System.out.println("データベースのエラーを検知(register)");
                try{
                    DeleteAccountDao deleteDao = new DeleteAccountDao();
                    deleteDao.deleteAccount(userid);
                } catch(Exception f) {}
            } finally {
                PrintWriter out = res.getWriter();
                out.println(message);
            }
        } else {
            System.out.println("不正アクセス検知(register)");
            RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
            rd.forward(req, res);
        }
    }

    // getで来た時の処理
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("不正アクセス検知(register)");
        RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
        rd.forward(req, res);
	}
}