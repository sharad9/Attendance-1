package com.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.attendance.model.Hod;

@Repository
public interface HodRepository extends JpaRepository<Hod, Long> {

	@Query("Select hod from Hod hod where hod.username=?1 and hod.password=?2")
	Hod getHodWithUsernameAndPassword(String username, String password);
}

