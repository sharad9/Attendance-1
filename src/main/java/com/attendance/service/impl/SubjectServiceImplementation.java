package com.attendance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.model.Subject;
import com.attendance.repository.SubjectRepository;
import com.attendance.service.DepartmentService;
import com.attendance.service.LectureService;
import com.attendance.service.SubjectService;

@Service
public class SubjectServiceImplementation implements SubjectService {


	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private LectureService lectureService;
	
	@Override
	public Subject saveSubject(Subject subject, Long departmentId) {
		
		
		return null;
	}

	@Override
	public List<Subject> getAllSubjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteSubjectWithId(Long subjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject getSubjectWithId(Long subjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subject editSubject(Subject newSubject, Long subjecId) {
		// TODO Auto-generated method stub
		return null;
	}

}
