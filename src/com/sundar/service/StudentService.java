package com.sundar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sundar.api.Student;

@Service
public class StudentService {

	public List<Student> getStudents() {

		List<Student> students = new ArrayList<>();

		Student student = new Student();
		student.setStudent_ID(1);
		student.setStudent_Name("Sundar");
		student.setStudent_Address("Chennai");

		Student student1 = new Student();
		student1.setStudent_ID(2);
		student1.setStudent_Name("Sabari");
		student1.setStudent_Address("Chennai");

		Student student2 = new Student();
		student2.setStudent_ID(3);
		student2.setStudent_Name("Jeeva");
		student2.setStudent_Address("Chennai");

		students.add(student);
		students.add(student1);
		students.add(student2);

		return students;
	}

	public void printStudents(List<Student> students) {
		
		for (Student student : students) {
			System.out.println(student);
		}
	}
}
