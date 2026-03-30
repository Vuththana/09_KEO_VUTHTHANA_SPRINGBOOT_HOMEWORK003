package com.goros._9_keo_vuththana_springboot_homework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Integer eventId;
    private String eventName;
    private Instant eventDate;
    private Venue venue;
    private List<Attendee> attendee;
}
