package dao;

import java.sql.*;

public class DeleteAccountDao extends DatabaseConnector{
    public void deleteAccount(String userid) {
        String sql = "DELETE FROM student WHERE ユーザーID = '" + userid + "'";
        Statement stmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            super.connect("bairsdb");
            // ユーザー登録
            stmt = super.con.createStatement();
            result = stmt.executeUpdate(sql);
        } catch(Exception e) {
            System.out.println("アカウントの削除に失敗しました");
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
    }
}