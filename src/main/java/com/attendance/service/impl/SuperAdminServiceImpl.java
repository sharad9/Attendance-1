package com.attendance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.dto.UserCredentials;
import com.attendance.model.SuperAdmin;

import com.attendance.repository.SuperAdminRepository;
import com.attendance.service.SuperAdminService;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {
	
	@Autowired
	private SuperAdminRepository superAdminRepository;


	@Override
	public SuperAdmin verifyLoginCredentials(UserCredentials userCredentials) {
			SuperAdmin admin = superAdminRepository.getSuperAdminWithUsernameAndPassword(userCredentials.getUsername(), userCredentials.getPassword());
			return admin;
	}



	@Override
	public SuperAdmin createSuperAdmin(String name, String username, String password, String email) {
		List<SuperAdmin> users = superAdminRepository.findAll();
		for(SuperAdmin user:users) {
			if(user.getUsername().equals(username) || user.getPassword().equals(password)) {
				return user;
			}
		}
		
			SuperAdmin user = new SuperAdmin();
			user.setName(name);
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);			
			
			
			return superAdminRepository.save(user);
		}


	@Override
	public SuperAdmin getAdminById(Long superAdminId) {
		
		return superAdminRepository.getReferenceById(superAdminId);
	}
	
}


	


