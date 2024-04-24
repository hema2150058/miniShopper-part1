package com.mini.service;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.model.Address;
import com.mini.model.Role;
import com.mini.model.User;
import com.mini.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public List<User> getAllCustomers(){
		Role customerRole = new Role();
		customerRole.setRoleName("Customer");
		
		List<User> customers = userRepo.findByRoles(customerRole);
		return customers;
	}
	
	public void updateUserAddress(String userName, Address updatedAddress) {
		User user = userRepo.findByUserName(userName);
//				.orElseThrow(()->new EntityNotFoundException("User not found with Username: "+ userName));
		if(user == null) {
			System.out.println("not found");
		}
		
		user.getAddress().setAddressLine(updatedAddress.getAddressLine());
		user.getAddress().setStreet(updatedAddress.getStreet());
		user.getAddress().setCity(updatedAddress.getCity());
		user.getAddress().setState(updatedAddress.getState());
		user.getAddress().setPincode(updatedAddress.getPincode());
		
		userRepo.save(user);

	}
}
