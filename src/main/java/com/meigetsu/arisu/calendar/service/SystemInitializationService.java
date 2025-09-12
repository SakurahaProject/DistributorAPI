package com.meigetsu.arisu.calendar.service;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meigetsu.arisu.calendar.component.File;
import com.meigetsu.arisu.calendar.model.CreateAdministrator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SystemInitializationService implements ApplicationRunner {
    @Autowired
    private AdministratorService administratorService;
    private static final String adminAccountFilePath = "./account.json";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (Files.exists(Paths.get(adminAccountFilePath))) {
            String fileContent = File.readAllText(adminAccountFilePath);
            ObjectMapper mapper = new ObjectMapper();
            CreateAdministrator initialAdmin = mapper.readValue(fileContent, CreateAdministrator.class);
            String id = administratorService.append(initialAdmin);
            Files.delete(Paths.get(adminAccountFilePath));
            Files.write(Paths.get("./" + UUID.randomUUID() + ".txt"), id.getBytes(Charset.forName("UTF-8")));
        }
    }
}
