package com.movie.app.service;

import java.util.List;
import com.movie.app.model.MovieCategory;
import com.movie.app.model.User;



public interface UserService {

	public User findUserById(int id);
	
	public List<User> findAllUsers();
	public User findUser(int userId);
	 
	public void deleteAccount(User user);

	public void createAccount(User user, List<MovieCategory> categories);
	  
	public User editAccount(User user, List<MovieCategory> categories);
	 
	  /**
	   * Return the respective User object if username and password are correct, null otherwise
	   * @param username
	   * @param pwd
	   * @return
	   */
	public User authenticate(String username, String pwd);
}
