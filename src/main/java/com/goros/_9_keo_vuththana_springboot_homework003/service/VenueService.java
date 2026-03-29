package com.goros._9_keo_vuththana_springboot_homework003.service;

import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Venue;
import com.goros._9_keo_vuththana_springboot_homework003.model.request.VenueRequest;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenues(Integer page, Integer size);
    Venue getVenueById(Integer venueId);
    Venue saveVenue(VenueRequest venueRequest);
    int deleteVenueById(Integer venueId);
    int updateVenueById(VenueRequest venueRequest ,Integer venueId);
}
