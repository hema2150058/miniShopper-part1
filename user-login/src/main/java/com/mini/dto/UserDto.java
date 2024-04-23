package com.mini.dto;

import java.sql.Timestamp;
import java.util.Set;

import com.mini.model.Address;
import com.mini.model.Role;

import lombok.Data;

@Data
public class UserDto {

	private String userName;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
	private Timestamp createdDate;
    

}
