package com.goros._9_keo_vuththana_springboot_homework003.service;

import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Attendee;
import com.goros._9_keo_vuththana_springboot_homework003.model.request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendees(Integer page, Integer size);
    Attendee getAttendeeById(Integer attendeeId);
    Attendee saveAttendee(AttendeeRequest attendeeRequest);
    int deleteAttendeeById(Integer attendeeId);
    int updateAttendeeById(AttendeeRequest attendeeRequest, Integer attendeeId);
}
