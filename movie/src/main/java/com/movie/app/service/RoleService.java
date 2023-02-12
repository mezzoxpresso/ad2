package com.movie.app.service;

import java.util.List;

import com.movie.app.model.Role;

public interface RoleService {
	List<Role> findAllRoles();
	Role findRole(String roleId);
	Role createRole(Role role);
	Role changeRole(Role role);
	void removeRole(Role role);
	List<String> findAllRolesNames();
	List<Role> findRoleByName(String name);
	
	public Role findRoleByRoleName(String name);
}
