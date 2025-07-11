package dao;

import java.sql.*;

public class StoreInSecretDao extends DatabaseConnector{
    public boolean registerSecret(String userid, String answer) {
        String sql = "INSERT INTO secret VALUES ('" + userid + "','" + answer + "')";
        Statement stmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            super.connect("bairsdb");
            // ユーザー登録
            stmt = super.con.createStatement();
            result = stmt.executeUpdate(sql);
        } catch(Exception e) {
            System.out.println("secretテーブルの登録に失敗しました(register)");
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