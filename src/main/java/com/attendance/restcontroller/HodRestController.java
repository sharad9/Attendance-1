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

import com.attendance.dto.HodDetails;
import com.attendance.dto.UserCredentials;
import com.attendance.model.Hod;
import com.attendance.service.HodService;

@RestController
@RequestMapping("/hod")
public class HodRestController {


	@Autowired
	private HodService hodService;


	@PostMapping("verifyLoginCredentials")
	public boolean verifyLoginCredentials(@RequestBody UserCredentials userCredentials, HttpSession session) {
		Hod hod = hodService.verifyLoginCredentials(userCredentials);
		if (hod != null) {
			session.setAttribute("hodId", hod.getId());
			session.setAttribute("departmentId", hod.getDepartment().getId());
			return true;
		}

		return false;
	}

	@PostMapping("/addHod")
	public Hod addHod(@RequestBody HodDetails hodDetails) {
	
		return hodService.addHod(hodDetails);
	}


	@DeleteMapping("/deleteHod/{hodId}")
	public Boolean deleteHod(@PathVariable("hodId") Long hodId) {
		return hodService.deleteHodById(hodId);
	}

	@GetMapping("/getAllHods")
	public List<HodDetails> getAllHods() {
		return hodService.getAllHods();
	}
	@PostMapping("/editHod/{hodId}")
	public ResponseEntity<Hod> editHod(@RequestBody HodDetails hod, @PathVariable("hodId") Long hodId) {
		Hod responseHod = hodService.editHod(hod, hodId);
		return ResponseEntity.ok().body(responseHod);
	}



}
