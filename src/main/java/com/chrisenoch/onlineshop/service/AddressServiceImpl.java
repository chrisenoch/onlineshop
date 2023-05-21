package com.chrisenoch.onlineshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chrisenoch.onlineshop.dao.AddressDao;
import com.chrisenoch.onlineshop.entity.Address;

@Service
public class AddressServiceImpl implements AddressService {
	
	private AddressDao addressDao;
	
	@Autowired	
	public AddressServiceImpl(AddressDao addressDao) {
		super();
		this.addressDao = addressDao;
	}

	@Override
	@Transactional
	public void save(Address theAddress) {
		addressDao.save(theAddress);

	}
	
	@Override
	@Transactional
	public void delete(Address theAddress) {
		addressDao.delete(theAddress);

	}
	
	@Override
	@Transactional
	public void deleteAllAddresses(List<Address> theAddresses) {
		addressDao.deleteAllAddresses(theAddresses);
	}

	@Override
	@Transactional
	public List<Address> getAllAddresses(int userId) {
		return addressDao.getAllAddresses(userId);
	}
	
	@Override
	@Transactional
	public Address getAddress(int addressId) {
		return addressDao.getAddress(addressId);
	}
	
	@Override
	@Transactional
	public void setDefaultAddress(int addressId, int userId) {
		addressDao.setDefaultAddress(addressId, userId);
	}

}
