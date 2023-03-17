package com.attendance.restcontroller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.dto.UserCredentials;
import com.attendance.model.SuperAdmin;
import com.attendance.service.SuperAdminService;

@RestController
@RequestMapping("/superAdmin")
public class SuperAdminRestController {
	@Autowired
	private SuperAdminService superAdminService;
	
	

	@PostMapping("verifyLoginCredentials")
	public boolean verifyLoginCredentials(@RequestBody UserCredentials userCredentials,HttpSession session) {
		SuperAdmin admin = superAdminService.verifyLoginCredentials(userCredentials);
		if(admin !=null) {
			session.setAttribute("superAdminId", admin.getId());		
	
			return true;
		}

		return false;
	}
	

}
