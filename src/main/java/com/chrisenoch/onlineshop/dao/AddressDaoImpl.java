package com.chrisenoch.onlineshop.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.entity.Address;

@Repository
@Profile("hibernate")
public class AddressDaoImpl implements AddressDao {

	private SessionFactory sessionFactory;
	
	@Autowired
	public AddressDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Address theAddress) {
				Session currentSession = sessionFactory.getCurrentSession();

				currentSession.saveOrUpdate(theAddress);
		
	}
	
	@Override
	public void delete(Address theAddress) {
				Session currentSession = sessionFactory.getCurrentSession();

				currentSession.delete(theAddress);
		
	}
	
	@Override
	public void deleteAllAddresses(List<Address> theAddresses) {
				Session currentSession = sessionFactory.getCurrentSession();	
				//theAddresses.forEach();//Improve code
				
				for(Address address: theAddresses) {
					currentSession.delete(address);
				}			
	}
	
	
	
	@Override
	public List<Address> getAllAddresses(int userId) throws NoResultException {		
		Session currentSession = sessionFactory.getCurrentSession();
			
			String sqlQuery = "from Address where user.iD=:userId"; 
			
			Query<Address> theQuery = 
					currentSession.createQuery(sqlQuery,
												Address.class);
			
			theQuery.setParameter("userId", userId);
			List<Address> theAddresses = theQuery.getResultList();
	
			return theAddresses;
			
	}
	
	@Override
	public Address getAddress(int addressId) throws NoResultException {		
		Session currentSession = sessionFactory.getCurrentSession();
	
			return currentSession .get(Address.class, addressId);		
	}
	
	/**
	 * Deletes the current default address for the given userId and adds a new default address according to addressId and userId.
	 */
	@Override
	public void setDefaultAddress(int addressId, int userId) throws NoResultException {		
		Session currentSession = sessionFactory.getCurrentSession();
			
			String sqlQuery = "update Address set defaultAddress=false where user.iD=:userId"; 
			
			Query theQuery = 
					currentSession.createQuery(sqlQuery);
			
			theQuery.setParameter("userId", userId);
			
			theQuery.executeUpdate();
			
			String sqlQuery2 = "update Address set defaultAddress=true where id=:addressId"; 
			
			Query theQuery2 = 
					currentSession.createQuery(sqlQuery2);
			
			theQuery2.setParameter("addressId", addressId);
			
			theQuery2.executeUpdate();
			
	}

}
