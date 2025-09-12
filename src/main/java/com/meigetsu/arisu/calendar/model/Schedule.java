package com.meigetsu.arisu.calendar.model;

import java.time.LocalDate;

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
@Table(name = "schedules", uniqueConstraints = {
        @UniqueConstraint(columnNames = "Date", name = "UK_Date")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Schedule {
    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonIgnore
    private String id;
    @Column(name = "Date", nullable = false, unique = true, columnDefinition = "DATE")
    @JsonProperty("date")
    private LocalDate date;
    @Column(name = "Schedule", nullable = false, columnDefinition = "TEXT")
    @JsonProperty("schedule")
    private String schedule;

    public LocalDate getDate() {
        return date;
    }

    public void setNewSchedule(Schedule newData) {
        this.schedule = newData.schedule;
    }

    public boolean scheduleIsEmpty() {
        return this.schedule == null || this.schedule.isBlank();
    }
}
