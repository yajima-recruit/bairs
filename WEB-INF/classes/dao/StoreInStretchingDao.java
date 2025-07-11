package dao;

import java.sql.*;

public class StoreInStretchingDao extends DatabaseConnector{
    public boolean registerStretching(String userid, int stretching) {
        String sql = "INSERT INTO stretching VALUES ('" + userid + "'," + stretching + ")";
        Statement stmt = null;
        ResultSet rs = null;
        int result_int = 0;
        boolean result = false;
        try {
            super.connect("stretchingdb");
            // ユーザー登録
            stmt = super.con.createStatement();
            result_int = stmt.executeUpdate(sql);
            result = true;
        } catch(Exception e) {
            System.out.println("stretchingテーブルの登録に失敗しました(register)");
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