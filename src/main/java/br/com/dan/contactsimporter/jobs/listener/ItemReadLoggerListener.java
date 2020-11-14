package br.com.dan.contactsimporter.jobs.listener;

import org.springframework.batch.core.ItemReadListener;

import br.com.dan.contactsimporter.jobs.mapper.FileLine;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemReadLoggerListener implements ItemReadListener<FileLine> {

    @Override
    public void beforeRead() {
        
    }

    @Override
    public void afterRead(FileLine item) {
        
    }

    @Override
    public void onReadError(Exception ex) {
        log.error("Error when reading file line {}", ex);
    }

}