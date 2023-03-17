package com.attendance.service;
import java.util.List;

import com.attendance.dto.AdminDetails;
import com.attendance.dto.UserCredentials;
import com.attendance.model.Admin;
public interface AdminService {
	
	Admin verifyLoginCredentials(UserCredentials userCredentials);
	Admin addAdmin(AdminDetails adminDetails);
	Admin saveAdmin(Admin admin);
	Boolean deleteAdminById(Long adminId);
	Admin getAdminById(Long adminId);
	List<AdminDetails> getAllAdmins();
	Admin editAdmin(AdminDetails adminDetails, Long adminId);
	

}
