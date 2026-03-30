package com.goros._9_keo_vuththana_springboot_homework003.service;

import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Event;
import com.goros._9_keo_vuththana_springboot_homework003.model.request.EventRequest;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(Integer page, Integer size);
    Event getEventById(Integer eventId);
    Event saveEvent(EventRequest eventRequest);
    int deleteEventById(Integer eventId);
    int updateEventById(EventRequest eventRequest, Integer eventId);
}
