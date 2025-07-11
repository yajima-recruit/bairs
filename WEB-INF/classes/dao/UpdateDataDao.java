package dao;

import java.sql.*;

public class UpdateDataDao extends DatabaseConnector{
    public boolean update_hash(String userid, String hash) {
        String sql = "UPDATE student SET ハッシュ値 = '"+hash+"' WHERE ユーザーID = '"+userid+"'";
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
            System.out.println("ハッシュ値の更新に失敗しました(reset)");
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

    public boolean update_salt(String userid, String salt) {
        String sql = "UPDATE salt SET ソルト = '"+salt+"' WHERE ユーザーID = '"+userid+"'";
        Statement stmt = null;
        ResultSet rs = null;
        int result_int = 0;
        boolean result = false;
        try {
            super.connect("saltdb");
            // ユーザー登録
            stmt = super.con.createStatement();
            result_int = stmt.executeUpdate(sql);
            result = true;
        } catch(Exception e) {
            System.out.println("ソルトの更新に失敗しました(reset)");
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

    public boolean update_stretching(String userid, int stretching) {
        String sql = "UPDATE stretching SET ストレッチング = "+stretching+" WHERE ユーザーID = '"+userid+"'";
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
            System.out.println("ストレッチングの更新に失敗しました(reset)");
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