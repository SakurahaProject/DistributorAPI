package com.meigetsu.arisu.calendar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.meigetsu.arisu.calendar.component.Log;
import com.meigetsu.arisu.calendar.model.Administrator;
import com.meigetsu.arisu.calendar.model.CreateAdministrator;
import com.meigetsu.arisu.calendar.model.Profile;
import com.meigetsu.arisu.calendar.model.Schedule;
import com.meigetsu.arisu.calendar.model.SiteLink;
import com.meigetsu.arisu.calendar.service.AccessKeyService;
import com.meigetsu.arisu.calendar.service.AdministratorService;
import com.meigetsu.arisu.calendar.service.ProfileService;
import com.meigetsu.arisu.calendar.service.ScheduleService;
import com.meigetsu.arisu.calendar.service.SiteLinkService;

@Controller
public class DistributorActionController {
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private AccessKeyService accessKeyService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private SiteLinkService siteLinkService;

    @PostMapping("/distributor/administrator")
    public ResponseEntity<?> addAdministrator(@RequestHeader("Authorization") String currentAdminAccessKey,
            @RequestBody CreateAdministrator newAdmin) {
        Administrator admin = null;
        try {
            admin = accessKeyService.validate(currentAdminAccessKey);
            if (admin == null) {
                Log.error("Unknown", "/distributor/administrator", "Invalid access key:" + currentAdminAccessKey);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            String newAdminID = administratorService.append(newAdmin);
            return ResponseEntity.status(HttpStatus.CREATED).body(newAdminID);
        } catch (Exception e) {
            Log.error(admin == null ? "Unknown" : admin.getId(), "/distributor/administrator",
                    "Failed to add administrator: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            Log.access(admin == null ? "Unknown" : admin.getId(), "/distributor/administrator",
                    "Attempted to add administrator");
        }
    }

    @PostMapping("/distributor/administrator/authorization")
    public ResponseEntity<?> issueAdministratorAccessKey(
            @RequestHeader("Authorization") String basicAuthorizationHeader) {
        try {
            String[] parts = basicAuthorizationHeader.split(" ");
            if (parts.length != 2 || !parts[0].equals("Basic")) {
                Log.error("Authorization Manager", "/distributor/administrator/authorization",
                        "Invalid authorization header: " + basicAuthorizationHeader);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            String[] credentials = new String(java.util.Base64.getDecoder().decode(parts[1])).split(":", 2);
            if (credentials.length != 2) {
                Log.error("Authorization Manager", "/distributor/administrator/authorization",
                        "Invalid authorization header: " + basicAuthorizationHeader);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            String accessKey = accessKeyService.issue(credentials[0], credentials[1]);
            if (accessKey == null) {
                Log.error(credentials[0], "/distributor/administrator/authorization", "Invalid ID or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            return ResponseEntity.ok(accessKey);
        } catch (Exception e) {
            Log.error("Authorization Manager", "/distributor/administrator/authorization",
                    "Failed to issue access key: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            Log.access("Authorization Manager", "/distributor/administrator/authorization",
                    "Attempted to issue access key");
        }
    }

    @DeleteMapping("/distributor/administrator/authorization")
    public ResponseEntity<?> revokeAdministratorAccessKey(
            @RequestHeader("Authorization") String accessKey) {
        Administrator admin = null;
        try {
            admin = accessKeyService.validate(accessKey);
            if (admin == null) {
                Log.error("Unknown", "/distributor/administrator/authorization", "Invalid access key:" + accessKey);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            accessKeyService.revoke(accessKey);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            Log.error(admin == null ? "Unknown" : admin.getId(), "/distributor/administrator/authorization",
                    "Failed to revoke access key: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            Log.access(admin == null ? "Unknown" : admin.getId(), "/distributor/administrator/authorization",
                    "Attempted to revoke access key");
        }
    }

    @PostMapping("/distributor/profile")
    public ResponseEntity<?> editProfile(@RequestHeader("Authorization") String adminAccessKey,
            @RequestBody List<Profile> newProfiles) {
        Administrator admin = null;
        try {
            admin = accessKeyService.validate(adminAccessKey);
            if (admin == null) {
                Log.error("Unknown", "/distributor/profile", "Invalid access key:" + adminAccessKey);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            for (Profile newProfile : newProfiles) {
                profileService.appendOrUpdateProfile(newProfile);
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            Log.error(admin == null ? "Unknown" : admin.getId(), "/distributor/profile",
                    "Failed to edit profile: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            Log.access(admin == null ? "Unknown" : admin.getId(), "/distributor/profile",
                    "Attempted to edit profile");
        }
    }

    @PostMapping("/distributor/schedule")
    public ResponseEntity<?> editSchedule(@RequestHeader("Authorization") String adminAccessKey,
            @RequestBody List<Schedule> newSchedules) {
        Administrator admin = null;
        try {
            admin = accessKeyService.validate(adminAccessKey);
            if (admin == null) {
                Log.error("Unknown", "/distributor/schedule", "Invalid access key:" + adminAccessKey);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            for (Schedule newSchedule : newSchedules) {
                scheduleService.appendOrUpdateSchedule(newSchedule);
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            Log.error(admin == null ? "Unknown" : admin.getId(), "/distributor/schedule",
                    "Failed to edit schedule: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            Log.access(admin == null ? "Unknown" : admin.getId(), "/distributor/schedule",
                    "Attempted to edit schedule");
        }
    }

    @PostMapping("/distributor/link")
    public ResponseEntity<?> editSiteLink(@RequestHeader("Authorization") String adminAccessKey,
            @RequestBody List<com.meigetsu.arisu.calendar.model.SiteLink> newSiteLinks) {
        Administrator admin = null;
        try {
            admin = accessKeyService.validate(adminAccessKey);
            if (admin == null) {
                Log.error("Unknown", "/distributor/link", "Invalid access key:" + adminAccessKey);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            for (SiteLink newSiteLink : newSiteLinks) {
                siteLinkService.appendOrUpdateSiteLink(newSiteLink);
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            Log.error(admin == null ? "Unknown" : admin.getId(), "/distributor/link",
                    "Failed to edit site link: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } finally {
            Log.access(admin == null ? "Unknown" : admin.getId(), "/distributor/link",
                    "Attempted to edit site link");
        }
    }
}
