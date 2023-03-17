package com.attendance.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClassController {

	@RequestMapping("/classDashboard")
	public String superSiDashboard(HttpSession session, Model model) throws Exception {
		Long siId = (Long)session.getAttribute("siId");
		Long classId = (Long)session.getAttribute("classId");
	if (siId != null) {
		model.addAttribute("siId", siId);
		model.addAttribute("classId", classId);

		return "ClassDashboard";
	}

	throw new Exception("Login First");

}

}