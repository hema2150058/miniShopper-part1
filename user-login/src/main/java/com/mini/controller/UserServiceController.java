package com.mini.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.mini.dto.UserDto;
import com.mini.model.Address;
import com.mini.model.User;
import com.mini.repo.UserRepo;
import com.mini.service.LRService;
import com.mini.service.UserService;
import com.mini.util.JwtUtil;

@RestController
public class UserServiceController {

	@Autowired
	private LRService lrService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtUtil jwtUtil;
	
//	@GetMapping( "/getAllUsers")
//	@PreAuthorize("hasRole('Shopper')")
//    public ResponseEntity<List<User>> getAllUserDetails(@RequestHeader(name = "Authorization") String tokenDup) {
//
//        List<User> result= userRepo.findAll();
//        Iterator<User> it=result.iterator();
//        List<User> usersList=new ArrayList<>();
//
//        while(it.hasNext()) {
//            User row= it.next();
//            System.out.println(row.getRole().toString());
//            if(row.getRole().toString().equals("[Customer]")) {
//                User users=new User();
//               
//                users.setUserName(row.getUserName());
//                users.setUserFirstName(row.getUserFirstName());
//                users.setUserLastName(row.getUserLastName());
//                users.setUserEmail(row.getUserEmail());
//                users.setCreatedDate(row.getCreatedDate());
//                users.setUserPassword(row.getUserPassword());
//                users.setAddress(row.getAddress());
//          
//                usersList.add(users);
//                System.out.println("all users data "+usersList);
//            }
//            else {
//            	System.out.println("not went inside if loop");
//            }
//
//        }
        
//        return new ResponseEntity<List<User>>(usersList,HttpStatus.OK);

//    }
	
	@GetMapping(path ="/getAllCustomers",produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('Shopper')")
	public ResponseEntity<List<User>> getAllCustomers(@RequestHeader(name = "Authorization") String tokenDup){
		List<User> customers = userService.getAllCustomers();
		System.out.println(customers);
		return new ResponseEntity<>(customers,HttpStatus.OK);
	}//not working


	@GetMapping(path = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> getUserDetails(@RequestHeader(name = "Authorization") String tokenDup) {

		String token = tokenDup.substring(7);

		String username = jwtUtil.extractUsername(token);

		User users = userRepo.findByUserEmail(username);

		return new ResponseEntity<>(new UserDto(users.getUserEmail(), users.getUserFirstName(), users.getUserLastName(), users.getUserName(), users.getUserPassword(), null, null),HttpStatus.OK);

	}
	
	@PutMapping("/address/{userName}")
	public ResponseEntity<?> updateAddress(@RequestHeader(name = "Authorization") String tokenDup, @PathVariable String userName, @RequestBody Address updatedAddress) {
		try {
			userService.updateUserAddress(userName,updatedAddress);
			return ResponseEntity.ok("Address updated Successfully");
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update Address: "+ e.getMessage());
		}
	}

}
