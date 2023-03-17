package com.attendance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.model.Admin;
import com.attendance.model.Course;

import com.attendance.repository.CourseRepository;
import com.attendance.service.CourseService;

@Service
public class CourseServiceImplementation implements CourseService {

	
	@Autowired
	private CourseRepository  courseRepository;
	
	
	@Override
	public Course saveCourse(Course course) {
		
		return courseRepository.save(course);
	}

	@Override
	public Course CreateCourse(String courseName, Admin admin) {
		List<Course> courses = courseRepository.findAll();
		for(Course courseObj:courses) {
			if(courseObj.getName().equals(courseName)) {
			
				
				return null;
			}
		}
		
			
			Course course = new Course();
			course.setAdmin(admin);
			course.setDepartments(null);
			course.setName(courseName);
			
			
			
			return saveCourse(course);
	}

	@Override
	public Course getCourseById(Long courseId) {
		
		return courseRepository.getReferenceById(courseId);
	}

	

	

}
