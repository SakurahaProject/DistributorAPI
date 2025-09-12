package com.meigetsu.arisu.calendar.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.meigetsu.arisu.calendar.service.ProfileService;
import com.meigetsu.arisu.calendar.service.ScheduleService;
import com.meigetsu.arisu.calendar.service.SiteLinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserSideActionController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private SiteLinkService siteLinkService;
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        try {
            return ResponseEntity.ok(profileService.getAllProfile());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
    @GetMapping("/schedule")
    public ResponseEntity<?> getSchedule(@RequestParam(name = "date", required = true) String date) {
        try {
            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return ResponseEntity.badRequest().body("Invalid date format. Use YYYY-MM-DD.");
            }
            return ResponseEntity.ok(scheduleService.getSchedulesBetween(LocalDate.parse(date)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
    @GetMapping("/link")
    public ResponseEntity<?> getSiteLinks() {
        try {
            return ResponseEntity.ok(siteLinkService.getAllSiteLinks());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
