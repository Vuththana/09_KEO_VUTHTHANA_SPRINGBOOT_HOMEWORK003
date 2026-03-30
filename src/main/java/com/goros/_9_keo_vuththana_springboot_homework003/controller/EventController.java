package com.goros._9_keo_vuththana_springboot_homework003.controller;

import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Event;
import com.goros._9_keo_vuththana_springboot_homework003.model.request.EventRequest;
import com.goros._9_keo_vuththana_springboot_homework003.model.response.ApiResponse;
import com.goros._9_keo_vuththana_springboot_homework003.model.response.ApiResponseVoid;
import com.goros._9_keo_vuththana_springboot_homework003.service.EventService;
import com.goros._9_keo_vuththana_springboot_homework003.utils.ResponseUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/events/")
@Validated
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(@Positive(message = "Page must be positive") @RequestParam(defaultValue = "1") Integer page, @Positive(message = "Size must be positive") @RequestParam(defaultValue = "10") Integer size) {
        List<Event> events = eventService.getAllEvents(page, size);
        ApiResponse<List<Event>> response = ResponseUtil.success( HttpStatus.OK,"Events successfully fetched", events);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("{event-id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@Positive(message = "Event ID must be positive") @PathVariable("event-id") Integer eventId) {
        Event event = eventService.getEventById(eventId);
        ApiResponse<Event> response = ResponseUtil.success(HttpStatus.OK, "Event successfully fetched", event);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Event>> saveEvent(@Valid @RequestBody EventRequest eventRequest) {
        Event event = eventService.saveEvent(eventRequest);
        ApiResponse<Event> response = ResponseUtil.success(HttpStatus.CREATED,"Event successfully created", event);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("{event-id}")
    public ResponseEntity<ApiResponseVoid> deleteEventById(@Positive(message = "Event ID must be positive") @PathVariable("event-id") Integer eventId) {
        eventService.deleteEventById(eventId);
        ApiResponseVoid response = ResponseUtil.successVoid(HttpStatus.OK ,"Event successfully deleted");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("{event-id}")
    public ResponseEntity<ApiResponseVoid> updateEventById(@Valid @RequestBody EventRequest eventRequest, @Positive(message = "Event ID must be positive") @PathVariable("event-id") Integer eventId) {
        eventService.updateEventById(eventRequest, eventId);
        ApiResponseVoid response = ResponseUtil.successVoid(HttpStatus.OK, "Event successfully updated");
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
