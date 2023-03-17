package com.attendance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.attendance.dto.HodDetails;
import com.attendance.dto.UserCredentials;
import com.attendance.model.Department;
import com.attendance.model.Hod;

import com.attendance.repository.DepartmentRepository;
import com.attendance.repository.HodRepository;

import com.attendance.service.DepartmentService;
import com.attendance.service.HodService;
import com.attendance.service.mail.MailService;

@Service
public class HodServiceImplementation implements HodService {

	@Autowired
	private HodRepository  hodRepository;

	@Autowired
	private DepartmentService departmentService;
	

	@Autowired
	private DepartmentRepository  departmentRepository;

	@Autowired
	private MailService mailService;

	@Override
	public Hod verifyLoginCredentials(UserCredentials userCredentials) {
			Hod hod = hodRepository.getHodWithUsernameAndPassword(userCredentials.getUsername(), userCredentials.getPassword());
		return hod;
	}

	@Override
	public Hod addHod(HodDetails hodDetails) {
		List < Hod > users = hodRepository.findAll();
		for (Hod user:users) {
			if (user.getUsername().equals(hodDetails.getUsername()) || user.getEmail().equals(hodDetails.getEmail())) {
				return null;
			}
		}
		
			Hod hod = new Hod();
		hod.setName(hodDetails.getName());
		hod.setUsername(hodDetails.getUsername());
		hod.setPassword(hodDetails.getPassword());
		hod.setEmail(hodDetails.getEmail());
		

			
		Department department = departmentService.CreateDepartment(hodDetails.getDepartment(), hod,hodDetails.getCourseId());

		hod.setDepartment(department);
		hod = hodRepository.save(hod);
		hod.setDepartment(null);


			
			
			
			
		String subject = "Welcome to ☀ Sasa Attendance";
		String message = "<p>Your Profile is created on <strong>🐧 Sasa Attendance</strong></p>"
		+ "<p>You Have Class Si Level Access into Our system </p>"
		+ "<p>Click on this link: https://apniattendance.web.app </p>"
		+ "<p>Use Below Credentials to Log into Our System </p>"
		+ "<p>Username: " + hodDetails.getUsername() + "</p>"
		+ "<p>Password:" + hodDetails.getPassword() + "</p>"
		+ "<p style='color:red;'>NOTE: DO NOT SHARE YOUR CREDENTIALS WITH ANYONE!!</p>";
		
		
		this.sendEmail(hod.getEmail(), subject, message);


		return hod;

	}

	@Override
	public Hod saveHod(Hod hod) {

		return hodRepository.save(hod);
	}

	@Override
	public Boolean deleteHodById(Long hodId) {
		if (hodRepository.existsById(hodId)) {
			hodRepository.deleteById(hodId);
			return true;
		}
		return false;
	}

	@Override
	public Hod getHodById(Long hodId) {
		if (hodRepository.existsById(hodId)) {
			return hodRepository.getReferenceById(hodId);
		}
		return null;
	}

	@Override
	public List<HodDetails> getAllHods() {
		List < Hod > hods = hodRepository.findAll();
		List < HodDetails > hodsDetails = new ArrayList < HodDetails > ();
		hods.forEach(hod -> {
			if(hod.getDepartment() != null) {
				HodDetails hodDetails = new HodDetails();
			hodDetails.setId(hod.getId());
			hodDetails.setDepartment(hod.getDepartment().getName());
			hodDetails.setEmail(hod.getEmail());
			hodDetails.setName(hod.getName());
			hodDetails.setUsername(hod.getUsername());
			hodDetails.setPassword(hod.getPassword());
			hodsDetails.add(hodDetails);


		}
	} );
		
		
		return hodsDetails;
	}


	@Override
	public Hod editHod(HodDetails hodDetails, Long hodId) {
		Hod hod = hodRepository.getReferenceById(hodId);

		hod.setName(hodDetails.getName());
		hod.setUsername(hodDetails.getUsername());
		hod.setPassword(hodDetails.getPassword());
		hod.setEmail(hodDetails.getEmail());


		if (!hod.getDepartment().getName().equals(hodDetails.getDepartment())) {
				Department department = departmentRepository.getReferenceById(hod.getDepartment().getId());
				department.setName(hodDetails.getDepartment());
		}

		hod = hodRepository.save(hod);
		hod.setDepartment(null);
		String subject = "Welcome to ☀ Sasa Attendance";
		String message = "<p>Your Profile is created on <strong>🐧 Sasa Attendance</strong></p>"
		+ "<p>You Have Class Si Level Access into Our system </p>"
		+ "<p>Click on this link: https://apniattendance.web.app </p>"
		+ "<p>Use Below Credentials to Log into Our System </p>"
		+ "<p>Username: " + hodDetails.getUsername() + "</p>"
		+ "<p>Password:" + hodDetails.getPassword() + "</p>"
		+ "<p style='color:red;'>NOTE: DO NOT SHARE YOUR CREDENTIALS WITH ANYONE!!</p>";


		this.sendEmail(hod.getEmail(), subject, message);

		return hod;
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
