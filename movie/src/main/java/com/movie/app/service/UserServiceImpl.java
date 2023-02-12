package com.movie.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.movie.app.model.MovieCategory;
import com.movie.app.model.Role;
import com.movie.app.model.User;
import com.movie.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleService roleService;

	@Override
	public User findUserById(int id) {
		return userRepo.findById(id);
	}

	@Override
	public User authenticate(String username, String pwd) {
		return userRepo.findUserByNamePwd(username, pwd);
	}

	// findAll
	@Override
	@Transactional
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	// create
	@Override
	@Transactional
	public void createAccount(User user, List<MovieCategory> categories) {

		//all create user will become User role(hard-coded);
		
		Role role = roleService.findRoleByRoleName("User");
		
		List<Role> roleList = new ArrayList<Role>();
		roleList.add(role);
		user.setRoleSet(roleList);
		
		
		String setPref = "";

		for (MovieCategory c : categories) {
			setPref += c.getMovieCategoryName() + "|";
		}

		user.setSetPref(setPref);
		userRepo.save(user);

	}

	// findById
	@Override
	public User findUser(int id) {
		return userRepo.findById(id);
	}

	// delete
	@Override
	@Transactional
	public void deleteAccount(User user) {
		userRepo.delete(user);

	}

	// edit
	@Override
	@Transactional
	public User editAccount(User user, List<MovieCategory> categories) {

		String setPref = "";

		for (MovieCategory c : categories) {
			setPref += c.getMovieCategoryName() + "|";
		}
		
		//set edit user also to User Role
		Role role = roleService.findRoleByRoleName("User");
		
		List<Role> roleList = new ArrayList<Role>();
		roleList.add(role);
		user.setRoleSet(roleList);
		//
		
		user.setSetPref(setPref);

		return userRepo.saveAndFlush(user);
	}

}
