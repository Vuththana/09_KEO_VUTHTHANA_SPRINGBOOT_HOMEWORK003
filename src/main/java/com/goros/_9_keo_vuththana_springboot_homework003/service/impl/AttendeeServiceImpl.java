package com.goros._9_keo_vuththana_springboot_homework003.service.impl;

import com.goros._9_keo_vuththana_springboot_homework003.exception.EmailExistException;
import com.goros._9_keo_vuththana_springboot_homework003.exception.NotFoundException;
import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Attendee;
import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Venue;
import com.goros._9_keo_vuththana_springboot_homework003.model.request.AttendeeRequest;
import com.goros._9_keo_vuththana_springboot_homework003.repository.AttendeeRepository;
import com.goros._9_keo_vuththana_springboot_homework003.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    @Override
    public List<Attendee> getAllAttendees(Integer page, Integer size) {
        int offset = (page - 1) * size;
        return attendeeRepository.getAllAttendees(offset, size);
    }

    public Attendee getAttendeeById(Integer attendeeId) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        if(attendee == null) {
            throw new NotFoundException("Venue with " + attendeeId + " not found.");
        }
        return attendee;
    }

    @Override
    public Attendee saveAttendee(AttendeeRequest attendeeRequest) {
        int emailExists = attendeeRepository.countByEmail(attendeeRequest.getEmail());
        if(emailExists != 0) {
            throw new EmailExistException("Attendee email already exists");
        }
        return attendeeRepository.saveAttendee(attendeeRequest);
    }

    @Override
    public int deleteAttendeeById(Integer attendeeId) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        if(attendee == null) {
            throw new NotFoundException("Venue with " + attendeeId + " not found.");
        }
        return attendeeRepository.deleteAttendeeById(attendeeId);
    }

    @Override
    public int updateAttendeeById(AttendeeRequest attendeeRequest, Integer attendeeId) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);
        if(attendee == null) {
            throw new NotFoundException("Venue with " + attendeeId + " not found.");
        }
        return attendeeRepository.updateAttendeeById(attendeeRequest, attendeeId);
    }
}
