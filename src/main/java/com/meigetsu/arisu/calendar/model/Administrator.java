package com.meigetsu.arisu.calendar.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.meigetsu.arisu.calendar.component.HashUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "administrators", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID", name = "UK_ID"),
        @UniqueConstraint(columnNames = "MailAddress", name = "UK_MailAddress")
})
public class Administrator {
    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 8)
    @Pattern(regexp = "^a[0-9]{6}$", message = "Invalid administrator ID format")
    private String id;
    @Column(name = "MailAddress", nullable = false, unique = true)
    private String mailAddress;
    @Column(name = "Password", nullable = false, length = 128)
    @Pattern(regexp = "^[0-9A-F]{128}", message = "Invalid password hash format")
    private String password;
    @CreationTimestamp
    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt = null;
    @OneToMany(mappedBy = "administrator")
    private List<AccessKey> accessKeys = new ArrayList<>();

    public Administrator() {
    }

    public Administrator(String id, String mailAddress, String password) {
        this.id = id;
        this.mailAddress = mailAddress;
        this.password = HashUtils.Password(password);
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public boolean comparePassword(String password) {
        return HashUtils.Password(password).equals(this.password);
    }
}
