package com.meigetsu.arisu.calendar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "sitelinks", uniqueConstraints = {
        @UniqueConstraint(columnNames = "SiteName", name = "UK_SiteName")
})
@JsonInclude(JsonInclude.Include.ALWAYS)
public class SiteLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    @JsonIgnore
    private Long id;
    @Column(name = "SiteName", nullable = false, unique = true)
    @JsonProperty("site")
    private String siteName;
    @Column(name = "URL", nullable = false)
    @JsonProperty("url")
    private String url;

    public boolean contentIsEmpty() {
        return this.url == null || this.url.isBlank();
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteUrl(SiteLink newLink) {
        this.url = newLink.url;
    }
}
