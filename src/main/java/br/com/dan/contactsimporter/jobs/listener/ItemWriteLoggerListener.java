package br.com.dan.contactsimporter.jobs.listener;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;

import br.com.dan.contactsimporter.models.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemWriteLoggerListener implements ItemWriteListener<Person> {

    @Override
    public void beforeWrite(List<? extends Person> items) {
        
    }

    @Override
    public void afterWrite(List<? extends Person> items) {
        
    }

    @Override
    public void onWriteError(Exception ex, List<? extends Person> items) {
        log.error("Error when writing person {}", ex.getMessage());
    }
    
}
