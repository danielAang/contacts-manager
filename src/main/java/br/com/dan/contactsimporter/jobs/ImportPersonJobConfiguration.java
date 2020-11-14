package br.com.dan.contactsimporter.jobs;

import java.net.MalformedURLException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import br.com.dan.contactsimporter.jobs.listener.ItemProcessLoggerListener;
import br.com.dan.contactsimporter.jobs.listener.ItemReadLoggerListener;
import br.com.dan.contactsimporter.jobs.listener.ItemWriteLoggerListener;
import br.com.dan.contactsimporter.jobs.listener.JobCompletionNotificationListener;
import br.com.dan.contactsimporter.jobs.mapper.FileLine;
import br.com.dan.contactsimporter.jobs.mapper.PersonMapper;
import br.com.dan.contactsimporter.jobs.processor.PersonItemProcessor;
import br.com.dan.contactsimporter.models.Person;
import br.com.dan.contactsimporter.repositories.PersonRepository;

@Configuration
public class ImportPersonJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    @StepScope
    public FlatFileItemReader<FileLine> reader(@Value("#{jobParameters[fileName]}") String fileName)
            throws MalformedURLException {
        FlatFileItemReader<FileLine> reader = new FlatFileItemReaderBuilder<FileLine>()
            .name("personItemReader")
            .linesToSkip(1)
            .delimited()
            .delimiter(",")
            .names(new String[] { "nome", "idade", "cpf", "rg", "data_nasc", "sexo", "email", "cep", "endereco", "numero", "bairro", "cidade", "estado", "telefone_fixo", "celular" })
            .fieldSetMapper(new PersonMapper())
            .resource(new FileSystemResource(fileName))
            .build();
        return reader;
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public RepositoryItemWriter<Person> writer() {
        return new RepositoryItemWriterBuilder<Person>()
            .repository(personRepository)
            .methodName("save")
            .build();
    }

    @Bean
    public Job importPersonJob(Step step1) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(jobCompletionListener())
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1(ItemReader<FileLine> reader) {
        return stepBuilderFactory.get("step1")
            .<FileLine, Person>chunk(10)
            .reader(reader)
            .processor(processor())
            .writer(writer())
            .listener(new ItemReadLoggerListener())
            .listener(new ItemWriteLoggerListener())
            .listener(new ItemProcessLoggerListener())
            .transactionManager(platformTransactionManager)
            .build();
    }

    @Bean
    @JobScope
    public JobExecutionListener jobCompletionListener() {
        return new JobCompletionNotificationListener();
    }

}
