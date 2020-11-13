package br.com.dan.contactsimporter;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class ContactsImporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactsImporterApplication.class, args);
	}

}
