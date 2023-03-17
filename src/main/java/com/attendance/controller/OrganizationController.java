package com.attendance.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class OrganizationController {
	
	@RequestMapping("/organizationDashboard")
	public String superAdminDashboard(HttpSession session,Model model) throws Exception {
		Long superAdminId = (Long)session.getAttribute("superAdminId");
		if(superAdminId != null) {
			model.addAttribute("superAdminId",superAdminId);
			return "OrganisationDashboard";
		}
		
		 throw new Exception("Login First");
	
	}

}
