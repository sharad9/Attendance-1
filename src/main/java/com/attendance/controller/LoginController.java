package com.attendance.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
	
	@RequestMapping("/")
	public String login(HttpSession session){
		 session.invalidate();
		return "login";
		
	}
	  
}
