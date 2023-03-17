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

import com.attendance.dto.AdminDetails;
import com.attendance.dto.UserCredentials;
import com.attendance.model.Admin;
import com.attendance.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
	
	
	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("verifyLoginCredentials")
	public boolean verifyLoginCredentials(@RequestBody UserCredentials userCredentials,HttpSession session) {
		Admin admin = adminService.verifyLoginCredentials(userCredentials);
		if(admin !=null) {
			session.setAttribute("adminId", admin.getId());		
			session.setAttribute("courseId", admin.getCourse().getId());
			return true;
		}

		return false;
	}
	
	@PostMapping("/addAdmin")
	public Admin addAdmin(@RequestBody AdminDetails adminDetails ){
		return adminService.addAdmin(adminDetails);
	}
	
	
	@DeleteMapping("/deleteAdmin/{adminId}")
	public Boolean deleteAdmin(@PathVariable("adminId") Long adminId){
		return adminService.deleteAdminById(adminId);
	}
	
	@GetMapping("/getAllAdmins")
	public List<AdminDetails> getAllAdmins(){
		return adminService.getAllAdmins();
	}
	@PostMapping("/editAdmin/{adminId}")
	public ResponseEntity<Admin> editAdmin(@RequestBody AdminDetails admin, @PathVariable("adminId") Long adminId) {
		Admin responseAdmin = adminService.editAdmin(admin, adminId);
		return ResponseEntity.ok().body(responseAdmin);
	}
	
	

}
