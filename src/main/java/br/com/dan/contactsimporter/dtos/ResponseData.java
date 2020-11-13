package br.com.dan.contactsimporter.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private LocalDateTime date;
    private Object data;
    private String message;
    private String error;
    private int status;

    public ResponseData(Object data, String message, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.status = status.value();
        date = LocalDateTime.now();
    }

    public ResponseData(String error, String message, HttpStatus status) {
        this.error = error;
        this.message = message;
        this.status = status.value();
        date = LocalDateTime.now();
    }

}
