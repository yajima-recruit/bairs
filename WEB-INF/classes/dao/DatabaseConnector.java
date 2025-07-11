package dao;

import java.sql.*;

public class DatabaseConnector{
 	private final String URL = "jdbc:mysql://localhost/";
  	private final String USER = "root";
  	private final String PASS = "pass";
  	public Connection con = null;

  	public void connect(String dbname){
    	try{
      		// DBに接続
      		con = DriverManager.getConnection(URL+dbname, USER, PASS);
    	} catch(Exception e){
      		e.printStackTrace();
    	}
  	}

    public void disconnect(){
    	try{
      		// DBを切断
      		if(con != null) con.close();
    	} catch(Exception e){
      		e.printStackTrace();
    	}
  	}
}