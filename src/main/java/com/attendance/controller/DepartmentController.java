package com.attendance.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DepartmentController {

	@RequestMapping("/departmentDashboard")
	public String superHodDashboard(HttpSession session, Model model) throws Exception {
		Long hodId = (Long)session.getAttribute("hodId");
		Long departmentId = (Long)session.getAttribute("departmentId");
	if (hodId != null) {
		model.addAttribute("hodId", hodId);
		model.addAttribute("departmentId", departmentId);

		return "DepartmentDashboard";
	}

	throw new Exception("Login First");

}

}