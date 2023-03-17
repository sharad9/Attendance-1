package com.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.attendance.model.SuperAdmin;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {

	@Query("Select admin from SuperAdmin admin where admin.username=?1 and admin.password=?2")
	SuperAdmin getSuperAdminWithUsernameAndPassword(String username, String password);

	

}
