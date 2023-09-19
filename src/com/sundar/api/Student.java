package com.sundar.api;

public class Student {

	private int Student_ID;
	private String Student_Name;
	private String Student_Address;

	public int getStudent_ID() {
		return Student_ID;
	}

	public void setStudent_ID(int student_ID) {
		Student_ID = student_ID;
	}

	public String getStudent_Name() {
		return Student_Name;
	}

	public void setStudent_Name(String student_Name) {
		Student_Name = student_Name;
	}

	public String getStudent_Address() {
		return Student_Address;
	}

	public void setStudent_Address(String student_Address) {
		Student_Address = student_Address;
	}

	@Override
	public String toString() {
		return "Student [Student_ID=" + Student_ID + ", Student_Name=" + Student_Name + ", Student_Address="
				+ Student_Address + "]";
	}

}
