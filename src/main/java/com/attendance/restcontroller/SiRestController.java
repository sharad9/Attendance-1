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

import com.attendance.dto.SiDetails;
import com.attendance.dto.UserCredentials;
import com.attendance.model.Si;
import com.attendance.service.SiService;

@RestController
@RequestMapping("/si")
public class SiRestController {


	@Autowired
	private SiService siService;


	@PostMapping("verifyLoginCredentials")
	public boolean verifyLoginCredentials(@RequestBody UserCredentials userCredentials, HttpSession session) {
		Si si = siService.verifyLoginCredentials(userCredentials);
		if (si != null) {
			session.setAttribute("siId", si.getId());
			session.setAttribute("classId", si.getClasss().getId());
			return true;
		}

		return false;
	}

	@PostMapping("/addSi")
	public Si addSi(@RequestBody SiDetails siDetails) {

		return siService.addSi(siDetails);
	}


	@DeleteMapping("/deleteSi/{siId}")
	public Boolean deleteSi(@PathVariable("siId") Long siId) {
		return siService.deleteSiById(siId);
	}

	@GetMapping("/getAllSis")
	public List<SiDetails> getAllSis() {
		return siService.getAllSis();
	}
	@PostMapping("/editSi/{siId}")
	public ResponseEntity<Si> editSi(@RequestBody SiDetails si, @PathVariable("siId") Long siId) {
		Si responseSi = siService.editSi(si, siId);
		return ResponseEntity.ok().body(responseSi);
	}



}
