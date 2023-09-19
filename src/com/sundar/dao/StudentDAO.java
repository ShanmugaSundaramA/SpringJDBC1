package com.sundar.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sundar.api.Student;
import com.sundar.resultSetExtractor.StudentAddressResultSetExtractor;
import com.sundar.resultSetExtractor.StudentsResultSetExtractor;
import com.sundar.rowMapper.StudentRowMapper;

@Repository
public class StudentDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	// For DDL use execute()
	public void cleanup() {

		String sql = "TRUNCATE TABLE school.student ";

		jdbcTemplate.execute(sql);

		System.out.println("<<<<<<<< All Record will be Trucated >>>>>>>>");
	}

	// For DML use update()
	public void insertOneRecord(Student student) {

		String sql = "INSERT INTO school.student VALUES (?,?,?)";

		Object[] args = { student.getStudent_ID(), student.getStudent_Name(), student.getStudent_Address() };

		int update = jdbcTemplate.update(sql, args);

		System.out.println("<<<<<<<<<< " + update + " row will be Inserted >>>>>>>>>>");

	}

	public void deleteById(int id) {

		String sql = "DELETE FROM school.student WHERE Student_ID = ? ";

		int update = jdbcTemplate.update(sql, id);

		System.out.println("<<<<<<<<<< " + update + " row will be Deleted >>>>>>>>>>");

	}

	public void deletByNameAndAddress(String name, String address) {

		String sql = "DELETE FROM school.student WHERE Student_Name = ? and Student_Address = ?";

		Object[] args = { name, address };

		int update = jdbcTemplate.update(sql, args);

		System.out.println("<<<<<<<<<< " + update + " rows will be Deleted >>>>>>>>>>");

	}

	public void insertStudentsBulkRecord(List<Student> students) {

		String sql = "INSERT INTO school.student VALUES (?,?,?)";

		List<Object[]> studentargs = new ArrayList<>();

		for (Student student : students) {

			Object[] args = { student.getStudent_ID(), student.getStudent_Name(), student.getStudent_Address() };

			studentargs.add(args);
		}

		int[] update = jdbcTemplate.batchUpdate(sql, studentargs);

		System.out.println("<<<<<<<<<< " + Arrays.toString(update) + " row will be Inserted >>>>>>>>>>");
	}

	// For DRL use query method.
	public List<Student> findAllStudentsByRowMapper() {

		String sql = "SELECT * FROM school.student";

		List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper());

		return students;
	}

	public void findStudentByIdByRowMapper(int id) {

		// If table property and Domain class variable is not match use AS in query.

		String sql = "SELECT Student_ID as Student_ID," + "Student_Name as Student_Name,"
				+ "Student_Address as Student_Address" + " FROM school.student WHERE Student_ID = ?";

		// Student student = jdbcTemplate.queryForObject(sql, new StudentRowMapper(),
		// id);

		Student student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class), id);

		System.out.println("<<<<< Find Student By Id By RowMapper :" + student + " >>>>>");
	}

	public List<Student> findStudentsByNameByResultSetExtractor(String name) {

		String sql = "SELECT * FROM school.student WHERE Student_Name = ? ";

		List<Student> students = jdbcTemplate.query(sql, new StudentsResultSetExtractor(), name);

		return students;
	}

	public Map<String, List<String>> GroupByAddress(String address) {

		String sql = "SELECT * FROM school.student WHERE Student_Address = ?";

		Map<String, List<String>> students = jdbcTemplate.query(sql, new StudentAddressResultSetExtractor(), address);

		return students;

	}

	public void updateOneRecord(Student student) {

		String sql = "UPDATE school.student SET Student_Name = ? , Student_Address = ?" + " where Student_ID = ? ";

		Object[] args = { student.getStudent_Name(), student.getStudent_Address(), student.getStudent_ID() };

		int update = jdbcTemplate.update(sql, args);

		System.out.println("<<<<<<<<<< " + update + " row will be Updated >>>>>>>>>>");
	}

	@Transactional
	public void updateStudentsBulkRecord(List<Student> students) {

		String sql = "UPDATE school.student SET Student_Name = ? , Student_Address = ?" + " where Student_ID = ? ";

		int[] batchUpdate = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {

				ps.setString(1, students.get(index).getStudent_Name());
				ps.setString(2, students.get(index).getStudent_Address());
				ps.setInt(3, students.get(index).getStudent_ID());
			}

			@Override
			public int getBatchSize() {

				// Return the size of the List
				// And the Execute the setValues method for size of the List.

				return students.size();
			}
		});

		System.out.println("<<<<<<<<<< " + Arrays.toString(batchUpdate) + " row will be Updated >>>>>>>>>>");

	}

}
