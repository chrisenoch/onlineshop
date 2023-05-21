package com.chrisenoch.onlineshop.dao;

import com.chrisenoch.onlineshop.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
