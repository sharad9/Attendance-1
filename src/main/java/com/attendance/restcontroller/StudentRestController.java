package com.attendance.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.dto.StudentDetails;
import com.attendance.dto.UserCredentials;
import com.attendance.model.Student;
import com.attendance.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentRestController {


	@Autowired
	private StudentService studentService;


	@PostMapping("verifyLoginCredentials")
	public boolean verifyLoginCredentials(@RequestBody UserCredentials userCredentials, HttpSession sesstudenton) {
		Student student = studentService.verifyLoginCredentials(userCredentials);
		if (student != null) {
			sesstudenton.setAttribute("studentId", student.getId());
			sesstudenton.setAttribute("classId", student.getClasss().getId());
			return true;
		}

		return false;
	}

	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody StudentDetails studentDetails) {

		return studentService.addStudent(studentDetails);
	}


	@DeleteMapping("/deleteStudent/{studentId}")
	public Boolean deleteStudent(@PathVariable("studentId") Long studentId) {
		return studentService.deleteStudentById(studentId);
	}

	@GetMapping("/getAllStudents")
	public List<StudentDetails> getAllStudents() {
		return studentService.getAllStudents();
	}
	@PostMapping("/editStudent/{studentId}")
	public ResponseEntity<Student> editStudent(@RequestBody StudentDetails student, @PathVariable("studentId") Long studentId) {
		Student responseStudent = studentService.editStudent(student, studentId);
		return ResponseEntity.ok().body(responseStudent);
	}



}