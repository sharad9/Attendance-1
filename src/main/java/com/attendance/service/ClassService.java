package com.attendance.service;

import com.attendance.model.Class;

import com.attendance.model.Si;


public interface ClassService {

	Class CreateClass(Integer year,String branch, Integer section,Integer semester,Si si,Long departmentId);
	Class saveClass(Class classObj);
	Class getClassById(Long classId);
}
