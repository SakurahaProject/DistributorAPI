package com.meigetsu.arisu.calendar.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.ForeignKey;

@Entity
@Table(name = "accesskeys", uniqueConstraints = {
        @UniqueConstraint(columnNames = "AccessKey", name = "UK_AccessKey"),
        @UniqueConstraint(columnNames = "AdministratorId", name = "UK_AdministratorId")
})
public class AccessKey {
    @Id
    @Column(name = "AccessKey", length = 128, nullable = false, unique = true)
    private String accessKey;
    @ManyToOne
    @JoinColumn(name = "AdministratorId", referencedColumnName = "ID", nullable = false, foreignKey = @ForeignKey(name = "FK_AdministratorId"), unique = true)
    private Administrator administrator;
    @CreationTimestamp
    @Column(name = "CreatedAt", nullable = false, updatable = false)
    private LocalDateTime createdAt = null;

    public AccessKey() {
    }

    public AccessKey(String accessKey, Administrator administrator) {
        this.accessKey = accessKey;
        this.administrator = administrator;
    }

    public Administrator getAdministrator() {
        return administrator;
    }
}
