package br.com.dan.contactsimporter.jobs.imp.person;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.dan.contactsimporter.jobs.imp.person.listener.JobCompletionNotificationListener;

@Configuration
public class ImportPersonJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job importPersonJob(@Qualifier("loadFileDataStep") Step step1, @Qualifier("updateAddressDataStep") Step step2) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(jobCompletionListener())
            .start(step1)
            .next(step2)
            .build();
    }

    @Bean
    @JobScope
    public JobExecutionListener jobCompletionListener() {
        return new JobCompletionNotificationListener();
    }

}
