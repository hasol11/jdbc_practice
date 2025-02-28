package com.school.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.school.model.Subject;

public class AdministerRepository {
	
	//과목 추가
	public static int addSubject(String subjectName) {
		int result=0;
		String query="INSERT INTO subject (subject_name) VALUES('"+subjectName+"');";
		try(Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement()){
			//삽입, 수정, 삭제가 성공하면 0보다 크다. 
			result=stmt.executeUpdate(query);			
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	//관리자6-2-2 과목에 교사 추가 - 교사가 Null인지 확인
	public static int isTeacherNull(String subjectName1) {
		int res= 0;
		List<Subject> list = new ArrayList<>();
		String query="SELECT * FROM subject WHERE subject_name = ? and teacher_id is null";
		try(Connection conn =DatabaseManager.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(query)) {
			pstmt.setString(1, subjectName1);
			try (ResultSet rs= pstmt.executeQuery()){
				while(rs.next()) {
					Subject bvo = new Subject(rs.getInt("subject_id"), rs.getString("subject_name"), rs.getInt("teacher_id"));
							
					list.add(bvo);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
			if(list.isEmpty()) {
				res=0;
			}else {
				res=1;
			}
		return res;
	}
	//관리자6-2-2 과목에 교사 추가 - 교사가 Null인지 확인 -  교사 넣기 
	public static int addTeacherToSubject(String subjectName1, String teacherName1) {
		//교사가 교사 table에 있는지 검사하고 교사가 없다면 교사 table에 추가하고 나서 과목에 교사 번호 할당 
		//교사 x: 교사 있는지 확인 ->교사 table에 추가 -> 교사 table에서 교사 번호 select -> 해당 번호 할당  
		//교사 o: 교사 있는지 확인 -> 교사 table에서 교사 번호 select -> 해당 번호 할당 
		
		//교사 있는지 확인 
		int[] existRes=TeacherRepository.isTeacherExistName(teacherName1);
		int result=0;
		if(existRes[0]==1) {
			//교사 table에서 교사 id 확인 -> update subject table의 교사 id
			
			String query="update subject set teacher_id=? WHERE subject_name = ?";
			try(Connection conn =DatabaseManager.getConnection();
					PreparedStatement pstmt =conn.prepareStatement(query)) {
				pstmt.setInt(1, existRes[1]);
				pstmt.setString(2, subjectName1);
				result=pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println(e);
			}
		}else {
			System.out.println("교사 추가 후 과목에 할당해주세요");
		}

		return result;
	}
	
	


}
