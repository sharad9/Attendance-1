package com.attendance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.attendance.dto.FacultyDetails;
import com.attendance.dto.UserCredentials;

import com.attendance.model.Faculty;


import com.attendance.repository.FacultyRepository;

import com.attendance.service.FacultyService;
import com.attendance.service.mail.MailService;

@Service
public class FacultyServiceImplementation implements FacultyService {

	@Autowired
	private FacultyRepository  facultyRepository;
 


	@Autowired
	private MailService mailService;

	@Override
	public Faculty verifyLoginCredentials(UserCredentials userCredentials) {
			Faculty faculty = facultyRepository.getFacultyWithUsernameAndPassword(userCredentials.getUsername(), userCredentials.getPassword());
		return faculty;
	}

	@Override
	public Faculty addFaculty(FacultyDetails facultyDetails) {
		List < Faculty > users = facultyRepository.findAll();
		for (Faculty user:users) {
			if (user.getUsername().equals(facultyDetails.getUsername()) || user.getEmail().equals(facultyDetails.getEmail())) {
				return null;
			}
		}
		
			Faculty faculty = new Faculty();
		faculty.setName(facultyDetails.getName());
		faculty.setUsername(facultyDetails.getUsername());
		faculty.setPassword(facultyDetails.getPassword());
		faculty.setEmail(facultyDetails.getEmail());
		

			
		
		faculty = facultyRepository.save(faculty);
		


			
			
			
			

		String subject = "Welcome to ☀ Sasa Attendance";
		String message = "<p>Your Profile is created on <strong>🐧 Sasa Attendance</strong></p>"
		+ "<p>You Have Class Si Level Access into Our system </p>"
		+ "<p>Click on this link: https://apniattendance.web.app </p>"
		+ "<p>Use Below Credentials to Log into Our System </p>"
		+ "<p>Username: " + faculty.getUsername() + "</p>"
		+ "<p>Password:" + faculty.getPassword() + "</p>"
		+ "<p style='color:red;'>NOTE: DO NOT SHARE YOUR CREDENTIALS WITH ANYONE!!</p>";


		this.sendEmail(faculty.getEmail(), subject, message);


		return faculty;

	}

	@Override
	public Faculty saveFaculty(Faculty faculty) {

		return facultyRepository.save(faculty);
	}

	@Override
	public Boolean deleteFacultyById(Long facultyId) {
		if (facultyRepository.existsById(facultyId)) {
			facultyRepository.deleteById(facultyId);
			return true;
		}
		return false;
	}

	@Override
	public Faculty getFacultyById(Long facultyId) {
		if (facultyRepository.existsById(facultyId)) {
			return facultyRepository.getReferenceById(facultyId);
		}
		return null;
	}

	@Override
	public List<FacultyDetails> getAllFacultys() {
		List < Faculty > facultys = facultyRepository.findAll();
		List < FacultyDetails > facultysDetails = new ArrayList < FacultyDetails > ();
		facultys.forEach(faculty -> {
			
				FacultyDetails facultyDetails = new FacultyDetails();
			facultyDetails.setId(faculty.getId());
			facultyDetails.setEmail(faculty.getEmail());
			facultyDetails.setName(faculty.getName());
			facultyDetails.setUsername(faculty.getUsername());
			facultyDetails.setPassword(faculty.getPassword());
			facultysDetails.add(facultyDetails);


		
	} );
		
		
		return facultysDetails;
	}


	@Override
	public Faculty editFaculty(FacultyDetails facultyDetails, Long facultyId) {
		Faculty faculty = facultyRepository.getReferenceById(facultyId);

		faculty.setName(facultyDetails.getName());
		faculty.setUsername(facultyDetails.getUsername());
		faculty.setPassword(facultyDetails.getPassword());
		faculty.setEmail(facultyDetails.getEmail());




		faculty = facultyRepository.save(faculty);
	

		String subject = "Welcome to ☀ Sasa Attendance";
		String message = "<p>Your Profile is created on <strong>🐧 Sasa Attendance</strong></p>"
		+ "<p>You Have Class Si Level Access into Our system </p>"
		+ "<p>Click on this link: https://apniattendance.web.app </p>"
		+ "<p>Use Below Credentials to Log into Our System </p>"
		+ "<p>Username: " + faculty.getUsername() + "</p>"
		+ "<p>Password:" + faculty.getPassword() + "</p>"
		+ "<p style='color:red;'>NOTE: DO NOT SHARE YOUR CREDENTIALS WITH ANYONE!!</p>";

		this.sendEmail(faculty.getEmail(), subject, message);

		return faculty;
	}

	public void sendEmail(String receiver, String subject, String message) {
		try {
			mailService.sendMail(receiver, subject, message);
		} catch (MailException | MessagingException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	

}
