import bean.*;
import dao.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/edit_profile")
public class EditProfile extends HttpServlet {
    // postで来た時の処理
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // リクエストの文字コード指定
        req.setCharacterEncoding("utf-8");

        res.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession(false);
        String userid = null;
        try{
            userid = (String)session.getAttribute("userid");
        } catch(Exception e) {}
        if(userid!=null){
            String state = req.getParameter("state");
            ProfileBean pb = new ProfileBean();
            if("edit".equals(state)){
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
                
                UpdateProfileDao upd = new UpdateProfileDao();
                boolean result = false;
                try{
                    result = upd.update_profile(userid,icon,color,name,snum,snumpublic,gender,bloodtype);
                    if(!result) throw new Exception("");
                    result = upd.update_profile(userid,introduction,instagram,x,tiktok);
                    if(!result) throw new Exception("");
                    result = upd.update_profile(userid,hobby);
                    if(!result) throw new Exception("");
                    System.out.println("プロフィールの更新が完了しました(edit)");
                } catch(Exception e){
                    System.out.println("データベースのエラーを検知(edit)");
                }
                if(result){
                    RequestDispatcher rd = req.getRequestDispatcher("/home.jsp");
                    rd.forward(req, res);
                } else {
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

                    req.setAttribute("pb", pb);
                    RequestDispatcher rd = req.getRequestDispatcher("/edit_profile.jsp");
                    rd.forward(req, res);
                }
            } else if("move".equals(state)){
                GetUserDao gud = new GetUserDao();
                boolean result = gud.get_profile(userid);
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

                    req.setAttribute("pb", pb);
                    RequestDispatcher rd = req.getRequestDispatcher("/edit_profile.jsp");
                    rd.forward(req, res);
                } else {
                    System.out.println("不正アクセス検知(edit)");
                    RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
                    rd.forward(req, res);
                }
            } else {
                System.out.println("不正アクセス検知(edit)");
                RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
                rd.forward(req, res);
            }
        } else{
            System.out.println("不正アクセス検知(edit)");
            RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
            rd.forward(req, res);
        }
    }

    // getで来た時の処理
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("不正アクセス検知(edit)");
        RequestDispatcher rd = req.getRequestDispatcher("/hacking.jsp");
        rd.forward(req, res);
	}
}