package com.attendance.repository;

import org.springframework.stereotype.Repository;

import com.attendance.model.Class;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
	
	@Query("select c from Class c where c.department.id=?1")
	Set<Class> getAllClassesByDepartmentId(Long departmentId);
	
	
}
