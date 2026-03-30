package com.goros._9_keo_vuththana_springboot_homework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendee {
    private Integer attendeeId;
    private String attendeeName;
    private String email;
}
