package com.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.attendance.model.Si;

@Repository
public interface SiRepository extends JpaRepository<Si, Long> {
	
	@Query("Select si from Si si where si.username=?1 and si.password=?2")
	Si getSiWithUsernameAndPassword(String username, String password);

}
