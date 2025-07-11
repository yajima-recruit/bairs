package dao;

import java.sql.*;

public class UpdateProfileDao extends DatabaseConnector{
    public boolean update_profile(String userid, String icon, String color, String name, long snum, boolean snumpublic, String gender, String bloodtype) {
        String sql = "UPDATE student SET ファイル名 = '"+icon+"',背景色 = '"+color+"',ニックネーム = '"+name+"',学籍番号 = "+snum+",学籍番号の公開 = "+snumpublic+",性別 = '"+gender+"',血液型 = '"+bloodtype+"' WHERE ユーザーID = '"+userid+"'";
        Statement stmt = null;
        ResultSet rs = null;
        int result_int = 0;
        boolean result = false;
        try {
            super.connect("bairsdb");
            // ユーザー登録
            stmt = super.con.createStatement();
            result_int = stmt.executeUpdate(sql);
            result = true;
        } catch(Exception e) {
            System.out.println("データの更新に失敗しました(edit)");
        } finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                super.disconnect();
            }
            catch(Exception e){
                System.out.println("データベースの切り離し中にエラーが発生しました");
            }
        }
        
        return result;
    }

    public boolean update_profile(String userid, String introduction, String instagram, String x, String tiktok) {
        String sql;
        Statement stmt = null;
        ResultSet rs = null;
        int result_int = 0;
        boolean result = false;
        try {
            super.connect("bairsdb");
            stmt = super.con.createStatement();
            if(introduction!=null){
                sql = "UPDATE student SET 自己紹介 = '"+introduction+"' WHERE ユーザーID = '"+userid+"'";
                result_int = stmt.executeUpdate(sql);
            }
            if(instagram!=null){
                sql = "UPDATE student SET Instagram = '"+instagram+"' WHERE ユーザーID = '"+userid+"'";
                result_int = stmt.executeUpdate(sql);
            }
            if(x!=null){
                sql = "UPDATE student SET X = '"+x+"' WHERE ユーザーID = '"+userid+"'";
                result_int = stmt.executeUpdate(sql);
            }
            if(tiktok!=null){
                sql = "UPDATE student SET TikTok = '"+tiktok+"' WHERE ユーザーID = '"+userid+"'";
                result_int = stmt.executeUpdate(sql);
            }
            result = true;
        } catch(Exception e) {
            System.out.println("データの更新に失敗しました(edit)");
        } finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                super.disconnect();
            }
            catch(Exception e){
                System.out.println("データベースの切り離し中にエラーが発生しました");
            }
        }
        
        return result;
    }

    public boolean update_profile(String userid, String[] hobby) {
        String sql;
        Statement stmt = null;
        ResultSet rs = null;
        int result_int = 0;
        boolean result = false;

        try {
            super.connect("bairsdb");
            stmt = super.con.createStatement();
            sql = "DELETE FROM hobby WHERE ユーザーID = '"+userid+"'";
            result_int = stmt.executeUpdate(sql);
            result = true;
        } catch(Exception e) {
            System.out.println("hobbyテーブルの削除に失敗しました(edit)");
        } finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                super.disconnect();
            }
            catch(Exception e){
                System.out.println("データベースの切り離し中にエラーが発生しました");
            }
        }

        if(result){
            sql = "INSERT INTO hobby VALUES(?, ?)";
            PreparedStatement ps = null;
            result = false;
            try{
                super.connect("bairsdb");
                // ステートメントを生成
                ps = super.con.prepareStatement(sql);
                // SQLを実行
                for(int i = 0; i < hobby.length; i++){
                    ps.setString(1, userid); 
                    ps.setString(2, hobby[i]);
                    ps.executeUpdate();
                }
                result = true;
            } catch(Exception e){
                System.out.println("データの更新に失敗しました(edit)");
            } finally {
                try{
                    if(ps != null) ps.close();
                    super.disconnect();
                }
                catch(Exception e){
                    System.out.println("データベースの切り離し中にエラーが発生しました");
                }
            }
        }
        
        return result;
    }

}