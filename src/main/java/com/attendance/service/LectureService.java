package com.attendance.service;

import java.util.List;

import com.attendance.dto.LectureTimeTable;
import com.attendance.model.Lecture;
import com.attendance.model.Subject;



public interface LectureService {

	 Boolean connectLecturesWithSubjectFaculty();
	 Boolean deleteLectures(Long lectureId);
	 List<Lecture> getAllLecturesOfDayWithDepartmentId(Long departmentId, String day);
	 Lecture addLecture(Lecture lecture, Long classId, Long departmentId, Long facultyId, Long subjectId);
	 Lecture addLecture(LectureTimeTable lectureTimeTable, Long classId, Long departmentId, Long facultyId,
				Long subjectId);
	 void deleteAllLecturesOfSubject(Subject subject);
	 void deleteLectureClass(Lecture lecture);
	 void deleteLectureFromDepartment(Lecture lecture);
	 void deleteFacultyLectures(Lecture lecture);
}
