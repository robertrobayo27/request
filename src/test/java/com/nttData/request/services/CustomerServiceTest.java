package com.nttData.request.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.nttData.request.exception.CustomerNotFoundException;
import com.nttData.request.models.Customer;
import com.nttData.request.services.impl.CustomerServiceImpl;

import com.nttData.request.exception.messages.ErrorMessages; 

public class CustomerServiceTest {

	private CustomerServiceImpl customerService;
	private Map<String, Customer> database;  
	
	String docType = "";
	String docNumber = "";
	
	@BeforeEach  
    void setUp() {  
		database = new HashMap<>();  
       
        Customer customer1 = new Customer();
        customer1.setDocType("C");
        customer1.setDocumentNumber("12345");
        
        Customer customer2 = new Customer();
        customer2.setDocType("P");
        customer2.setDocumentNumber("67890");
        
        database.put("12345", customer1);  
        database.put("67890", customer2);  

        customerService = new CustomerServiceImpl(database);  
    }  
	
	@Test
	void getByDocumentExistTest() throws BadRequestException, NotFoundException {
		
		docType = "C";
		docNumber = "12345";
		
		Customer customerMock = new Customer();
		System.out.println("database "+ database);
		customerMock = customerService.getByDocument(docType, docNumber);
		
		assertNotNull(customerMock);  
        assertEquals("C", customerMock.getDocumentType());  
        assertEquals("12345", customerMock.getDocumentNumber());  
		
	}
	
	@Test
	void getByDocumentNotExistTest() throws BadRequestException, NotFoundException {
		 docType = "C";
		 docNumber = "99999";
		
		CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {  
            customerService.getByDocument(docType, docNumber);  
        });  
        assertEquals(String.format(ErrorMessages.CUSTOMER_NOT_FOUND.getMessage(), docType, docNumber), exception.getMessage());  
		
	}
	
	@Test  
    void getByDocumentInvalidDocumentTypeTest() {  
		
		 docType = "X";
		 docNumber = "99999";
		
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {  
            customerService.getByDocument(docType, docNumber);  
        });  
        assertEquals(ErrorMessages.INVALID_DOCUMENT_TYPE.getMessage(), exception.getMessage());  
    }  
	
	
}
