package br.com.dan.contactsimporter.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.dan.contactsimporter.models.ImportFile;

public interface ImportFileRepository extends PagingAndSortingRepository<ImportFile, Long> {
    
}
