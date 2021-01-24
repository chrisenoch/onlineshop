package com.chrisenoch.onlineshop.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.dao.RoleDao;
import com.chrisenoch.onlineshop.entity.Role;

@Repository
@Profile("jpa")
public class RoleDaoJPAImpl implements RoleDao {
	EntityManager eF ;

	public RoleDaoJPAImpl(EntityManager eF) {
		this.eF = eF;
	}


	@Override
	public Role findRoleByName(String theRoleName) {

		TypedQuery<Role> theQuery = eF.createQuery("from Role where authority=:roleName", Role.class);
		theQuery.setParameter("roleName", theRoleName);
		
		Role theRole = null;
		
		try {
			theRole = theQuery.getSingleResult();
			System.out.println("Testing roles " + theRole);
		} catch (Exception e) {
			theRole = null;
		}
		
		return theRole;
	}
}
