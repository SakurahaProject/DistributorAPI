package com.meigetsu.arisu.calendar.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meigetsu.arisu.calendar.component.File;
import com.meigetsu.arisu.calendar.model.Schedule;
import com.meigetsu.arisu.calendar.model.ScheduleAndMemo;
import com.meigetsu.arisu.calendar.repository.ScheduleRepository;

@Service
public class ScheduleService {
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Autowired
    private ScheduleRepository scheduleRepository;

    private static String readMemoFromText(LocalDate mondayDate) throws IOException {
        String path = "./memos/" + dateFormatter.format(mondayDate) + ".txt";
        return Files.exists(Paths.get(path), LinkOption.NOFOLLOW_LINKS)
                ? File.readAllText(path)
                : "";
    }

    public ScheduleAndMemo getSchedulesBetween(LocalDate baseDate) throws IOException {
        DayOfWeek dayOfWeek = baseDate.getDayOfWeek();
        LocalDate mostRecentMonday = baseDate.minusDays(dayOfWeek.getValue() - DayOfWeek.MONDAY.getValue());
        LocalDate nextSunday = mostRecentMonday.plusDays(6);
        List<Schedule> schedules = scheduleRepository.findByDateBetween(mostRecentMonday, nextSunday);
        return new ScheduleAndMemo(schedules, readMemoFromText(mostRecentMonday));
    }

    public void appendOrUpdateSchedule(Schedule schedule) {
        Schedule current = scheduleRepository.findByDate(schedule.getDate()).orElse(null);
        if (current == null) {
            if (!schedule.scheduleIsEmpty())
                scheduleRepository.save(schedule);
        } else {
            if (schedule.scheduleIsEmpty()) {
                scheduleRepository.delete(current);
            } else {
                Schedule existing = current;
                existing.setNewSchedule(schedule);
                scheduleRepository.save(existing);
            }
        }
    }
}
