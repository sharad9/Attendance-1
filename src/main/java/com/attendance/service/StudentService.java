package com.attendance.service;

import java.util.List;

import com.attendance.dto.StudentDetails;
import com.attendance.dto.UserCredentials;
import com.attendance.model.Student;

public interface StudentService {

	Student verifyLoginCredentials(UserCredentials userCredentials);
	Student addStudent(StudentDetails studentDetails);
	Student saveStudent(Student student);
	Boolean deleteStudentById(Long studentId);
	Student getStudentById(Long studentId);
	List< StudentDetails > getAllStudents();
	Student editStudent(StudentDetails studentDetails, Long studentId);
}
