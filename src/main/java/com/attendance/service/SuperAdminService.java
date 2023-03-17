package com.attendance.service;

import com.attendance.dto.UserCredentials;
import com.attendance.model.SuperAdmin;

public interface SuperAdminService {
	SuperAdmin verifyLoginCredentials(UserCredentials userCredentials) ;
	SuperAdmin createSuperAdmin(String name , String username,  String password, String email );
	SuperAdmin getAdminById(Long superAdminId);
	
	
}
