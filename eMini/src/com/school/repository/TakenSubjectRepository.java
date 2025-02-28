package com.school.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.school.model.Subject;




public class TakenSubjectRepository {

	//교사2 - 학생 정보 조회 - 성적 수정 및 입력 
	public static int updateGrade(String student_name, String subject_name, int score) {	
		int result=0;
		String query = "UPDATE taken_subject SET score= " +score+ " WHERE student_id=(SELECT student_id "
				+ " FROM student WHERE student_name='"+student_name+"') AND subject_id=(SELECT subject_id "
				+ " FROM subject WHERE subject_name='"+subject_name+"')";
		
		try (Connection conn = DatabaseManager.getConnection();
				Statement stmt=conn.createStatement()) {
			result=stmt.executeUpdate(query);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	//학생화면 1-1 내 점수 보기
	public static List<Map<String, Object>> showStudentScore(int student_id) {
		List<Map<String, Object>> takenSubjects = new ArrayList<>();

		
		String query = "SELECT * " +
				"FROM taken_subject ts " + 
				"INNER JOIN subject sub ON ts.subject_id = sub.subject_id "+
				"WHERE student_id= " + student_id;

		try (Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
						row.put("taken_id", rs.getInt("taken_id"));
						
				        // score를 getObject()로 가져와 NULL 처리
				        Object score = rs.getObject("score");
				        row.put("score", score);  // score가 NULL이면, Map에 NULL로 저장됨

						//row.put("score", rs.getInt("score"));
						row.put("student_id", rs.getInt("student_id"));
						row.put("subject_id", rs.getInt("subject_id"));
						row.put("teacher_id", rs.getInt("teacher_id"));
						row.put("subject_name", rs.getString("subject_name"));

				takenSubjects.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		return takenSubjects;
	}

	
	// 학생화면 1-2 수강신청에서 내가 수강하고 있는 과목인지 확인
	public static int checkIsTakenSubject(String subject_name, int student_id) {
		List<Map<String, Object>> takenSubjects = new ArrayList<>();

		int result = 0;
		
		
		String query = "SELECT * " +
				"FROM taken_subject ts " +
				"INNER JOIN subject sub ON sub.subject_id = ts.subject_id " +
				"WHERE sub.subject_name= " + "'" + subject_name + "'" + " AND ts.student_id = " + student_id;

		try (Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				Map<String, Object> row = new HashMap<String, Object>();
						row.put("taken_id", rs.getInt("taken_id"));
						row.put("score", rs.getInt("score"));
						row.put("student_id", rs.getInt("student_id"));
						row.put("subject_id", rs.getInt("subject_id"));
						row.put("teacher_id", rs.getInt("teacher_id"));
						row.put("subject_id", rs.getInt("subject_id"));
						row.put("subject_name", rs.getString("subject_name"));
						row.put("teacher_id", rs.getInt("teacher_id"));
						row.put("teacher_id", rs.getInt("teacher_id"));

				takenSubjects.add(row);
			}
			if(takenSubjects.size()== 0) {
				result = 404;
			}else {
					result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		return result;
	}
	

	
	// 학생화면 2 수강신청에서 taken_subject에 새로 신청한거 삽입

	public static int insertTakenSubject(String subject_name, int student_id) {
		int result = 0;
		List<Subject> Subjects = new ArrayList<>();
		String query1 = "SELECT * FROM subject WHERE subject_name = " + "'"+ subject_name + "'";
		try(Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query1)){
			while (rs.next()) {
				Subject Subject = new Subject(
						rs.getInt("subject_id"),rs.getString("subject_name"),rs.getInt("teacher_id"));

				Subjects.add(Subject);
			}			
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		int subject_id = Subjects.get(0).getSubject_id();
		int teacher_id = Subjects.get(0).getTeacher_id();
		
		

		
		String query = "INSERT INTO taken_subject (student_id , subject_id, teacher_id) " +
				"VALUES(" + student_id + "," + subject_id + "," + teacher_id +")";
		
		try(Connection conn = DatabaseManager.getConnection();
				Statement stmt = conn.createStatement()){
				// 삽입, 수정, 삭제가 성공하면 0보다 크다.{
			result = stmt.executeUpdate(query);
		} catch (Exception e) {
			
		}
		return result;
		}
	


}
