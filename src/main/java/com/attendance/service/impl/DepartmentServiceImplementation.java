package com.attendance.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.attendance.model.Department;
import com.attendance.model.Hod;
import com.attendance.repository.CourseRepository;
import com.attendance.repository.DepartmentRepository;
import com.attendance.service.DepartmentService;


@Service
public class DepartmentServiceImplementation implements DepartmentService {
	

	@Autowired
	private DepartmentRepository  departmentRepository;
	@Autowired
	private CourseRepository courseRepository;  
    
	@Override
	public Department CreateDepartment(String departmentName, Hod hod,Long courseId) {
		List<Department> departments = departmentRepository.findAll();
		for(Department departmentObj:departments) {
			if(departmentObj.getName().equals(departmentName)) {
			
				
				return null;
			}
		}
		
			
			Department department = new Department();
		
			department.setClasses(null);
		
			department.setHod(hod);
			department.setLectures(null);
			department.setSubjects(null);
			department.setCourse(courseRepository.getReferenceById(courseId));
			department.setName(departmentName);
			//department.setStudents(null);
			
			
			return saveDepartment(department);
	}

	@Override
	public Department saveDepartment(Department department) {
	
		return departmentRepository.save(department);
	}

	@Override
	public Department getDepartmentById(Long departmentId) {
		
		return departmentRepository.getReferenceById(departmentId);
	}


	


}
