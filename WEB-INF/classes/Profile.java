import bean.*;
import dao.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/profile")
public class Profile extends HttpServlet {
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
            ProfileBean pb = new ProfileBean();
            String state = req.getParameter("state");
            boolean result = false;
            if("myprofile".equals(state)){
                result = gud.get_profile(userid);
            } else {
                String targetid = req.getParameter("userid");
                if(userid.equals(targetid)) state = "myprofile";
                result = gud.get_profile(targetid);
            }
            if(result){
                pb.setIcon(gud.getIcon());
                pb.setColor(gud.getColor());
                pb.setName(gud.getName());
                pb.setSnum(gud.getSnum());
                pb.setSnumpublic(gud.getSnumpublic());
                pb.setGender(gud.getGender());
                pb.setBloodtype(gud.getBloodtype());
                pb.setHobby(gud.getHobby());
                pb.setIntroduction(gud.getIntroduction());
                pb.setInstagram(gud.getInstagram());
                pb.setX(gud.getX());
                pb.setTiktok(gud.getTiktok());

                String who = gud.getName();
                if("myprofile".equals(state)){
                    who = "自分";
                }
                
                req.setAttribute("who", who);
                req.setAttribute("pb", pb);
                // profile画面をjspで作り転送
                RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
                rd.forward(req, res);
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
                rd.forward(req, res);
            }
        } else {
            System.out.println("不正アクセス検知(profile)");
            RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
            rd.forward(req, res);
        }
    }

    // getで来た時の処理
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doPost(req, res);
	}
}