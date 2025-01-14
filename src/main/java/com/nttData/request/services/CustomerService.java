package com.nttData.request.services;

import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.nttData.request.models.Customer;

public interface CustomerService {

	 Customer getByDocument(String documentType, String documentNUmber) throws BadRequestException, NotFoundException;
		
}
