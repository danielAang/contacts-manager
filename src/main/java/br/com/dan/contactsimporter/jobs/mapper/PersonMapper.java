package br.com.dan.contactsimporter.jobs.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PersonMapper implements FieldSetMapper<FileLine> {

    @Override
    public FileLine mapFieldSet(FieldSet fieldSet) throws BindException {
        return FileLine.builder()
            .name(fieldSet.readString("name"))
            .birthDate(fieldSet.readString("birth_date"))
            .email(fieldSet.readString("email"))
            .phoneNumber(fieldSet.readString("phone_number"))
            .gender(fieldSet.readString("gender"))
            .state(fieldSet.readString("state"))
            .city(fieldSet.readString("city"))
            .street(fieldSet.readString("street"))
            .zipCode(fieldSet.readString("zip_code"))
            .number(fieldSet.readString("number"))
            .build();
    }
    
}
