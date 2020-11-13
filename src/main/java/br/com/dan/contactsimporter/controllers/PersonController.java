package br.com.dan.contactsimporter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dan.contactsimporter.dtos.ResponseData;
import br.com.dan.contactsimporter.exceptions.DocumentNotFound;
import br.com.dan.contactsimporter.models.Person;
import br.com.dan.contactsimporter.services.PersonService;

@RestController
@RequestMapping("api/v1/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(name = "getPerson", path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseData> get(@PathVariable(name = "id", value = "id") String id) {
        try {
            Person person = personService.find(id);
            ResponseData responseData = ResponseData.builder().data(person).status(HttpStatus.OK.value()).build();
            return ResponseEntity.ok(responseData);
        } catch (DocumentNotFound e) {
            ResponseData responseData = ResponseData.builder().error(e.getMessage())
                    .status(HttpStatus.NOT_FOUND.value()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
    }

    @GetMapping(name = "getPersons", path = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResponseData> getAll(Pageable pageRequest) {
        Iterable<Person> person = personService.findAll(pageRequest);
        ResponseData responseData = ResponseData.builder().data(person).status(HttpStatus.OK.value()).build();
        return ResponseEntity.ok(responseData);
    }

}
