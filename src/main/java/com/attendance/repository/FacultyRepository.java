package com.attendance.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.attendance.model.Faculty;


@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long>{
	@Query("Select faculty from Faculty faculty where faculty.username=?1 and faculty.password=?2")
	Faculty getFacultyWithUsernameAndPassword(String username, String password);
	@Query("Select faculty from Faculty faculty where faculty.username=?1 or faculty.email=?2")
	Faculty getFacultyWithUsernameOrEmail(String username, String email);

}
