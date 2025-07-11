// データベースを参照し、引数に与えられた学籍番号がある場合はハッシュ値を返し、ない場合は"false"を返す。
package dao;

import java.sql.*;

public class AccountSearchDao extends DatabaseConnector{
	public String select_userid(String userid) {
    	Statement stmt = null;
    	ResultSet rs = null;
    	String result = null;
    	String sql = "SELECT ハッシュ値 FROM student WHERE ユーザーID = '" + userid + "'";
    	try{
			super.connect("bairsdb");
      		// ステートメントを生成
      		stmt = super.con.createStatement();
      		// SQLを実行
      		rs = stmt.executeQuery(sql);
      		// 検索結果の処理
      		rs.next();
      		result = rs.getString("ハッシュ値");
    	} catch(Exception e){
			System.out.println("ユーザーIDの検索に失敗しました");
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

	public String select_username(String userid) {
    	Statement stmt = null;
    	ResultSet rs = null;
    	String result = null;
    	String sql = "SELECT ニックネーム FROM student WHERE ユーザーID = '" + userid + "'";
    	try{
			super.connect("bairsdb");
      		// ステートメントを生成
      		stmt = super.con.createStatement();
      		// SQLを実行
      		rs = stmt.executeQuery(sql);
      		// 検索結果の処理
      		rs.next();
      		result = rs.getString("ニックネーム");
    	} catch(Exception e){
			System.out.println("ユーザーIDの検索に失敗しました");
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

	public String select_question(String userid) {
    	Statement stmt = null;
    	ResultSet rs = null;
    	String result = null;
    	String sql = "SELECT 質問 FROM student WHERE ユーザーID = '" + userid + "'";
    	try{
			super.connect("bairsdb");
      		// ステートメントを生成
      		stmt = super.con.createStatement();
      		// SQLを実行
      		rs = stmt.executeQuery(sql);
      		// 検索結果の処理
      		rs.next();
      		result = rs.getString("質問");
    	} catch(Exception e){
			System.out.println("ユーザーIDの検索に失敗しました");
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

	public String select_answer(String userid) {
    	Statement stmt = null;
    	ResultSet rs = null;
    	String result = null;
    	String sql = "SELECT 答え FROM secret WHERE ユーザーID = '" + userid + "'";
    	try{
			super.connect("bairsdb");
      		// ステートメントを生成
      		stmt = super.con.createStatement();
      		// SQLを実行
      		rs = stmt.executeQuery(sql);
      		// 検索結果の処理
      		rs.next();
      		result = rs.getString("答え");
    	} catch(Exception e){
			System.out.println("答えの検索に失敗しました");
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