package br.com.dan.contactsimporter.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DocumentNotFound extends Exception {
    
    private static final long serialVersionUID = 1L;
    private String message;
    private Throwable ex;
    
    public DocumentNotFound(String message) {
        super(message);
        this.message = message;
    }

    public DocumentNotFound(Throwable ex) {
        super(ex);
        this.ex = ex;
    }

    public DocumentNotFound(String message, Throwable ex) {
        super(message, ex);
        this.message = message;
        this.ex = ex;
    }

}
