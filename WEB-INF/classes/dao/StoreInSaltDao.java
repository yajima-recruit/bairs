package dao;

import java.sql.*;

public class StoreInSaltDao extends DatabaseConnector{
    public boolean registerSalt(String userid, String salt) {
        String sql = "INSERT INTO salt VALUES ('" + userid + "','" + salt + "')";
        Statement stmt = null;
        ResultSet rs = null;
        int result = 0;

        try {
            super.connect("saltdb");
            // ユーザー登録
            stmt = super.con.createStatement();
            result = stmt.executeUpdate(sql);
        } catch(Exception e) {
            System.out.println("saltテーブルの登録に失敗しました(register)");
            return false;
        } finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                super.disconnect();
            }
            catch(Exception e){
                System.out.println("データベースの切り離し中にエラーが発生しました");
                return false;
            }
        }
        
        return true;
    }
}