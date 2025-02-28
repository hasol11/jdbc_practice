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



public class SubjectRepository {
	
	
	
	//과목 존재 유무 확인 
	public static int isSubjectExist(String subjectName) {
		List<Subject> list = new ArrayList<>();
		String query="SELECT * FROM subject WHERE subject_name = ?";
		try(Connection conn =DatabaseManager.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(query)) {
			pstmt.setString(1, subjectName);
			try (ResultSet rs= pstmt.executeQuery()){
				while(rs.next()) {
					Subject bvo = new Subject(rs.getInt("subject_id"),rs.getString("subject_name"),rs.getInt("teacher_id"));
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

	// 학생화면  2 수강신청 전 과목목록 출력
	// 관리자화면 1 전체 과목 출력
	
	public static List<Map<String, Object>> showSubject() {
		List<Map<String, Object>> Subjects = new ArrayList<>();

		
		String query = "SELECT * " +
				"FROM teacher t " +
				"RIGHT JOIN subject sub ON t.teacher_id = sub.teacher_id";

		try (Connection conn = DatabaseManager.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
						row.put("teacher_id", rs.getInt("teacher_id"));
						Object teacherObject1 = rs.getObject("teacher_name");
						row.put("teacher_name", teacherObject1);
						row.put("login_id", rs.getInt("login_id"));
						row.put("subject_id", rs.getInt("subject_id"));
						row.put("subject_name", rs.getString("subject_name"));
						Object teacherObject2 = rs.getObject("teacher_id");
						row.put("teacher_id", teacherObject2);

				Subjects.add(row);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		return Subjects;
	}	
	


	
	// 학생화면 1-2 수강신청에서 과목이 존재하는지 확인
	public static int checkIsSubject(String subject_name) {
		List<Subject> Subjects = new ArrayList<>();
		String query = "SELECT * FROM subject WHERE subject_name = " + "'" + subject_name + "'";
		int result = 0;
		
		try(Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			
			while (rs.next()) {
				Subject Subject = new Subject(
						rs.getInt("subject_id"),rs.getString("subject_name"),rs.getInt("teacher_id"));

				Subjects.add(Subject);
			}
			if(Subjects.size()== 0) {
				result = 404;
			}else {
				result = 1;
			}
			
		}catch (Exception e) {
			
		}
		
		return result;
	}

	

	
	
	
	
	
}
