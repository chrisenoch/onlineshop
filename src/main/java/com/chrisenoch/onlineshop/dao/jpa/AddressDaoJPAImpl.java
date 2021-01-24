package com.chrisenoch.onlineshop.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.dao.AddressDao;
import com.chrisenoch.onlineshop.entity.Address;

@Repository
@Profile("jpa")
public class AddressDaoJPAImpl implements AddressDao {

	EntityManager eF ;

	public AddressDaoJPAImpl (EntityManager eF) {
		this.eF = eF;
	}
	

	@Override
	public void save(Address theAddress) {
				eF.merge(theAddress);
		
	}
	
	@Override
	public void delete(Address theAddress) {

				eF.remove(theAddress);
		
	}
	
	@Override
	public void deleteAllAddresses(List<Address> theAddresses) {
				
				for(Address address: theAddresses) {
					eF.remove(address);
				}			
	}
	
	
	
	@Override
	public List<Address> getAllAddresses(int userId) throws NoResultException {		
			
			String sqlQuery = "from Address where user.iD=:userId"; 
			
			TypedQuery<Address> theQuery = 
					eF.createQuery(sqlQuery, Address.class);
			
			theQuery.setParameter("userId", userId);
			List<Address> theAddresses = theQuery.getResultList();
	
			return theAddresses;
			
	}
	
	@Override
	public Address getAddress(int addressId) throws NoResultException {		
			return eF.find(Address.class, addressId);		
	}
	
	@Override
	public void setDefaultAddress(int addressId, int userId) throws NoResultException {		
			
			String sqlQuery = "update Address set defaultAddress=false where user.iD = " + userId; 
			
			Query theQuery = eF.createQuery(sqlQuery);
			
			theQuery.executeUpdate();
			
			String sqlQuery2 = "update Address set defaultAddress=true where id = " + addressId; 
			
			Query theQuery2 = eF.createQuery(sqlQuery2);
			
			theQuery2.executeUpdate();
			
	}

}
