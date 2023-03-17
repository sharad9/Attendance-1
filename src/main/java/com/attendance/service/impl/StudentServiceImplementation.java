package com.attendance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.attendance.dto.StudentDetails;
import com.attendance.dto.UserCredentials;
import com.attendance.model.Class;
import com.attendance.model.Student;

import com.attendance.repository.ClassRepository;
import com.attendance.repository.StudentRepository;
import com.attendance.service.ClassService;
import com.attendance.service.StudentService;
import com.attendance.service.mail.MailService;

@Service
public class StudentServiceImplementation implements StudentService {

	@Autowired
	private StudentRepository  studentRepository;

	@Autowired
	private ClassService classsService;


	@Autowired
	private ClassRepository  classsRepository;

	@Autowired
	private MailService mailService;

	@Override
	public Student verifyLoginCredentials(UserCredentials userCredentials) {
			Student student = studentRepository.getStudentWithUsernameAndPassword(userCredentials.getUsername(), userCredentials.getPassword());
		return student;
	}

	@Override
	public Student addStudent(StudentDetails studentDetails) {
		List < Student > users = studentRepository.findAll();
		for (Student user:users) {
			if (user.getUsername().equals(studentDetails.getUsername()) || user.getEmail().equals(studentDetails.getEmail())) {
				return null;
			}
		}
		
		Student student = new Student();
		student.setName(studentDetails.getName());
		student.setUsername(studentDetails.getUsername());
		student.setPassword(studentDetails.getPassword());
		student.setEmail(studentDetails.getEmail());
		
		
		Class classs = classsService.getClassById(studentDetails.getClassId());
		

		classs.getStudents().add(student);
		student.setClasss(classs);
		student = studentRepository.save(student);
		student.setClasss(null);


			
			
			
			
			String subject = "Welcome to ☀ Sasa Attendance";
			String message = "<p>Your Profile is created on <strong>🐧 Sasa Attendance</strong></p>"
			+ "<p>You Have Class Student Level Access into Our system </p>"
			+ "<p>Click on this link: https://apniattendance.web.app </p>"
			+ "<p>Use Below Credentials to Log into Our System </p>"
			+ "<p>Username: " + student.getUsername() + "</p>"
			+ "<p>Password:" + student.getPassword() + "</p>"
			+ "<p style='color:red;'>NOTE: DO NOT SHARE YOUR CREDENTIALS WITH ANYONE!!</p>";


		this.sendEmail(student.getEmail(), subject, message);


		return student;

	}

	@Override
	public Student saveStudent(Student student) {

		return studentRepository.save(student);
	}

	@Override
	public Boolean deleteStudentById(Long studentId) {
		if (studentRepository.existsById(studentId)) {
			studentRepository.deleteById(studentId);
			return true;
		}
		return false;
	}

	@Override
	public Student getStudentById(Long studentId) {
		if (studentRepository.existsById(studentId)) {
			return studentRepository.getReferenceById(studentId);
		}
		return null;
	}

	@Override
	public List<StudentDetails> getAllStudents() {
		List < Student > students = studentRepository.findAll();
		List < StudentDetails > studentsDetails = new ArrayList < StudentDetails > ();
		students.forEach(student -> {
			if(student.getClass() != null) {
				StudentDetails studentDetails = new StudentDetails();
			studentDetails.setId(student.getId());
			studentDetails.setEmail(student.getEmail());
			studentDetails.setName(student.getName());
			studentDetails.setUsername(student.getUsername());
			studentDetails.setPassword(student.getPassword());
			studentDetails.setRollNo(student.getRollNo());
			studentsDetails.add(studentDetails);


		}
	} );
		
		
		return studentsDetails;
	}


@Override
public Student editStudent(StudentDetails studentDetails, Long studentId) {
	Student student = studentRepository.getReferenceById(studentId);

	student.setName(studentDetails.getName());
	student.setUsername(studentDetails.getUsername());
	student.setPassword(studentDetails.getPassword());
	student.setEmail(studentDetails.getEmail());

	Class classs = classsRepository.getReferenceById(student.getClasss().getId());

	classs.getStudents().add(student);

	
	student.setClasss(classs);
		

	

	student = studentRepository.save(student);
	student.setClasss(null);
	String subject = "Welcome to ☀ Sasa Attendance";
	String message = "<p>Your Profile is created on <strong>🐧 Sasa Attendance</strong></p>"
		+ "<p>You Have Class Student Level Access into Our system </p>"
		+ "<p>Click on this link: https://apniattendance.web.app </p>"
		+ "<p>Use Below Credentials to Log into Our System </p>"
		+ "<p>Username: " + student.getUsername() + "</p>"
		+ "<p>Password:" + student.getPassword() + "</p>"
		+ "<p style='color:red;'>NOTE: DO NOT SHARE YOUR CREDENTIALS WITH ANYONE!!</p>";



	this.sendEmail(student.getEmail(), subject, message);

	return student;
}

	public void sendEmail(String receiver, String subject, String message) {
	try {
		mailService.sendMail(receiver, subject, message);
	} catch (MailException | MessagingException e) {

		e.printStackTrace();
	}
}
	

	

}
