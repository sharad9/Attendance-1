package com.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.model.ClassLog;

@Repository
public interface ClassLogRepository extends JpaRepository<ClassLog, Long> {

		
}
