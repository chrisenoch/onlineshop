package com.chrisenoch.onlineshop.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.chrisenoch.onlineshop.entity.StockReservedByUser;
import com.chrisenoch.onlineshop.entity.User;

@Repository
@Profile("hibernate")
public class StockReservedByUserDaoImpl implements StockReservedByUserDao{

	private SessionFactory sessionFactory;

	@Autowired
	public StockReservedByUserDaoImpl (SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public void save(StockReservedByUser stockReservedByUser) {
				Session currentSession = sessionFactory.getCurrentSession();

				currentSession.saveOrUpdate(stockReservedByUser);
		
	}
	
	@Override
	public void delete(StockReservedByUser stockReservedByUser) {
				Session currentSession = sessionFactory.getCurrentSession();

				currentSession.delete(stockReservedByUser);
		
	}
	
	@Override
	public void deleteStockReservedByUser(User theUser) {
		
				Session currentSession = sessionFactory.getCurrentSession();
				
				String sqlQuery = "delete from StockReservedByUser where user=:theUser";
				
				Query theQuery = currentSession
						.createQuery(sqlQuery);
				
				theQuery.setParameter("theUser", theUser);
				
				
				theQuery.executeUpdate();
		
	}


	@Override
	public List<StockReservedByUser> getStockReservedByUser(User theUser) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		int userId = theUser.getiD();

		String sqlQuery = "from StockReservedByUser where user.id=:id"; 

		Query<StockReservedByUser> theQuery = 
				currentSession.createQuery(sqlQuery,
						StockReservedByUser.class);
		
		theQuery.setParameter("id", userId);
		
		List<StockReservedByUser> theUsers = theQuery.getResultList();
		
		return theUsers;
		
	}
	
	
	
}
