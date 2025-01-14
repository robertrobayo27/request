package com.nttData.request.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttData.request.exception.CustomerNotFoundException;
import com.nttData.request.models.Customer;
import com.nttData.request.services.CustomerService;

import com.nttData.request.exception.messages.ErrorMessages; 

import jakarta.annotation.PostConstruct;

@Service
public class CustomerServiceImpl implements CustomerService {

	private Map<String, Customer> customers = new HashMap<>();  

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
    // Constructor para inyectar la base de datos o inicializarla  
    public CustomerServiceImpl(Map<String, Customer> database) { 
    	if (database != null) {  
            this.customers = database; 
        }  
    } 
    
    @PostConstruct
    public void loadDataFromJson() {
    
            ObjectMapper objectMapper = new ObjectMapper();  

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("customers.json");  
            
            logger.info("Attempting to load JSON file from classpath..."); 
            logger.info("InputStream: {}", inputStream);
            
            if (inputStream == null) {  
                throw new RuntimeException("JSON File not found");  
            }  
  
            try {
            	List<Customer> customerList = objectMapper.readValue(inputStream, new TypeReference<List<Customer>>() {});  
                for (Customer customer : customerList) {  
                    customers.put(customer.getDocumentNumber(), customer);
                }  

            } catch (IOException e) {
                throw new RuntimeException("Error loading data from JSON file", e);
            }
  
    }
    
	@Override
	public Customer getByDocument(String documentType, String documentNumber) throws BadRequestException, NotFoundException {
		if (!documentType.equals("C") && !documentType.equals("P")) {  
            throw new BadRequestException(ErrorMessages.INVALID_DOCUMENT_TYPE.getMessage());  
        }  

		 Customer customer = customers.get(documentNumber); 
		 
				 if (customer == null || !customer.getDocumentType().equals(documentType)) {  
			            throw new CustomerNotFoundException(String.format(ErrorMessages.CUSTOMER_NOT_FOUND.getMessage(), documentType, documentNumber));  
			        }  
			        return customer; 
	    
	}

}
