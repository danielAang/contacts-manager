package br.com.dan.contactsimporter.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dan.contactsimporter.exceptions.DocumentNotFound;
import br.com.dan.contactsimporter.models.Address;
import br.com.dan.contactsimporter.repositories.AddressRepository;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;

    public Address insert(Address address) {
        return addressRepository.save(address);
    }

    public Address update(Address address) {
        return addressRepository.save(address);
    }

    public void delete(String id) {
        addressRepository.deleteById(id);
    }

    public Address find(String id) throws DocumentNotFound {
        Optional<Address> optAddress = addressRepository.findById(id);
        return optAddress.orElseThrow(() -> new DocumentNotFound("Address not found"));
    }

    public Iterable<Address> findAll() {
        return addressRepository.findAll();
    }

}
