package com.goros._9_keo_vuththana_springboot_homework003.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest {

    @NotBlank(message = "Venue name is required")
    @Size(max = 200, message = "Venue name must be less than 200 characters")
    private String venueName;

    @NotBlank(message = "Location is required")
    @Size(max = 200, message = "Location must be less than 200 characters")
    private String location;
}
