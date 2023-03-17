package com.attendance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.attendance.dto.SiDetails;
import com.attendance.dto.UserCredentials;
import com.attendance.model.Class;
import com.attendance.model.Si;

import com.attendance.repository.ClassRepository;
import com.attendance.repository.SiRepository;
import com.attendance.service.ClassService;
import com.attendance.service.SiService;
import com.attendance.service.mail.MailService;

@Service
public class SiServiceImplementation implements SiService {

	@Autowired
	private SiRepository  siRepository;

	@Autowired
	private ClassService classsService;


	@Autowired
	private ClassRepository  classsRepository;

	@Autowired
	private MailService mailService;

	@Override
	public Si verifyLoginCredentials(UserCredentials userCredentials) {
			Si si = siRepository.getSiWithUsernameAndPassword(userCredentials.getUsername(), userCredentials.getPassword());
		return si;
	}

	@Override
	public Si addSi(SiDetails siDetails) {
		List < Si > users = siRepository.findAll();
		for (Si user:users) {
			if (user.getUsername().equals(siDetails.getUsername()) || user.getEmail().equals(siDetails.getEmail())) {
				return null;
			}
		}
		
		Si si = new Si();
		si.setName(siDetails.getName());
		si.setUsername(siDetails.getUsername());
		si.setPassword(siDetails.getPassword());
		si.setEmail(siDetails.getEmail());
		
		
		Class classs = classsService.CreateClass(siDetails.getYear(),siDetails.getBranch(), siDetails.getSection(), siDetails.getSemester(), si, siDetails.getDepartmentId());
		
		si.setClasss(classs);
		si = siRepository.save(si);
		si.setClasss(null);


			
			
			
			
			String subject = "Welcome to ☀ Sasa Attendance";
			String message = "<p>Your Profile is created on <strong>🐧 Sasa Attendance</strong></p>"
			+ "<p>You Have Class Si Level Access into Our system </p>"
			+ "<p>Click on this link: https://apniattendance.web.app </p>"
			+ "<p>Use Below Credentials to Log into Our System </p>"
			+ "<p>Username: " + si.getUsername() + "</p>"
			+ "<p>Password:" + si.getPassword() + "</p>"
			+ "<p style='color:red;'>NOTE: DO NOT SHARE YOUR CREDENTIALS WITH ANYONE!!</p>";


		this.sendEmail(si.getEmail(), subject, message);


		return si;

	}

	@Override
	public Si saveSi(Si si) {

		return siRepository.save(si);
	}

	@Override
	public Boolean deleteSiById(Long siId) {
		if (siRepository.existsById(siId)) {
			siRepository.deleteById(siId);
			return true;
		}
		return false;
	}

	@Override
	public Si getSiById(Long siId) {
		if (siRepository.existsById(siId)) {
			return siRepository.getReferenceById(siId);
		}
		return null;
	}

	@Override
	public List<SiDetails> getAllSis() {
		List < Si > sis = siRepository.findAll();
		List < SiDetails > sisDetails = new ArrayList < SiDetails > ();
		sis.forEach(si -> {
			if(si.getClass() != null) {
				SiDetails siDetails = new SiDetails();
			siDetails.setId(si.getId());
			siDetails.setBranch(si.getClasss().getBranch());
			siDetails.setSection(si.getClasss().getSection());
			siDetails.setYear(si.getClasss().getYear());
			siDetails.setSemester(si.getClasss().getSemester());
			siDetails.setEmail(si.getEmail());
			siDetails.setName(si.getName());
			siDetails.setUsername(si.getUsername());
			siDetails.setPassword(si.getPassword());
			sisDetails.add(siDetails);


		}
	} );
		
		
		return sisDetails;
	}


@Override
public Si editSi(SiDetails siDetails, Long siId) {
		Si si = siRepository.getReferenceById(siId);

	si.setName(siDetails.getName());
	si.setUsername(siDetails.getUsername());
	si.setPassword(siDetails.getPassword());
	si.setEmail(siDetails.getEmail());


	if (! siDetails.getBranch().equals(si.getClasss().getBranch()) && siDetails.getSection().equals(si.getClasss().getSection()) && siDetails.getYear().equals(si.getClasss().getYear())&& siDetails.getSemester().equals(si.getClasss().getSemester())) {
		
		Class classs = classsRepository.getReferenceById(si.getClasss().getId());
		classs.setBranch(siDetails.getBranch());

		classs.setSection(siDetails.getSection());
		classs.setSemester(siDetails.getSemester());
		classs.setSi(si);
		classs.setYear(siDetails.getYear());
	
	}

	si = siRepository.save(si);
	si.setClasss(null);
	String subject = "Welcome to ☀ Sasa Attendance";
	String message = "<p>Your Profile is created on <strong>🐧 Sasa Attendance</strong></p>"
	+ "<p>You Have Class Si Level Access into Our system </p>"
	+ "<p>Click on this link: https://apniattendance.web.app </p>"
	+ "<p>Use Below Credentials to Log into Our System </p>"
	+ "<p>Username: " + si.getUsername() + "</p>"
	+ "<p>Password:" + si.getPassword() + "</p>"
	+ "<p style='color:red;'>NOTE: DO NOT SHARE YOUR CREDENTIALS WITH ANYONE!!</p>";



	this.sendEmail(si.getEmail(), subject, message);

	return si;
}

	public void sendEmail(String receiver, String subject, String message) {
	try {
		mailService.sendMail(receiver, subject, message);
	} catch (MailException | MessagingException e) {
		
		e.printStackTrace();
	}
}
	

	

}
