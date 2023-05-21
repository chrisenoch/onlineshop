package com.chrisenoch.onlineshop.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenoch.onlineshop.dao.StockReservedByUserDao;
import com.chrisenoch.onlineshop.entity.StockReservedByUser;
import com.chrisenoch.onlineshop.entity.User;

@Repository
@Profile("jpa")
public class StockReservedByUserDaoJPAImpl implements StockReservedByUserDao{
	
	EntityManager eF ;

	public StockReservedByUserDaoJPAImpl(EntityManager eF) {
		this.eF = eF;
	}
	
	@Override
	@Transactional
	public void save(StockReservedByUser stockReservedByUser) {
				eF.merge(stockReservedByUser);
		
	}
	
	@Override
	public void delete(StockReservedByUser stockReservedByUser) {
				eF.remove(stockReservedByUser);
		
	}
	
	@Override
	public void deleteStockReservedByUser(User theUser) {
				
				String sqlQuery = "delete from StockReservedByUser where user=:theUser";
				
				Query theQuery = eF
						.createQuery(sqlQuery);
				
				theQuery.setParameter("theUser", theUser);
				
				theQuery.executeUpdate();
		
	}


	@Override
	public List<StockReservedByUser> getStockReservedByUser(User theUser) {
		
		int userId = theUser.getiD();

		String sqlQuery = "from StockReservedByUser where user.id=:id"; 

		TypedQuery<StockReservedByUser> theQuery = 
				eF.createQuery(sqlQuery,
						StockReservedByUser.class);
		
		theQuery.setParameter("id", userId);
		
		List<StockReservedByUser> theUsers = theQuery.getResultList();
		
		return theUsers;
		
	}
	
	
	
}
