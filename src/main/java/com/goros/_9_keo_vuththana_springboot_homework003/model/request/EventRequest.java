package com.goros._9_keo_vuththana_springboot_homework003.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    @NotBlank(message = "Event name is required")
    private String eventName;

    @NotNull(message = "Event date is required")
    private LocalDate eventDate;

    @NotNull(message = "Venue ID is required")
    private Integer venueId;

    @NotEmpty(message = "Attendee list cannot be empty")
    private List<Integer> attendeeId;
}
