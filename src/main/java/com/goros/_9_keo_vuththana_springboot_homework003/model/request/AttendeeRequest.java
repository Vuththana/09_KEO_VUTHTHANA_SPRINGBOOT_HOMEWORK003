package com.goros._9_keo_vuththana_springboot_homework003.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeRequest {
    private String attendeeName;
    private String email;
}
