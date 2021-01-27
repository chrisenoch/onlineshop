 package com.chrisenoch.onlineshop.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.dao.UsersDao;
import com.chrisenoch.onlineshop.entity.User;

@Repository
@Profile("jpa")
public class UsersDaoJPAImpl implements UsersDao{
	
	EntityManager eF ;

	public UsersDaoJPAImpl(EntityManager eF) {
		this.eF = eF;
	}

	@Override //@Transactional - functionality moved up to service layer
	public List<User> getMembers(int pageStart, int recordsPerPage) {

			// create a query  ... sort by last name
			TypedQuery<User> theQuery = 
					eF.createQuery("from Users order by firstName",
												User.class)
					.setFirstResult(pageStart)
					.setMaxResults(recordsPerPage);
	
		 	List<User> members = theQuery.getResultList();
		 	
		 	members.forEach(System.out::println);
	
			return members;	
		
	}
	
	
	@Override
	public void saveProfilePicture(User theUser, String pictureURL) {
		theUser.setPictureURL(pictureURL);

			eF.merge(theUser); //Learn code.
	}
	
	
	@Override
	public int getTotalMembers() {

			TypedQuery<User> theQuery = 
					eF.createQuery("from Users",
												User.class);

		 	List<User> members = theQuery.getResultList();
		 	int total = members.size();
		 			
			return total;
		
	}
		
	@Override
	public User getUserByUsername(String username) throws NoResultException {		
		
		//Try stmt in case invalid username entered or other problem.
		try {
			String sqlQuery = "from Users where username=:username"; 

			TypedQuery<User> theQuery = 
					eF.createQuery(sqlQuery, User.class);
			
			theQuery.setParameter("username", username);
			
			User theUser;
			theUser = theQuery.getSingleResult();
	
			return theUser;
		} catch(Exception exc) {
			throw exc;
		} 
			
	}

	@Override
	public User getUserByEmail(String email)  {		
		
			String sqlQuery = "from Users where email=:email"; 

			TypedQuery<User> theQuery = 
					eF.createQuery(sqlQuery, User.class);	
			theQuery.setParameter("email", email);
			
			User theUser;
			theUser = theQuery.getSingleResult();

			return theUser;
		
	}

	@Override
	public void save(User theUser) {
				eF.merge(theUser);
		
	}
	
	@Override
	public void delete(User theUser) {
				eF.remove(theUser);
		
	}
	
}
