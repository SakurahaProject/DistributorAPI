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
@Table(name = "profiles", uniqueConstraints = {
        @UniqueConstraint(columnNames = "Column", name = "UK_Column")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    @JsonIgnore
    private Long id;
    @Column(name = "Column")
    @JsonProperty("column")
    private String column;
    @Column(name = "Content")
    @JsonProperty("content")
    private String content;

    public String getColumn() {
        return column;
    }

    public void setContent(Profile newData) {
        this.content = newData.content;
    }

    public boolean contentIsEmpty() {
        return content == null || content.isEmpty();
    }
}
