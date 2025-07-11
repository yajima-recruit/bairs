/* 与えられた学籍番号に対応するストレッチングを返すクラス */
package dao;

import java.sql.*;

public class GetStretchingDao extends DatabaseConnector{
    public int GetStretching(String userid) {
    	Statement stmt = null;
    	ResultSet rs = null;
    	int result = 0;
    	String sql = "SELECT ストレッチング FROM stretching WHERE ユーザーID = '" + userid + "'";
    	try{
			super.connect("stretchingdb");
      		// ステートメントを生成
      		stmt = super.con.createStatement();
      		// SQLを実行
      		rs = stmt.executeQuery(sql);
      		// 検索結果の処理
      		rs.next();
      		result = rs.getInt("ストレッチング");
    	} catch(Exception e){
			System.out.println("ストレッチングの取得に失敗しました");
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