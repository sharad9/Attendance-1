package com.attendance.service;


import com.attendance.model.Admin;
import com.attendance.model.Course;


public interface CourseService {
	Course CreateCourse(String courseName,Admin admin);
	Course saveCourse(Course course);
	Course getCourseById(Long courseId);
}
