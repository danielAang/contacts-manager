package br.com.dan.contactsimporter.jobs.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileLine {

    private String name;
    private String birthDate;
    private String email;
    private String phoneNumber;
    private String gender;
    private String state;
    private String city;
    private String street;
    private String zipCode;
    private String number;
    
}
