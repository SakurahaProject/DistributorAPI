package com.meigetsu.arisu.calendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meigetsu.arisu.calendar.model.SiteLink;
import com.meigetsu.arisu.calendar.repository.SiteLinkRepository;

@Service
public class SiteLinkService {
    @Autowired
    private SiteLinkRepository siteLinkRepository;

    public List<SiteLink> getAllSiteLinks() {
        return siteLinkRepository.findAll();
    }

    public void appendOrUpdateSiteLink(SiteLink siteLink) {
        SiteLink current = siteLinkRepository.findByName(siteLink.getSiteName()).orElse(null);
        if (current == null) {
            if (!siteLink.contentIsEmpty())
                siteLinkRepository.save(siteLink);
        } else {
            if (siteLink.contentIsEmpty()) {
                siteLinkRepository.delete(current);
            } else {
                SiteLink existing = current;
                existing.setSiteUrl(siteLink);
                siteLinkRepository.save(existing);
            }
        }
    }

}
