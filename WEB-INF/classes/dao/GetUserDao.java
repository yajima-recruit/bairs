/* 与えられたユーザーIDに対応するソルトを返すクラス */
package dao;

import java.sql.*;
import java.util.*;

public class GetUserDao extends DatabaseConnector{
    private String icon = null;
    private String color = null;
    private String name = null;
    private long snum = 0L;
    private boolean snumpublic = false;
    private String gender = null;
    private String bloodtype = null;
    private String[] hobby = null;
    private String introduction = null;
    private String instagram = null;
    private String x = null;
    private String tiktok = null;

    public String[][] get_random_users(String userid) throws SQLException {
        String[][] result = null;
        String sql = "SELECT ユーザーID, ファイル名, 背景色, ニックネーム, 性別 FROM student WHERE ユーザーID <> ? ORDER BY RAND() LIMIT 4";

        super.connect("bairsdb");
        try (PreparedStatement statement = super.con.prepareStatement(sql)) {
            // パラメータの設定
            statement.setString(1, userid);

            try (ResultSet resultSet = statement.executeQuery()) {
                // 結果を格納するArrayList
                List<String[]> dataList = new ArrayList<>();

                // 結果を取得
                while (resultSet.next()) {
                    String[] rowData = {
                            resultSet.getString("ユーザーID"),
                            resultSet.getString("ファイル名"),
                            resultSet.getString("背景色"),
                            resultSet.getString("ニックネーム"),
                            resultSet.getString("性別")
                    };
                    dataList.add(rowData);
                }

                // ArrayListを2次元配列に変換
                result = dataList.toArray(new String[0][0]);
            }
        } catch (SQLException e) {
            // 例外処理（エラーメッセージを表示など）
            e.printStackTrace();
        } finally {
            super.disconnect(); // 必要であればfinallyブロックでクローズ処理を追加
        }

        return result;
    }

    public boolean get_profile(String userid){
        String sql;
        Statement stmt = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            super.connect("bairsdb");
            stmt = super.con.createStatement();
            sql = "SELECT ファイル名, 背景色, ニックネーム, 学籍番号, 学籍番号の公開, 性別, 血液型, 自己紹介, Instagram, X, TikTok FROM student WHERE ユーザーID = '"+userid+"'";
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                icon = rs.getString("ファイル名");
                color = rs.getString("背景色");
                name = rs.getString("ニックネーム");
                snum = rs.getLong("学籍番号");
                snumpublic = rs.getBoolean("学籍番号の公開");
                gender = rs.getString("性別");
                bloodtype = rs.getString("血液型");
                introduction = rs.getString("自己紹介");
                instagram = rs.getString("Instagram");
                x = rs.getString("X");
                tiktok = rs.getString("TikTok");

                ArrayList<String> hobbyList = new ArrayList<>();
                sql = "SELECT 趣味 FROM hobby WHERE ユーザーID = '"+userid+"'";
                rs = stmt.executeQuery(sql);
                while(rs.next()){
                    hobbyList.add(rs.getString("趣味"));
                }
                hobby = hobbyList.toArray(new String[0]);
                result = true;
            } else {
                System.out.println(userid+"のプロフィールの探索に失敗しました(profile)");
            }
        } catch(Exception e) {
            System.out.println("プロフィールの取得に失敗しました(profile)");
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

    public String getIcon(){
        return icon;
    }
    public String getColor(){
        return color;
    }
    public String getName(){
        return name;
    }
    public long getSnum(){
        return snum;
    }
    public boolean getSnumpublic(){
        return snumpublic;
    }
    public String getGender(){
        return gender;
    }
    public String getBloodtype(){
        return bloodtype;
    }
    public String[] getHobby(){
        return hobby;
    }
    public String getIntroduction(){
        return introduction;
    }
    public String getInstagram(){
        return instagram;
    }
    public String getX(){
        return x;
    }
    public String getTiktok(){
        return tiktok;
    }

}