package com.attendance.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.attendance.model.Hod;
import com.attendance.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


	@Query("select student from Student student where student.classs.id=?1")
	List<Student> getAllStudentsByClassId(Long classId); 
//	@Query("select student from Student student where student.department.id=?1")
//	List<Student> getAllStudentsByDepartmentId(Long departmentId);
	@Query("Select hod from Hod hod where hod.username=?1 and hod.password=?2")
	Student getStudentWithUsernameAndPassword(String username, String password);
}
