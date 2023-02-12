package com.movie.app.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int roleId;
	
	private String roleName;
	
	@ManyToMany(mappedBy = "roleSet")
	private List<User> userRoles;
	
	
	public Role() {
		
	}

	public Role(String roleName, List<User> userRoles) {
		super();
		this.roleName = roleName;
		this.userRoles = userRoles;
	}

	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public List<User> getUserRoles() {
		return userRoles;
	}


	public void setUserRoles(List<User> userRoles) {
		this.userRoles = userRoles;
	}
	
	
}
