package com.chrisenoch.onlineshop.service;

import java.util.List;

import com.chrisenoch.onlineshop.entity.Address;

public interface AddressService {
	
	public void save(Address theAddress);
	public void delete(Address theAddress);
	public void deleteAllAddresses(List<Address> theAddresses);
	public List<Address> getAllAddresses(int userId);
	public Address getAddress(int addressId);
	public void setDefaultAddress(int addressId, int userId);

}
