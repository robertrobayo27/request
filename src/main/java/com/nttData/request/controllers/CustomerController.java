package com.nttData.request.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.nttData.request.models.Customer;

public interface CustomerController {

	Customer getByDocument(String documentType, String documentNUmber) throws BadRequestException, NotFoundException;
}
