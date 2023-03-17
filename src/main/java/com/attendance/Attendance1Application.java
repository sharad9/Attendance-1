package com.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.attendance.model.SuperAdminCredentials;
import com.attendance.service.SuperAdminService;

@EnableCaching
@SpringBootApplication
public class Attendance1Application implements CommandLineRunner {
	 
	@Autowired
	private SuperAdminCredentials superAdminProperties;
	
	@Autowired
	private SuperAdminService superAdminService;
	@Override
	public void run(String... args) throws Exception {
		superAdminService.createSuperAdmin(superAdminProperties.getName(), superAdminProperties.getUsername(), superAdminProperties.getPassword(), superAdminProperties.getEmail());
	}
	public static void main(String[] args) {
		SpringApplication.run(Attendance1Application.class, args);
	}

}
