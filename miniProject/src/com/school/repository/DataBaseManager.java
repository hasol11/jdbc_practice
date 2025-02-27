package com.school.repository;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseManager {
	private static String URL;
	private static String USER;
	private static String PASSWORD; //다 대문자는 상수를 의미한다 
	
	//미리 만들 때 static 초기화하는 것, 값을 미리 넣어주기 위함.  
	static {
		try {
			//config 에 넣어넣은 값을 각각 필드에 넣어주는 것. 
			Properties properties = new Properties();
			properties.load(new FileInputStream("resources/config.properties"));
			//properties를 보게 되면 맵 형식인 것을 알 수 있다. 
			URL=properties.getProperty("db.url");
			USER=properties.getProperty("db.user");
			PASSWORD=properties.getProperty("db.password");
			
			//1. 드라이버 연결 -> 연결만 한 상태 접속은 아직 안 됨. 
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println(e);
		}
		}
	//2. 접속하기
	public static Connection getConnection()throws SQLException{
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
