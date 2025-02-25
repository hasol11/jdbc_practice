package com.library.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.model.Bookdata;

public class BookRepository {
	public static List<Bookdata> getBookList(){
		List<Bookdata> bookdatas = new ArrayList<>();
		
		//query 날리기
		String query= "SELECT * FROM bookdata";
		try (Connection conn = DataBaseManager.getConnection();
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery(query)
				){
			while(rs.next()) {
				Bookdata bookdata=new Bookdata(rs.getInt("bookdata_id"), rs.getString("title"), rs.getInt("available"));
				bookdatas.add(bookdata);
			}
		} catch (Exception e) {
		}
		return bookdatas;
	}
	
	//insert, update, delete는 무조건 int 로 나온다. (true, false가 아니라..?)
	public static int borrowBook(int bookdata_id) {
		List<Bookdata> bookdatas = new ArrayList<>();
		String query="select * from bookdata where bookdata_id= "+bookdata_id;
		int result=0;
		try (Connection conn = DataBaseManager.getConnection();
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery(query)
				){
			while(rs.next()) {
				Bookdata bookdata=new Bookdata(rs.getInt("bookdata_id"), rs.getString("title"), rs.getInt("available"));
				bookdatas.add(bookdata);
			}
			if(bookdatas.size()==0) {
				result= 404;
			}else if(bookdatas.size()==1) {
				if(bookdatas.get(0).getAvailable()==0) {
					result=0;
				}else {
					result=1;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	
	public static void updateBook(int bookdata_id) {
		//위 메서드에서 호출해서 실행하면 available 의 값을 0, 1을 받을 수 있다. 
		int res= borrowBook(bookdata_id);
		String query=null;
		if(res==0) {
			query="update bookdata set available=1 where bookdata_id ="+bookdata_id;
		}else {
			query="update bookdata set available=0 where bookdata_id ="+bookdata_id;
		}
		try (Connection conn = DataBaseManager.getConnection();
				Statement stmt=conn.createStatement()) {
			stmt.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
 
}
