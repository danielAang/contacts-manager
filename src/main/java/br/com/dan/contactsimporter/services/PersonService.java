package br.com.dan.contactsimporter.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.dan.contactsimporter.exceptions.DocumentNotFound;
import br.com.dan.contactsimporter.models.Person;
import br.com.dan.contactsimporter.repositories.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person insert(Person person) {
        return personRepository.save(person);
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }

    public void delete(String id) {
        personRepository.deleteById(id);
    }

    public Person find(String id) throws DocumentNotFound {
        Optional<Person> optPerson = personRepository.findById(id);
        return optPerson.orElseThrow(() -> new DocumentNotFound("Person not found"));
    }

    public Iterable<Person> findAll(Pageable pageRequest) {
        return personRepository.findAll(pageRequest);
    }
    
}
