package com.attendance.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseController {
	
	@RequestMapping("/courseDashboard")
	public String superAdminDashboard(HttpSession session,Model model) throws Exception {
		Long adminId = (Long)session.getAttribute("adminId");
		Long courseId = (Long)session.getAttribute("courseId");
		if(adminId != null ) {
			model.addAttribute("adminId",adminId);
			model.addAttribute("courseId",courseId);
			
			return "CourseDashboard";
		}
		
		throw new Exception("Login First");
	
	}

}