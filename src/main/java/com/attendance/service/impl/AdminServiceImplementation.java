package com.attendance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.attendance.dto.AdminDetails;
import com.attendance.dto.UserCredentials;
import com.attendance.model.Admin;
import com.attendance.model.Course;
import com.attendance.repository.AdminRepository;
import com.attendance.repository.CourseRepository;
import com.attendance.service.AdminService;
import com.attendance.service.CourseService;
import com.attendance.service.mail.MailService;


@Service
public class AdminServiceImplementation implements AdminService{

	@Autowired
	private AdminRepository  adminRepository;
	
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseRepository  courseRepository;
	
	@Autowired
	private MailService mailService;
	
	@Override
	public Admin verifyLoginCredentials(UserCredentials userCredentials) {
			Admin admin = adminRepository.getAdminWithUsernameAndPassword(userCredentials.getUsername(), userCredentials.getPassword());
			return admin;
	}

	@Override
	public Admin addAdmin(AdminDetails adminDetails) {
		
		
		List<Admin> users = adminRepository.findAll();
		for(Admin user:users) {
			
			if(user.getUsername().equals(adminDetails.getUsername()) || user.getEmail().equals(adminDetails.getEmail())) {
				return null;
			}
		}
		
			Admin admin = new Admin();
			admin.setName(adminDetails.getName());
			admin.setUsername(adminDetails.getUsername());
			admin.setPassword(adminDetails.getPassword());
			admin.setEmail(adminDetails.getEmail());	
			
			
			Course course = courseService.CreateCourse(adminDetails.getCourse(),admin);
			
			admin.setCourse(course);
			admin = adminRepository.save(admin);
			admin.setCourse(null);
			
			
			
			

			String subject = "Welcome to ☀ Sasa Attendance";
			String message = "<p>Your Profile is created on <strong>🐧 Sasa Attendance</strong></p>"
			+ "<p>You Have Class Si Level Access into Our system </p>"
			+ "<p>Click on this link: https://apniattendance.web.app </p>"
			+ "<p>Use Below Credentials to Log into Our System </p>"
			+ "<p>Username: " + admin.getUsername() + "</p>"
			+ "<p>Password:" + admin.getPassword() + "</p>"
			+ "<p style='color:red;'>NOTE: DO NOT SHARE YOUR CREDENTIALS WITH ANYONE!!</p>";
			
			this.sendEmail(admin.getEmail(), subject, message);
			
			
			return admin;
		
	}

	@Override
	public Admin saveAdmin(Admin admin) {
		
		return adminRepository.save(admin);
	}

	@Override
	public Boolean deleteAdminById(Long adminId) {
		if(adminRepository.existsById(adminId)) {
			adminRepository.deleteById(adminId);
			return true;
		}
		return false;
	}

	@Override
	public Admin getAdminById(Long adminId) {
		if(adminRepository.existsById(adminId)) {
			return adminRepository.getReferenceById(adminId);
		}
		return null;
	}

	@Override
	public List<AdminDetails> getAllAdmins() {
		List<Admin> admins = adminRepository.findAll();
		List<AdminDetails> adminsDetails = new ArrayList<AdminDetails>();
		admins.forEach(admin ->{
			if(admin.getCourse()!=null) {
				AdminDetails adminDetails = new AdminDetails();
				adminDetails.setId(admin.getId());
				adminDetails.setCourse(admin.getCourse().getName());
				adminDetails.setEmail(admin.getEmail());
				adminDetails.setName(admin.getName());
				adminDetails.setUsername(admin.getUsername());
				adminDetails.setPassword(admin.getPassword());
				adminsDetails.add(adminDetails);
			
				
			}
		} );
		
		
		return adminsDetails;
	}
	

	@Override
	public Admin editAdmin(AdminDetails adminDetails, Long adminId) {
		Admin admin = adminRepository.getReferenceById(adminId);
		
		admin.setName(adminDetails.getName());
		admin.setUsername(adminDetails.getUsername());
		admin.setPassword(adminDetails.getPassword());
		admin.setEmail(adminDetails.getEmail());	
		
		if(! admin.getCourse().getName().equals(adminDetails.getCourse())) {
			Course course = courseRepository.getReferenceById(admin.getCourse().getId());
			course.setName(adminDetails.getCourse());
		}
		
		admin = adminRepository.save(admin);
		admin.setCourse(null);

		String subject = "Welcome to ☀ Sasa Attendance";
		String message = "<p>Your Profile is created on <strong>🐧 Sasa Attendance</strong></p>"
		+ "<p>You Have Class Si Level Access into Our system </p>"
		+ "<p>Click on this link: https://apniattendance.web.app </p>"
		+ "<p>Use Below Credentials to Log into Our System </p>"
		+ "<p>Username: " + admin.getUsername() + "</p>"
		+ "<p>Password:" + admin.getPassword() + "</p>"
		+ "<p style='color:red;'>NOTE: DO NOT SHARE YOUR CREDENTIALS WITH ANYONE!!</p>";
		
		this.sendEmail(admin.getEmail(), subject, message);
		
		return admin;
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
