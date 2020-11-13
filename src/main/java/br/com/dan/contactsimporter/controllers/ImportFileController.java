package br.com.dan.contactsimporter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.dan.contactsimporter.dtos.ResponseData;
import br.com.dan.contactsimporter.services.ImportFileService;

@RestController
@RequestMapping("api/v1/imported-files")
public class ImportFileController {

    @Autowired
    private ImportFileService importFileService;

    @PostMapping("/import")
    public ResponseEntity<ResponseData> post(@RequestParam("file") MultipartFile file) {
        importFileService.processFile(file);
        return ResponseEntity.ok().build();
    }

}
