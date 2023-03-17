package com.attendance.service;

import java.util.List;

import com.attendance.dto.FacultyDetails;
import com.attendance.dto.UserCredentials;
import com.attendance.model.Faculty;



public interface FacultyService {
	Faculty verifyLoginCredentials(UserCredentials userCredentials);
	Faculty addFaculty(FacultyDetails facultyDetails);
	Faculty saveFaculty(Faculty faculty);
	Boolean deleteFacultyById(Long facultyId);
	Faculty getFacultyById(Long facultyId);
	List<FacultyDetails> getAllFacultys();
	Faculty editFaculty(FacultyDetails facultyDetails, Long facultyId);

}
