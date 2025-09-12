package com.meigetsu.arisu.calendar.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meigetsu.arisu.calendar.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {
    @Query("SELECT s FROM Schedule s WHERE s.date BETWEEN :start AND :end ORDER BY s.date ASC")
    List<Schedule> findByDateBetween(LocalDate start, LocalDate end);
    @Query("SELECT s FROM Schedule s WHERE s.date = :date")
    Optional<Schedule> findByDate(LocalDate date);
}
