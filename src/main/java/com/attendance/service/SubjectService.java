package com.attendance.service;

import java.util.List;

import com.attendance.model.Subject;

public interface SubjectService {
	Subject saveSubject(Subject subject, Long departmentId);
	List<Subject> getAllSubjects();
	Boolean deleteSubjectWithId(Long subjectId);
	Subject getSubjectWithId(Long subjectId);
	Subject editSubject(Subject newSubject, Long subjecId);

}
