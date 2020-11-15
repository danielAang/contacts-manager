package br.com.dan.contactsimporter.jobs.imp.person;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import br.com.dan.contactsimporter.jobs.imp.person.listener.JobCompletionNotificationListener;

@Configuration
public class ImportPersonJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job importPersonJob(@Qualifier("transformFile") Step step1) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(jobCompletionListener())
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    @JobScope
    public JobExecutionListener jobCompletionListener() {
        return new JobCompletionNotificationListener();
    }

}
