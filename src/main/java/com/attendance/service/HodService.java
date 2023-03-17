package com.attendance.service;

import java.util.List;


import com.attendance.dto.HodDetails;
import com.attendance.dto.UserCredentials;
import com.attendance.model.Hod;

public interface HodService {
	
	Hod verifyLoginCredentials(UserCredentials userCredentials);
	Hod addHod(HodDetails hodDetails);
	Hod saveHod(Hod hod);
	Boolean deleteHodById(Long hodId);
	Hod getHodById(Long hodId);
	List<HodDetails> getAllHods();
	Hod editHod(HodDetails hodDetails, Long hodId);
	

}
