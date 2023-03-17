package com.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.attendance.model.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long>{

}
