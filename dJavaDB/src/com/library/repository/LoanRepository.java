package com.library.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.model.Loan;

public class LoanRepository {
	//Loan은? -> model에 있다. vo라고 써도 상관 없음. model은 테이블과 연결되어 있음. 
	public static List<Loan> getBorrowBooks(){
		//select 문인 경우, 0, 1, 여러 개의 결과가 나올 수 있다. => List 사용 이유
		//Loan 클래스는 select 결과를 담기 위한 클래스 (VO, DTO)
		List<Loan> loans =new ArrayList<>();
		
		//3. Query 보내기
		String query="SELECT * FROM loan WHERE STATUS LIKE '대출중'"; //세미콜론 찍어서 보내면 안 됨
		
		//try-with-resources 자원을 안전하게 관리하기 위한 문법 
		//파일, 네트워크, 데이터베이스 사용 후 반드시 닫아야 하는 자원을 자동으로 처리
		//위에서 만들어진 query를 담아서 DB에 보내자
		try(Connection conn = DataBaseManager.getConnection();
				//3-1. DB에 보내질 Query 담을 클래스 객체 생성 
				Statement stmt = conn.createStatement();
				//3-2. Query를 담아서 보내고 결과를 받자 
				//Select => executeQuery(query) => 결과가 ResultSet  (조회된 표..????)
				//insert update delete => executeUpdate() => 결과가 int 
				ResultSet rs=stmt.executeQuery(query) //stmt로 보내고 rs에 결과가 담긴다.
				) {
			while(rs.next()) {
				Loan loan =new Loan(rs.getInt("loan_id"),rs.getInt("bookdata_id"), rs.getInt("member_id"), 
							rs.getString("status"),rs.getDate("loan_date").toLocalDate());
				loans.add(loan);
			}
		} catch (Exception e) {
		}
		return loans;
	}
	public static int insertBorrowBook(int bookdata_id,int member_id) {
		int result=0;
		String query="insert into loan(bookdata_id, member_id,status,loan_date) values("+
						bookdata_id+","+member_id+",'대출중',curdate())";
		try(Connection conn = DataBaseManager.getConnection();
				Statement stmt = conn.createStatement()){
			//삽입, 수정, 삭제가 성공하면 0보다 크다. 
			result=stmt.executeUpdate(query);			
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	public static int updateBorrowBook(int bookdata_id,int member_id) {
		int result=0;
		String query=" update loan set status='반납완료' where bookdata_id= " + 
					bookdata_id + " and member_id = " + member_id;
		try(Connection conn = DataBaseManager.getConnection();
				Statement stmt = conn.createStatement()){
			//삽입, 수정, 삭제가 성공하면 0보다 크다. 수정 성공하면 1, 수정 실패하면 0
			result=stmt.executeUpdate(query);			
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
}
