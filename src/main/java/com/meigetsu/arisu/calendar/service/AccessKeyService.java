package com.meigetsu.arisu.calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meigetsu.arisu.calendar.component.AccessKeyGenerator;
import com.meigetsu.arisu.calendar.component.HashUtils;
import com.meigetsu.arisu.calendar.model.AccessKey;
import com.meigetsu.arisu.calendar.model.Administrator;
import com.meigetsu.arisu.calendar.repository.AccessKeyRepository;
import com.meigetsu.arisu.calendar.repository.AdministratorRepository;

@Service
public class AccessKeyService {
    @Autowired
    private AccessKeyRepository accessKeyRepository;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private AccessKeyGenerator accessKeyGenerator;

    public String issue(String id, String password) {
        Administrator admin = administratorRepository.findById(id).orElse(null);
        if (admin == null || !admin.comparePassword(password)) {
            return null;
        }
        String accessKey = accessKeyGenerator.generate();
        accessKeyRepository.save(new AccessKey(HashUtils.AccessKey(accessKey), admin));
        return accessKey;
    }

    public Administrator validate(String accessKey) {
        String[] parts = accessKey.split(" ");
        if (parts.length != 2 || !parts[0].equals("Bearer")) {
            return null;
        }
        AccessKey key = accessKeyRepository.findById(HashUtils.AccessKey(parts[1])).orElse(null);
        return key == null ? null : key.getAdministrator();
    }

    public void revoke(String accessKey) {
        accessKeyRepository.deleteById(HashUtils.AccessKey(accessKey));
    }
}
