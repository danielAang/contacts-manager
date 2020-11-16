package br.com.dan.contactsimporter.jobs.imp.person.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InvalidZipCodeException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;

    public InvalidZipCodeException(String message) {
        super(message);
        this.message = message;
    }
    
}
