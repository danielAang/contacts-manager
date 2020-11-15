package br.com.dan.contactsimporter.jobs.imp.person.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import br.com.dan.contactsimporter.jobs.imp.person.mapper.FileLine;
import br.com.dan.contactsimporter.models.Person;
import br.com.dan.contactsimporter.repositories.PersonRepository;

@Configuration
public class UpdateAddressData {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private PersonRepository personRepository;

    @Bean("getAddressData")
    public Step step2(ItemReader<FileLine> reader) {
        return stepBuilderFactory.get("step1")
            .<FileLine, Person>chunk(10)
            .reader(reader)
            .processor(processor())
            .writer(writer())
            .transactionManager(platformTransactionManager)
            .build();
    }
    
}
