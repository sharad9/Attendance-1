package com.attendance.model;


import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "department", fetch = FetchType.LAZY)
	private Hod hod;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;
	
	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Lecture> lectures = new HashSet<Lecture>();
	
		
	
	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Class> classes = new HashSet<Class>();
	
	@ManyToMany( cascade = CascadeType.ALL)
	private Set<Subject> subjects = new HashSet<Subject>();
	


//	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<Student> students = new HashSet<Student>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hod getHod() {
		return hod;
	}

	public void setHod(Hod hod) {
		this.hod = hod;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Set<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(Set<Lecture> lectures) {
		this.lectures = lectures;
	}


	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}



	
	
	
}
