package com.chrisenoch.onlineshop.dao;

import java.util.List;

import com.chrisenoch.onlineshop.entity.StockReservedByUser;
import com.chrisenoch.onlineshop.entity.User;

public interface StockReservedByUserDao {

	void save(StockReservedByUser stockReservedByUser);

	void delete(StockReservedByUser stockReservedByUser);

	List<StockReservedByUser> getStockReservedByUser(User theUser);

	void deleteStockReservedByUser(User theUser);

}
