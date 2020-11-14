package br.com.dan.contactsimporter.jobs.imp.person.processor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.ItemProcessor;

import br.com.dan.contactsimporter.jobs.imp.person.mapper.FileLine;
import br.com.dan.contactsimporter.models.Address;
import br.com.dan.contactsimporter.models.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonItemProcessor implements ItemProcessor<FileLine, Person> {
    
    @Override
    public Person process(FileLine item) throws Exception {
        Address address = Address.builder()
            .street(item.getStreet())
            .city(null)
            .state(null)
            .number(transformToLong(item.getNumber()))
            .street(item.getStreet())
            .district(item.getDistrict())
            .zipCode(item.getZipCode())
            .build();
        Person person = Person.builder()
            .name(item.getName())
            .age(transformToInteger(item.getAge()))
            .socialId(item.getSocialId())
            .cpf(item.getCpf())
            .birthDate(transformToLocalDate(item.getBirthDate()))
            .email(item.getEmail().toLowerCase())
            .phoneNumber(item.getPhoneNumber())
            .gender(item.getGender())
            .address(address)
            .build();
        address.setPerson(person);
        return person;
    }

    private Long transformToLong(String number) {
        try {
            return Long.valueOf(number);
        } catch (Exception e) {
            log.error("Failed to transform number {}", number);
            return Long.valueOf("0");
        }
    }

    private Integer transformToInteger(String number) {
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            log.error("Failed to transform number {}", number);
            return 0;
        }
    }

    private LocalDate transformToLocalDate(String value) {
        try {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            log.error("Failed to convert date {}", value);
            return null;
        }
    }
    
}