package br.com.dan.contactsimporter.services;

import java.io.File;
import java.util.Optional;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.dan.contactsimporter.exceptions.DocumentNotFound;
import br.com.dan.contactsimporter.models.ImportFile;
import br.com.dan.contactsimporter.repositories.ImportFileRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImportFileService {

    @Autowired
    private ImportFileRepository importFileRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importPersonJob;
    
    public void processFile(MultipartFile file) {
        try {
            String path = saveFileToResourceFolder(file);
            JobParameters jobParameters = new JobParametersBuilder().addString("fileName", path).toJobParameters();
            jobLauncher.run(importPersonJob, jobParameters);
        } catch (Exception e) {
            log.error("Failed to start job importPersonJob", e);
        }
    }

    private String saveFileToResourceFolder(MultipartFile file) {
        try {
            File tmpFile = File.createTempFile("abc", null);
            file.transferTo(tmpFile);
            return tmpFile.getAbsolutePath();
        } catch (Exception e) {
            log.error("Failed to save file at resource folder", e);
        }
        return null;
    }

    public ImportFile insert(ImportFile importFile) {
        return importFileRepository.save(importFile);
    }

    public ImportFile update(ImportFile importFile) {
        return importFileRepository.save(importFile);
    }

    public void delete(Long id) {
        importFileRepository.deleteById(id);
    }

    public ImportFile find(Long id) throws DocumentNotFound {
        Optional<ImportFile> optImportFile = importFileRepository.findById(id);
        return optImportFile.orElseThrow(() -> new DocumentNotFound("ImportFile not found"));
    }

    public Iterable<ImportFile> findAll() {
        return importFileRepository.findAll();
    }
    
}
