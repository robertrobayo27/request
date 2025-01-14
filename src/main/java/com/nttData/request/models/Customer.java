package com.nttData.request.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class Customer {

	private String documentType;
	private String documentNumber;
	private String firstName;
	private String secondName;
	private String firstLastName;
	private String secondLastName;
	private String phone;
	private String address;
	private String city;

	public Customer(String documentType, String documentNumber, String firstName, String secondName,
			String firstLastName, String secondLastName, String phone, String address, String city) {
		super();
		this.documentType = documentType;
		this.documentNumber = documentNumber;
		this.firstName = firstName;
		this.secondName = secondName;
		this.firstLastName = firstLastName;
		this.secondLastName = secondLastName;
		this.phone = phone;
		this.address = address;
		this.city = city;
	}

	public Customer() {
	
		this.documentType = "";
		this.documentNumber = "";
		this.firstName = "";
		this.secondName = "";
		this.firstLastName = "";
		this.secondLastName = "";
		this.phone = "";
		this.address = "";
		this.city = "";
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getFirstLastName() {
		return firstLastName;
	}

	public void setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
	}

	public String getSecondLastName() {
		return secondLastName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
