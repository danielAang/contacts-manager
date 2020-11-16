package br.com.dan.contactsimporter.jobs.imp.person.steps;

import java.util.HashMap;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.PlatformTransactionManager;

import br.com.dan.contactsimporter.jobs.imp.person.processor.UpdateCityAddressProcessor;
import br.com.dan.contactsimporter.jobs.imp.person.skippolicy.UpdateAddressSkipPolicy;
import br.com.dan.contactsimporter.models.Address;
import br.com.dan.contactsimporter.repositories.AddressRepository;

@Configuration
public class UpdateAddressData {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private AddressRepository addressRepository;

    @Bean("updateAddressDataStep")
    public Step step2() {
        return stepBuilderFactory.get("updatePersonAddress")
            .<Address, Address> chunk(10)
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .faultTolerant()
            .skipPolicy(new UpdateAddressSkipPolicy())
            .transactionManager(platformTransactionManager)
            .build();
    }

    public RepositoryItemReader<Address> reader() {
        HashMap<String, Direction> sorts = new HashMap<>();
        return new RepositoryItemReaderBuilder<Address>()
            .name("name")
            .repository(addressRepository)
            .methodName("findByCityIsNull")
            .pageSize(10)
            .sorts(sorts)
            .build();
    }

    public UpdateCityAddressProcessor processor() {
        return new UpdateCityAddressProcessor();
    }

    public RepositoryItemWriter<Address> writer() {
        return new RepositoryItemWriterBuilder<Address>()
            .repository(addressRepository)
            .methodName("save")
            .build();
    }
    
}
