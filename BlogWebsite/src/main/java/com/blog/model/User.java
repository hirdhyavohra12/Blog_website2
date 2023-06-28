package com.blog.model;

import java.util.List;

import com.blog.model.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	@Column(nullable = false)
	private String name;
	
	
	@Column(nullable = false , unique = true)
	@NotEmpty
	@Email(message = "{errors.invalid_email}")
	private String email;
	
	private String password;
	
	@ManyToMany(cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles" , joinColumns = {@JoinColumn(name="USER_ID", referencedColumnName = "ID")},
	inverseJoinColumns = {@JoinColumn(name="ROLE_ID",referencedColumnName = "ID")} )
	private List<Roles> roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	
	public User(User user) {
		this.name=user.getName();		
		this.email=user.getEmail();
		this.password=user.getPassword();
		this.roles=user.getRoles();
	}
	
	public User() {
		
	}
	

}
