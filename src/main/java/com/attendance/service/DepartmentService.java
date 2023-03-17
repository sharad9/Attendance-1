package com.attendance.service;


import com.attendance.model.Department;
import com.attendance.model.Hod;


public interface DepartmentService {
	Department CreateDepartment(String courseName, Hod hod,Long courseId);
	Department saveDepartment(Department department);
	Department getDepartmentById(Long departmentId);
	
}
