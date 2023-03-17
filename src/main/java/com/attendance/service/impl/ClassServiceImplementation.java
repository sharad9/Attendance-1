package com.attendance.service.impl;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.model.Class;
import com.attendance.model.Department;
import com.attendance.model.Si;
import com.attendance.repository.ClassRepository;
import com.attendance.service.ClassService;
import com.attendance.service.DepartmentService;

@Service
public class ClassServiceImplementation implements ClassService {

	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private DepartmentService departmentService;
	

	@Override
	public Class saveClass(Class classObj) {
		
		return classRepository.save(classObj);
	}


	@Override
	public Class CreateClass(Integer year, String branch, Integer section, Integer semester, Si si, Long departmentId) {
		
			Set<Class> classes = departmentService.getDepartmentById(departmentId).getClasses();
			for(Class classObj:classes) {
				if(classObj.getBranch().equals(branch) && classObj.getSection().equals(section) && classObj.getYear().equals(year) || classObj.getSi().equals(si)) {
				
					
					return null;
				}
			}
			
				
				Class classs = new Class();
				classs.setBranch(branch);
				
				
				Department department = departmentService.getDepartmentById(departmentId);
				department.getClasses().add(classs);
				
				
				//departmentService.saveDepartment(department);
				
				
				classs.setDepartment(department);
				
		
			
				classs.setSection(section);
				classs.setSemester(semester);
				classs.setStudents(null);
				classs.setYear(year);
				classs.setSi(si);
			
		
				
				
				return saveClass(classs);
		}


	@Override
	public Class getClassById(Long classId) {
		
		return classRepository.getReferenceById(classId);
	}
		

	
}
