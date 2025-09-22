package com.meigetsu.arisu.calendar.service;

import java.util.List;
import java.util.stream.Stream;

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

    public void appendOrUpdateProfile(Stream<Profile> profile) {
        Stream<Profile> current = profileRepository.findAll().stream();
        List<Profile> appendTarget = profile
                .filter(np -> current.noneMatch(cp -> cp.matchColumn(np)) && !np.contentIsEmpty()).toList();
        List<Profile> updateTarget = profile.filter(np -> current.anyMatch(cp -> cp.matchColumn(np))).toList();
        List<Profile> delTarget = current.filter(p -> profile.noneMatch(np -> np.matchColumn(p))).toList();
        profileRepository.deleteAll(delTarget);
        profileRepository.saveAll(updateTarget);
        profileRepository.saveAll(appendTarget);
    }
}
