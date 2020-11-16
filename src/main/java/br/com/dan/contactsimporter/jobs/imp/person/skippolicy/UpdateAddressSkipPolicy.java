package br.com.dan.contactsimporter.jobs.imp.person.skippolicy;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

import br.com.dan.contactsimporter.jobs.imp.person.exceptions.InvalidZipCodeException;

public class UpdateAddressSkipPolicy implements SkipPolicy {

    @Override
    public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {
        if (t instanceof InvalidZipCodeException) {
            return true;
        }
        return false;
    }
    
}
