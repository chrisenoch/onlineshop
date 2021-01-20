package com.chrisenoch.onlineshop.dao;

import java.util.List;

import com.chrisenoch.onlineshop.entity.Address;

 public interface AddressDao {
	
	 void save(Address theAddress);
	 void delete(Address theAddress);
	 void deleteAllAddresses(List<Address> theAddresses);
	 List<Address> getAllAddresses(int userId);
	 Address getAddress(int addressId);
	 void setDefaultAddress(int addressId, int userId);

}
