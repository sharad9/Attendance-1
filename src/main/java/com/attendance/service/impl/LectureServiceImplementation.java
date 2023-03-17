package com.attendance.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.attendance.dto.LectureTimeTable;
import com.attendance.model.Lecture;
import com.attendance.model.Subject;
import com.attendance.service.LectureService;

@Service
public class LectureServiceImplementation implements LectureService{

	@Override
	public Boolean connectLecturesWithSubjectFaculty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteLectures(Long lectureId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lecture> getAllLecturesOfDayWithDepartmentId(Long departmentId, String day) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lecture addLecture(Lecture lecture, Long classId, Long departmentId, Long facultyId, Long subjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lecture addLecture(LectureTimeTable lectureTimeTable, Long classId, Long departmentId, Long facultyId,
			Long subjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllLecturesOfSubject(Subject subject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLectureClass(Lecture lecture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLectureFromDepartment(Lecture lecture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFacultyLectures(Lecture lecture) {
		// TODO Auto-generated method stub
		
	}

}
