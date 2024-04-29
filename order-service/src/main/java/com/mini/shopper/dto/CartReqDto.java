package com.mini.shopper.dto;

import org.springframework.stereotype.Component;
import lombok.*;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartReqDto {

//	/add , update 
	private int userId;
	
	private int productId;
	
	private int quantity;
	
	private int price;
}
