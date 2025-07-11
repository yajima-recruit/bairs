import bean.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/signup_profile")
public class SignUpProfile extends HttpServlet {
    // postで来た時の処理
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // リクエストの文字コード指定
        req.setCharacterEncoding("utf-8");

        res.setContentType("text/html;charset=utf-8");
        
        // クエリパラメータから情報を取得
        String icon = req.getParameter("selecticon");
        String color = req.getParameter("color");
        String name = req.getParameter("name");
        long snum = Long.parseLong(req.getParameter("snum"));
        boolean snumpublic = Boolean.valueOf(req.getParameter("snumpublic"));
        String gender = req.getParameter("gender");
        String bloodtype = req.getParameter("bloodtype");
        String[] hobby = req.getParameterValues("hobby");
        String work = req.getParameter("introduction");
        char work2;
        String introduction = "";
        for(int i = 0; i < work.length(); i++) {
            work2 = work.charAt(i);
            if('\n'==work2){
                introduction += "<br>";
            } else {
                introduction += String.valueOf(work2);
            }
        }
        String instagram = req.getParameter("instagram");
        String x = req.getParameter("x");
        String tiktok = req.getParameter("tiktok");

        // ここでセッションを取得する
        HttpSession session = req.getSession(false);
        ProfileBean pb = null;
        try{
            // セッションから情報を取得
            pb = (ProfileBean)session.getAttribute("pb");
        } catch(Exception e){}
        if(pb!=null){
            // Beanに情報を登録
            pb.setIcon(icon);
            pb.setColor(color);
            pb.setName(name);
            pb.setSnum(snum);
            pb.setSnumpublic(snumpublic);
            pb.setGender(gender);
            pb.setBloodtype(bloodtype);
            pb.setHobby(hobby);
            pb.setIntroduction(introduction);
            pb.setInstagram(instagram);
            pb.setX(x);
            pb.setTiktok(tiktok);

            // 確認画面の送信
            req.setAttribute("pb",pb);

            // セッションの保存
            session.setAttribute("pb",pb);

            // signup_check.jspへフォワード
            RequestDispatcher rd = req.getRequestDispatcher("/signup_check.jsp");
            rd.forward(req, res);
        } else {
            System.out.println("不正アクセス検知(signup)");
            RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
            rd.forward(req, res);
        }
    }

    // getで来た時の処理
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("不正アクセス検知(signup)");
        RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
        rd.forward(req, res);
	}
}