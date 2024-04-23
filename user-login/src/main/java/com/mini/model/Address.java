package com.mini.model;

import javax.persistence.*;
import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_address")
public class Address {

    @Id
    @Column(name = "add_id")
    private String addressId;
    
    private String addressLine;
    
    private String street;
    
    private String city;
    
    private String state;
    
    private int pincode;

}
