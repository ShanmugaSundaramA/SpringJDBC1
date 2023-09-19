package com.sundar.resultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.sundar.api.Student;

public class StudentsResultSetExtractor implements ResultSetExtractor<List<Student>> {

	@Override
	public List<Student> extractData(ResultSet res) throws SQLException, DataAccessException {
		List<Student> students = new ArrayList<>();

		while (res.next()) {

			Student student = new Student();
			student.setStudent_ID(res.getInt("Student_ID"));
			student.setStudent_Name(res.getString("Student_Name"));
			student.setStudent_Address(res.getString("Student_Address"));

			students.add(student);
		}

		return students;
	}

		

	}

