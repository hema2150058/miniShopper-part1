package com.mini.shopper.service;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import com.mini.shopper.dto.CartReqDto;
import com.mini.shopper.dto.PlaceOrderReq;
import com.mini.shopper.model.Cart;
import com.mini.shopper.repo.CartRepo;
import com.mini.shopper.repo.OrderRepo;

@Service
public class FileService {

	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired 
	CartService cartService;
	
	@Autowired
	OrderService orderService;
	
	 public void uploadToOrder(MultipartFile file) throws IOException {
	        try (InputStream inputStream = file.getInputStream()) {
	            Workbook workbook = new XSSFWorkbook(inputStream);
	            processCartReqSheet(workbook.getSheetAt(0)); // Process sheet 1 (CartReqDto)
	            processPlaceOrderSheet(workbook.getSheetAt(1)); // Process sheet 2 (PlaceOrderReq)
	            workbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw e; // Rethrow the exception to be handled by the controller
	        }
	    }
	  private void processCartReqSheet(Sheet sheet) {
	        List<CartReqDto> cartReqList = new ArrayList<>();
	        for (Row row : sheet) {
	            String userId = row.getCell(0).getStringCellValue();
	            int productId = (int) row.getCell(1).getNumericCellValue();
	            int quantity = (int) row.getCell(2).getNumericCellValue();
	            CartReqDto cartReqDto = new CartReqDto(userId, productId, quantity);
	            cartReqList.add(cartReqDto);
	            cartService.addProductsToCart(cartReqDto);
	        }
	        // Call addToCart service method with the list of CartReqDto objects
	    }
	
	  private void processPlaceOrderSheet(Sheet sheet) {
	        Row row = sheet.getRow(0); // Assuming there is only one row in the sheet
	        String userId = row.getCell(0).getStringCellValue();
	        String billingName = row.getCell(1).getStringCellValue();
	        String billingPno = String.valueOf((int)row.getCell(2).getNumericCellValue());
	        String billingAdress = row.getCell(3).getStringCellValue();
	        PlaceOrderReq placeOrderReq = new PlaceOrderReq(userId, billingName, billingPno, billingAdress);
	        orderService.placeOrder(placeOrderReq);
	        // Call placeOrder service method with the PlaceOrderReq object
	    }
	 
	
	  
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public void uploadexcel(MultipartFile files) throws EncryptedDocumentException, IOException{
//		
//		List<List<String>> rows = new ArrayList<>();
//		
//		Workbook workbook = WorkbookFactory.create(files.getInputStream());
//		Sheet sheet = workbook.getSheetAt(0);
//		
//		rows = StreamSupport.stream(sheet.spliterator(), false)
//				.map(row -> StreamSupport
//				.stream(row.spliterator(), false)
//				.map(this::getCellStringValue)
//				.collect(Collectors.toList()))
//				.collect(Collectors.toList());
//		System.out.println("rows : "+ rows);
//		
//		List<Cart> cartRedDto = rows.stream().map(row -> {
//			CartReqDto excelcartReq =null;
//			excelcartReq.setUserId(row.get(0));
//			excelcartReq.setProductId( Integer.parseInt(row.get(1)));
//			excelcartReq.setQuantity(Integer.parseInt(row.get(2)));
//			cartService.addProductsToCart(excelcartReq);
//			
//			
//		}).collect(Collectors.toList());
//		
//		cartRepo.saveAll( cartRedDto);
//		
//		
//	}
//	
//	private String getCellStringValue(Cell cell) {
//		CellType cellType = cell.getCellType();
//
//		if (cellType == CellType.STRING) {
//			return cell.getStringCellValue();
//		} else if (cellType == CellType.NUMERIC) {
//			return String.valueOf(cell.getNumericCellValue());
//		} else if (cellType == CellType.BOOLEAN) {
//			return String.valueOf(cell.getBooleanCellValue());
//		}
//
//		return null;
//	}
//	
//	private void readExcelFile(MultipartFile file) throws EncryptedDocumentException, IOException {
//	    Workbook workbook = WorkbookFactory.create(file.getInputStream());
//	    Sheet sheet = workbook.getSheetAt(0);
//	    DataFormatter dataFormatter = new DataFormatter();
//	    sheet.forEach(row -> {
//	        row.forEach(cell -> {
//	            String cellValue = dataFormatter.formatCellValue(cell);
//	            System.out.print(cellValue + "\t");
//	        });
//	        System.out.println();
//	    });
//	}
	
}	