package br.com.dan.contactsimporter.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.dan.contactsimporter.models.Address;

public interface AddressRepository extends PagingAndSortingRepository<Address, String> {

    Page<Address> findByStateIsNull(Pageable pageable);

    Page<Address> findByCityIsNull(Pageable pageable);
    
}
