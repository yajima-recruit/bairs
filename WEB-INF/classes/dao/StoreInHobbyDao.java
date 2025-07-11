package dao;

import java.sql.*;

public class StoreInHobbyDao extends DatabaseConnector{
    public boolean registerHobby(String userid, String[] hobby){
        String sql = "INSERT INTO hobby VALUES(?, ?)";
        PreparedStatement ps = null;
        boolean result = false;
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
            System.out.println("hobbyテーブルの登録に失敗しました(register)");
        } finally {
            try{
                if(ps != null) ps.close();
                super.disconnect();
            }
            catch(Exception e){
                System.out.println("データベースの切り離し中にエラーが発生しました");
            }
        }
        
        return result;
    }
}