package com.meigetsu.arisu.calendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meigetsu.arisu.calendar.model.Profile;
import com.meigetsu.arisu.calendar.repository.ProfileRepository;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getAllProfile() {
        return profileRepository.findAll();
    }

    public void appendOrUpdateProfile(Profile profile) {
        Profile current = profileRepository.findByColumn(profile.getColumn()).orElse(null);
        if (current == null) {
            if (!profile.contentIsEmpty())
                profileRepository.save(profile);
        } else {
            if (profile.contentIsEmpty()) {
                profileRepository.delete(current);
            } else {
                Profile existing = current;
                existing.setContent(profile);
                profileRepository.save(existing);
            }
        }
    }
}
