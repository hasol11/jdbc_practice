package com.school.repository;

import java.sql.Connection;
import java.sql.Statement;


public class TakenSubjectRepository {

	public static int updateGrade(String student_name, String subject_name, int score) {	
		int result=0;
		String query = "UPDATE taken_subject SET score= " +score+ " WHERE student_id=(SELECT student_id "
				+ " FROM student WHERE student_name='"+student_name+"') AND subject_id=(SELECT subject_id "
				+ " FROM subject WHERE subject_name='"+subject_name+"')";
		
		try (Connection conn = DataBaseManager.getConnection();
				Statement stmt=conn.createStatement()) {
			result=stmt.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

}
