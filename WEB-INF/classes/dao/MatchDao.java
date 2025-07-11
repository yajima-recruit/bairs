package dao;

import java.sql.*;

public class MatchDao extends DatabaseConnector {
    public String[][] matching(String userid) {
        String sql = "SELECT t2.ユーザーID, s2.ニックネーム, COUNT(*) AS 一致数, COUNT(*) OVER () AS 全体の行数 " +
                     "FROM hobby AS t1 " +
                     "JOIN hobby AS t2 ON t1.趣味 = t2.趣味 AND t1.ユーザーID <> t2.ユーザーID " +
                     "JOIN student AS s2 ON t2.ユーザーID = s2.ユーザーID " +
                     "WHERE t1.ユーザーID = ? " +
                     "GROUP BY t1.ユーザーID, t2.ユーザーID, s2.ニックネーム " +
                     "ORDER BY 一致数 DESC";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String[][] result = {{null, null, null}};

        super.connect("bairsdb");
        try {
            // プリペアドステートメントを生成
            pstmt = super.con.prepareStatement(sql);
            // パラメータをセット
            pstmt.setString(1, userid);
            // SQLを実行
            rs = pstmt.executeQuery();
            // 検索結果の処理
            int i = 0;
            if (rs.next()) {
                int row = rs.getInt("全体の行数");
                result = new String[row][3];
                result[i][0] = rs.getString("ユーザーID");
                result[i][1] = rs.getString("ニックネーム");
                result[i][2] = String.valueOf(rs.getInt("一致数"));
                i++;
                while (rs.next()) {
                    result[i][0] = rs.getString("ユーザーID");
                    result[i][1] = rs.getString("ニックネーム");
                    result[i][2] = String.valueOf(rs.getInt("一致数"));
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.disconnect();
        return result;
    }
}
