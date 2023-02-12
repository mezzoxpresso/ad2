package com.movie.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	@NotBlank(message="Name can't be null.")
	private String userName;
	private String age;
	@NotBlank(message="Email can't be null.")
	private String email;
	@NotBlank(message="Password can't be null.")
	private String password;
	private String setPref;

	@ManyToMany(targetEntity = Role.class, cascade = { CascadeType.ALL, CascadeType.PERSIST }, fetch = FetchType.EAGER)
	@JoinTable(name = "userrole", joinColumns = {
			@JoinColumn(name = "userId", referencedColumnName = "userId") }, inverseJoinColumns = {
					@JoinColumn(name = "roleId", referencedColumnName = "roleId") })
	private List<Role> roleSet;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Review> reviews;
    
	
	public User() {}
	
	public User(int userId, String userName, String password) {
		    this.userId=userId;
		    this.userName = userName;
		    this.password = password;
		    }
	
	
	public User(String userName, String profilePhoto,String password, String age, String email,String setPref,List<Role> roleSet, List<Review> reviews) {
		super();
		this.userName = userName;
		this.password = password;
		this.age=age;
		this.email=email;
		this.roleSet = roleSet;
		this.reviews = reviews;
		this.setPref = setPref;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getAge(){
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public String getEmail(){
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
	
	public String getSetPref() {
		return setPref;
	}
	public void setSetPref(String setPref) {
		this.setPref = setPref;
	}
	
	
	public List<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(List<Role> roleSet) {
		this.roleSet = roleSet;
	}
	
	 public List<String> getRoleNames() {
		    List<String> roleList = new ArrayList<>();
		    roleSet.forEach(role -> roleList.add(role.getRoleName()));
		    
		    return roleList;
		  }

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	
}
