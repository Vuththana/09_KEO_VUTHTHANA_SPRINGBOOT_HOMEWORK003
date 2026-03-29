package com.goros._9_keo_vuththana_springboot_homework003.controller;

import com.goros._9_keo_vuththana_springboot_homework003.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;
}
