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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="classs")
public class Class {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
		
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="department_id")
	private Department department;
	
    private String branch;
	private Integer semester;
	private Integer section;
	private Integer year;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Student> students = new HashSet<Student>();
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "classs", fetch = FetchType.LAZY)
	private Si si;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getSemester() {
		return semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Si getSi() {
		return si;
	}

	public void setSi(Si si) {
		this.si = si;
	}

	

	
	
}
