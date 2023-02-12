package com.movie.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movie.app.model.Role;

public interface RoleRepository extends JpaRepository<Role, String>{
  @Query("SELECT r.roleName FROM Role r")
  List<String> findAllRolesNames();
  
  @Query("SELECT r FROM Role r WHERE r.roleName = :name")
  List<Role> findRoleByName(@Param("name") String name);
  
  @Query("SELECT r FROM Role r WHERE r.roleName=:name")
  public Role findRoleByRoleName(@Param("name")String name);
}
