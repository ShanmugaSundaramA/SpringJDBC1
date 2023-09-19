package com.sundar.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sundar.api.Student;
import com.sundar.config.StudentConfig;
import com.sundar.dao.StudentDAO;
import com.sundar.service.StudentService;

public class Test {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StudentConfig.class);

		// Successfully Loaded.
		System.out.println("Loaded...");

		StudentDAO dao = context.getBean("studentDAO", StudentDAO.class);
		StudentService service = context.getBean("studentService", StudentService.class);

		Student student = new Student();
		student.setStudent_ID(1);
		student.setStudent_Name("Sundar");
		student.setStudent_Address("Chennai");

		// Truncate all data.
		dao.cleanup();

		// Insert One Record.
		dao.insertOneRecord(student);

		// Delete one Record By ID.
		dao.deleteById(1);

		// Delete record by Name and Address.
		dao.deletByNameAndAddress("Sundar", "Chennai");

		// Insert BULK records.
		dao.insertStudentsBulkRecord(service.getStudents());

		// Display All Records.
		List<Student> students = dao.findAllStudentsByRowMapper();
		service.printStudents(students);

		// Display Record using ID by RowMapper and BeanPropertyRowMapper<T>(T.class)
		dao.findStudentByIdByRowMapper(2);

		// Display Record using Name by ResultSetExtractor
		List<Student> studentss = dao.findStudentsByNameByResultSetExtractor("Sundar");
		service.printStudents(studentss);
		
		//Display Record Group by Address using ResultSetExtractor.
		System.out.println(dao.GroupByAddress("Chennai"));
		
		Student student2 = new Student();
		student2.setStudent_ID(1);
		student2.setStudent_Name("Vikram1");
		student2.setStudent_Address("Kanchipuram1");
		
		//Update One Record
		//dao.updateOneRecord(student2);
		
		Student student5 = new Student();
		student5.setStudent_ID(1);
		student5.setStudent_Name("Vikram1");
		student5.setStudent_Address("Kanchipuram1");
		
		Student student3 = new Student();
		student3.setStudent_ID(2);
		student3.setStudent_Name("Suryafffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
		student3.setStudent_Address("Trichy1");
		
		Student student4 = new Student();
		student4.setStudent_ID(3);
		student4.setStudent_Name("Vijay1");
		student4.setStudent_Address("Madurai1");
		
		List<Student> students1 = new ArrayList<>();
		students1.add(student5);
		students1.add(student3);
		students1.add(student4);
		
		
		dao.updateStudentsBulkRecord(students1);
		
		context.close();
	}

}
