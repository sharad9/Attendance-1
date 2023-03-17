package com.attendance.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@Column(name = "name", length = 5)
	private String name;
	

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "course", fetch = FetchType.LAZY)
	private Admin admin;
	
	@OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Department> departments = new HashSet<Department>();

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

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	public Course(Long id, String name, Admin admin, Set<Department> departments) {
		super();
		this.id = id;
		this.name = name;
		this.admin = admin;
		this.departments = departments;
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
