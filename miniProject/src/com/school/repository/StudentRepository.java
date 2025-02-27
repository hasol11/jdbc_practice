package com.school.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.school.model.Student;

public class StudentRepository {

	//관리자 6-3 존재유무 
	//login_id가 같은지 확인 
	public static int isStudentExist(int loginId) {
		List<Student> list = new ArrayList<>();
		String query="SELECT * FROM student WHERE login_id = ?";
		try(Connection conn =DataBaseManager.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(query)) {
			pstmt.setInt(1, loginId);
			try (ResultSet rs= pstmt.executeQuery()){
				while(rs.next()) {
					Student bvo = new Student(rs.getInt("student_id"),rs.getString("student_name"),rs.getInt("login_id"));
					list.add(bvo);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
			int res = 0;
			if(list.isEmpty()) {
				res=0;
			}else {
				res=1;
			}
		
		return res;
	}

	//관리자 6-3-학생 리스트에 추가 
	public static int addStudent(String studentName, int loginId, int password) {
		String query="INSERT INTO login (login_id,password, role) VALUES('"+loginId+ "',"+password+",1)";
		try(Connection conn = DataBaseManager.getConnection();
				Statement stmt = conn.createStatement()){
			//삽입, 수정, 삭제가 성공하면 0보다 크다. 
			stmt.executeUpdate(query);			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		int result=0;
		String query2="INSERT INTO student (student_name, login_id) VALUES('"+studentName+"','"+loginId+ "')";
		try(Connection conn = DataBaseManager.getConnection();
				Statement stmt = conn.createStatement()){
			//삽입, 수정, 삭제가 성공하면 0보다 크다. 
			result=stmt.executeUpdate(query2);			
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

}
