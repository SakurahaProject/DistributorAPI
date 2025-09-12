package com.meigetsu.arisu.calendar.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class ScheduleAndMemo {
    @JsonProperty("schedules")
    private List<Schedule> schedules;
    @JsonProperty("memo")
    private String memo;
    public ScheduleAndMemo(List<Schedule> schedules, String memo) {
        this.schedules = schedules;
        this.memo = memo;
    }
}
