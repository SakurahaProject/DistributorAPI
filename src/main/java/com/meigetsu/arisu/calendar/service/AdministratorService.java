package com.meigetsu.arisu.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meigetsu.arisu.calendar.component.CheckDigit;
import com.meigetsu.arisu.calendar.model.Administrator;
import com.meigetsu.arisu.calendar.model.CreateAdministrator;
import com.meigetsu.arisu.calendar.repository.AdministratorRepository;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    public String append(CreateAdministrator record) {
        long currentRecordCount = administratorRepository.count();
        String id = String.format("%5d", currentRecordCount + 1);
        id = String.valueOf(CheckDigit.calcModulus10Weight21(id)) + id;
        Administrator newAdmin = new Administrator(id, record.mailAddress(), record.password());
        administratorRepository.save(newAdmin);
        return id;
    }
}
