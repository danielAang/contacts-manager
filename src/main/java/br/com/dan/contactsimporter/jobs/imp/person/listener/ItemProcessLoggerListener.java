package br.com.dan.contactsimporter.jobs.imp.person.listener;

import org.springframework.batch.core.ItemProcessListener;

import br.com.dan.contactsimporter.jobs.imp.person.mapper.FileLine;
import br.com.dan.contactsimporter.models.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemProcessLoggerListener implements ItemProcessListener<FileLine, Person> {

    @Override
    public void beforeProcess(FileLine item) {
        
    }

    @Override
    public void afterProcess(FileLine item, Person result) {
        
    }

    @Override
    public void onProcessError(FileLine item, Exception e) {
        log.error("Error when processing item {}: {}", item.getName(), e.getLocalizedMessage());
    }

}
