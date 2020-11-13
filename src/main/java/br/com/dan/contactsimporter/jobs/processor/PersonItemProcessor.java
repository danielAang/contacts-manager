package br.com.dan.contactsimporter.jobs.processor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.ItemProcessor;

import br.com.dan.contactsimporter.jobs.mapper.FileLine;
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
            .number(transformToNumber(item.getNumber()))
            .zipCode(item.getZipCode())
            .build();
        return Person.builder()
            .name(item.getName())
            .birthDate(transformToLocalDate(item.getBirthDate()))
            .email(item.getEmail().toLowerCase())
            .phoneNumber(item.getPhoneNumber())
            .gender(item.getGender())
            .address(address)
            .build();
    }

    private Long transformToNumber(String number) {
        try {
            return Long.valueOf(number);
        } catch (Exception e) {
            log.error("Failed to transform number {}", number);
            return Long.valueOf("0");
        }
    }

    private LocalDate transformToLocalDate(String value) {
        try {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            log.error("Failed to convert date {}", value);
            return null;
        }
    }
    
}