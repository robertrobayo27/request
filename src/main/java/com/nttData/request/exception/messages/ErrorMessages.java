package com.nttData.request.exception.messages;

public enum ErrorMessages {
	INVALID_DOCUMENT_TYPE("Tipo de Documento Invalido. Usa 'C' para Cédula o 'P' para Pasaporte."),  
    CUSTOMER_NOT_FOUND("Cliente con tipo de documento '%s' y número '%s' no está registrado");  

    private final String message;  

    ErrorMessages(String message) {  
        this.message = message;  
    }  

    public String getMessage() {  
        return message;  
    }
}
