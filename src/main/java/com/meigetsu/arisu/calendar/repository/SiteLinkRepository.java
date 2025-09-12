package com.meigetsu.arisu.calendar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meigetsu.arisu.calendar.model.SiteLink;

@Repository
public interface SiteLinkRepository extends JpaRepository<SiteLink, Long> {
    @Query("SELECT s FROM SiteLink s WHERE s.siteName = :name")
    Optional<SiteLink> findByName(String name);
}
