package br.com.dan.contactsimporter.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.dan.contactsimporter.models.Address;

public interface AddressRepository extends PagingAndSortingRepository<Address, String> {
    
}
