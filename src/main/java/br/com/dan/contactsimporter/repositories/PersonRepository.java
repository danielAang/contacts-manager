package br.com.dan.contactsimporter.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.dan.contactsimporter.models.Person;

public interface PersonRepository extends PagingAndSortingRepository<Person, String> {
    
}
