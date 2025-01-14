package com.nttData.request.controllers.impl;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttData.request.controllers.CustomerController;
import com.nttData.request.models.Customer;
import com.nttData.request.services.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerControllerImpl implements CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerControllerImpl(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/get-by-document")
	public Customer getByDocument(@RequestParam String documentType, @RequestParam String documentNumber) throws BadRequestException, NotFoundException {
		return customerService.getByDocument(documentType, documentNumber);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExceptions(Exception e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("An unexpected error occurred: " + e.getMessage());
	}
}
