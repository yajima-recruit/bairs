package dao;

import java.sql.*;

public class StoreInStudentDao extends DatabaseConnector{
    public boolean registerUser(String userid, String hash, String question, String icon, String color, String name, long snum, boolean snumpublic, String gender, String bloodtype) {
        String sql = "INSERT INTO student(ユーザーID, ハッシュ値, 質問, ファイル名, 背景色, ニックネーム, 学籍番号, 学籍番号の公開, 性別, 血液型) " +
                                  "VALUES ('"+userid+"','"+hash+"','"+question+"','"+icon+"','"+color+"','"+name+"','"+snum+"',"+snumpublic+",'"+gender + "','" + bloodtype + "')";
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
            System.out.println("studentテーブルの登録に失敗しました(register)");
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

    public boolean registerUser(String userid, String introduction, String instagram, String x, String tiktok){
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
            System.out.println("studentテーブルの登録に失敗しました(register)");
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
}