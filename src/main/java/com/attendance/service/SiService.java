package com.attendance.service;

import java.util.List;

import com.attendance.dto.SiDetails;
import com.attendance.dto.UserCredentials;
import com.attendance.model.Si;

public interface SiService {

	Si verifyLoginCredentials(UserCredentials userCredentials);
	Si addSi(SiDetails siDetails);
	Si saveSi(Si si);
	Boolean deleteSiById(Long siId);
	Si getSiById(Long siId);
	List<SiDetails> getAllSis();
	Si editSi(SiDetails siDetails, Long siId);
}
