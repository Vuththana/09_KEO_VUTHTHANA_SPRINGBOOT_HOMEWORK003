package com.goros._9_keo_vuththana_springboot_homework003.controller;

import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Attendee;
import com.goros._9_keo_vuththana_springboot_homework003.model.request.AttendeeRequest;
import com.goros._9_keo_vuththana_springboot_homework003.model.response.ApiResponse;
import com.goros._9_keo_vuththana_springboot_homework003.model.response.ApiResponseVoid;
import com.goros._9_keo_vuththana_springboot_homework003.service.AttendeeService;
import com.goros._9_keo_vuththana_springboot_homework003.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/attendees/")
public class AttendeeController {
    private final AttendeeService attendeeService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        List<Attendee> attendees = attendeeService.getAllAttendees(page, size);
        ApiResponse<List<Attendee>> response = ResponseUtil.success( HttpStatus.OK,"Attendees successfully fetched", attendees);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable("attendee-id") Integer attendeeId) {
        Attendee attendee = attendeeService.getAttendeeById(attendeeId);
        ApiResponse<Attendee> response = ResponseUtil.success(HttpStatus.OK, "Attendee successfully fetched", attendee);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> saveAttendee(@RequestBody AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeService.saveAttendee(attendeeRequest);
        ApiResponse<Attendee> response = ResponseUtil.success(HttpStatus.CREATED,"Attendee successfully created", attendee);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("{attendee-id}")
    public ResponseEntity<ApiResponseVoid> deleteAttendeeById(@PathVariable("attendee-id") Integer attendeeId) {
        attendeeService.deleteAttendeeById(attendeeId);
        ApiResponseVoid response = ResponseUtil.successVoid(HttpStatus.OK ,"Attendee successfully deleted");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("{attendee-id}")
    public ResponseEntity<ApiResponseVoid> updateVenueById(@RequestBody AttendeeRequest attendeeRequest, @PathVariable("attendee-id") Integer attendeeId) {
        attendeeService.updateAttendeeById(attendeeRequest, attendeeId);
        ApiResponseVoid response = ResponseUtil.successVoid(HttpStatus.OK, "Attendee successfully updated");
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
