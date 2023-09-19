package com.sundar.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sundar.api.Student;

public class StudentRowMapper implements RowMapper<Student> {

	//This method call for per record.
	
	@Override
	public Student mapRow(ResultSet res, int index) throws SQLException {
		
		Student student = new Student();
		student.setStudent_ID(res.getInt("Student_ID"));
		student.setStudent_Name(res.getString("Student_Name"));
		student.setStudent_Address(res.getString("Student_Address"));
		
		return student;
	}

}
