package com.goros._9_keo_vuththana_springboot_homework003.service.impl;

import com.goros._9_keo_vuththana_springboot_homework003.exception.NotFoundException;
import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Attendee;
import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Event;
import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Venue;
import com.goros._9_keo_vuththana_springboot_homework003.model.request.EventRequest;
import com.goros._9_keo_vuththana_springboot_homework003.repository.AttendeeRepository;
import com.goros._9_keo_vuththana_springboot_homework003.repository.EventAttendeeRepository;
import com.goros._9_keo_vuththana_springboot_homework003.repository.EventRepository;
import com.goros._9_keo_vuththana_springboot_homework003.repository.VenueRepository;
import com.goros._9_keo_vuththana_springboot_homework003.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final AttendeeRepository attendeeRepository;
    private final EventAttendeeRepository eventAttendeeRepository;

    @Override
    public List<Event> getAllEvents(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return eventRepository.getAllEvents(offset, size);
    }

    @Override
    public Event getEventById(Integer eventId) {
        Event event = eventRepository.getEventById(eventId);
        if(event == null) {
            throw new NotFoundException("Event with " + eventId + " not found");
        }
        return event;
    }

    @Override
    public Event saveEvent(EventRequest eventRequest) {
        Venue venue = venueRepository.getVenueById(eventRequest.getVenueId());
        if(venue == null) {
            throw new NotFoundException("Venue with " + eventRequest.getVenueId() + " not found");
        }
        Event event = eventRepository.saveEvent(eventRequest);

        for(Integer attendeeId : eventRequest.getAttendeeId()) {
            Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
            if(attendee == null) {
                throw new NotFoundException("Attendee with " + attendeeId + " not found");
            }
            eventAttendeeRepository.insertAttendeeIntoEvent(Math.toIntExact(event.getEventId()), attendeeId);
        }

        return event;
    }

    @Override
    public int deleteEventById(Integer eventId) {
        Event event = eventRepository.getEventById(eventId);
        if(event == null) {
            throw new NotFoundException("Event with " + eventId + " not found");
        }
        return eventRepository.deleteEventById(eventId);
    }

    @Override
    public int updateEventById(EventRequest eventRequest, Integer eventId) {
        Event event = eventRepository.getEventById(eventId);
        if(event == null) {
            throw new NotFoundException("Event with " + eventId + " not found");
        }
        int updatedEvent = eventRepository.updateEventById(eventRequest, eventId);
        eventAttendeeRepository.deleteAttendeesByEventId(eventId);
        for (Integer attendeeId : eventRequest.getAttendeeId()) {
            Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
            if(attendee == null) {
                throw new NotFoundException("Attendee with " + attendeeId + " not found");
            }
            eventAttendeeRepository.insertAttendeeIntoEvent(eventId, attendeeId);
        }

        return updatedEvent;
    }
}
