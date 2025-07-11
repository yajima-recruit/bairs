/* 与えられたユーザーIDに対応するソルトを返すクラス */
package dao;

import java.sql.*;

public class GetSaltDao extends DatabaseConnector{
    public String GetSalt(String userid) {
    	Statement stmt = null;
    	ResultSet rs = null;
    	String result = null;
    	String sql = "SELECT ソルト FROM salt WHERE ユーザーID = '" + userid + "'";
    	try{
			super.connect("saltdb");
      		// ステートメントを生成
      		stmt = super.con.createStatement();
      		// SQLを実行
      		rs = stmt.executeQuery(sql);
      		// 検索結果の処理
      		rs.next();
      		result = rs.getString("ソルト");
    	} catch(Exception e){
			System.out.println("ソルトの取得に失敗しました");
    	} finally {
      		try{
        		if(rs != null) rs.close();
        		if(stmt != null) stmt.close();
				super.disconnect();
      		} catch(Exception e){
        		System.out.println("データベースの切り離し中にエラーが発生しました");
      		}
    	}
    	
    	return result;
  	}
}