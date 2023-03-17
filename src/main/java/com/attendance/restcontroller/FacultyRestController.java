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

import com.attendance.dto.FacultyDetails;
import com.attendance.dto.UserCredentials;
import com.attendance.model.Faculty;
import com.attendance.service.FacultyService;

@RestController
@RequestMapping("/faculty")
public class FacultyRestController {


	@Autowired
	private FacultyService facultyService;


	@PostMapping("verifyLoginCredentials")
	public boolean verifyLoginCredentials(@RequestBody UserCredentials userCredentials, HttpSession session) {
		Faculty faculty = facultyService.verifyLoginCredentials(userCredentials);
		if (faculty != null) {
			session.setAttribute("facultyId", faculty.getId());
			
			return true;
		}

		return false;
	}

	@PostMapping("/addFaculty")
	public Faculty addFaculty(@RequestBody FacultyDetails facultyDetails) {
		return facultyService.addFaculty(facultyDetails);
	}


	@DeleteMapping("/deleteFaculty/{facultyId}")
	public Boolean deleteFaculty(@PathVariable("facultyId") Long facultyId) {
		return facultyService.deleteFacultyById(facultyId);
	}

	@GetMapping("/getAllFacultys")
	public List<FacultyDetails> getAllFacultys() {
		return facultyService.getAllFacultys();
	}
	@PostMapping("/editFaculty/{facultyId}")
	public ResponseEntity<Faculty> editFaculty(@RequestBody FacultyDetails faculty, @PathVariable("facultyId") Long facultyId) {
		Faculty responseFaculty = facultyService.editFaculty(faculty, facultyId);
		return ResponseEntity.ok().body(responseFaculty);
	}



}
