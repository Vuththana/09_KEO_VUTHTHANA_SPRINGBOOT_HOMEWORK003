package com.goros._9_keo_vuththana_springboot_homework003.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeRequest {
    @NotBlank(message = "Attendee name must not be blank")
    @Size(max = 100, message = "Attendee name must be at most 100 characters")
    private String attendeeName;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be a valid email address")
    private String email;
}
