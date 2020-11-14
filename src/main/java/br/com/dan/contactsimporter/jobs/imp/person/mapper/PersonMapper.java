package br.com.dan.contactsimporter.jobs.imp.person.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PersonMapper implements FieldSetMapper<FileLine> {

    @Override
    public FileLine mapFieldSet(FieldSet fieldSet) throws BindException {
        return FileLine.builder()
            .name(fieldSet.readString("nome"))
            .age(fieldSet.readString("idade"))
            .socialId(fieldSet.readString("rg"))
            .cpf(fieldSet.readString("cpf"))
            .birthDate(fieldSet.readString("data_nasc"))
            .email(fieldSet.readString("email"))
            .phoneNumber(fieldSet.readString("telefone_fixo"))
            .gender(fieldSet.readString("sexo"))
            .state(fieldSet.readString("estado"))
            .city(fieldSet.readString("cidade"))
            .street(fieldSet.readString("endereco"))
            .district(fieldSet.readString("bairro"))
            .zipCode(fieldSet.readString("cep"))
            .number(fieldSet.readString("numero"))
            .build();
    }
    
}
