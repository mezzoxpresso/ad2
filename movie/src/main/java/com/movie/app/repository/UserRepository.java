package com.movie.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movie.app.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	@Query("SELECT u FROM User u WHERE u.userName=:userName AND u.password=:pwd")
	User findUserByNamePwd(@Param("userName")String userName, @Param("pwd")String pwd);
	
	@Query("SELECT u FROM User u where u.userId=:uid")
	User findById(@Param("uid") int uid);

}
