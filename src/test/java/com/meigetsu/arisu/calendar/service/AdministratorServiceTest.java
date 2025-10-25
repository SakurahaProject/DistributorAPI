package com.meigetsu.arisu.calendar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.meigetsu.arisu.calendar.model.CreateAdministrator;

@SpringBootTest
public class AdministratorServiceTest {
    @ExtendWith(SpringExtension.class)
    @Test
    public void testAdminAuthorizationServices() {
        AdministratorService administratorService = new AdministratorService();
        AccessKeyService accessKeyService = new AccessKeyService();

        CreateAdministrator createAdmin = new CreateAdministrator("testAdmin@example.com", "password");
        String id = administratorService.append(createAdmin);
        assert id.matches("^a[0-9]{6}$") : "Generated ID does not match expected format";
        String accessKey = accessKeyService.issue(id, "password");
        assert accessKey != null : "Failed to issue access key";
        assert accessKeyService.validate(accessKey) != null : "Failed to validate access key";
        accessKeyService.revoke(accessKey);
        assert accessKeyService.validate(accessKey) == null : "Access key should be revoked";
        administratorService.delete(id);
        assert accessKeyService.issue(id, "password") == null : "Administrator should be deleted";
    }
}
