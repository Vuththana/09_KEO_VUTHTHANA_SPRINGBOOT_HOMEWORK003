package com.goros._9_keo_vuththana_springboot_homework003.service.impl;

import com.goros._9_keo_vuththana_springboot_homework003.exception.NotFoundException;
import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Venue;
import com.goros._9_keo_vuththana_springboot_homework003.model.request.VenueRequest;
import com.goros._9_keo_vuththana_springboot_homework003.repository.VenueRepository;
import com.goros._9_keo_vuththana_springboot_homework003.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenues(Integer page, Integer size) {
        Integer offset = (page - 1) * size;

        return venueRepository.getAllVenues(offset, size);
    }

    @Override
    public Venue getVenueById(Integer venueId) {
        Venue venue = venueRepository.getVenueById(venueId);
        if(venue == null) {
            throw new NotFoundException("Venue with " + venueId + " not found.");
        }
        return venue;
    }

    @Override
    public Venue saveVenue(VenueRequest venueRequest) {
        return venueRepository.saveVenue(venueRequest);
    }

    @Override
    public int deleteVenueById(Integer venueId) {
        Venue venue = venueRepository.getVenueById(venueId);
        if(venue == null) {
            throw new NotFoundException("Venue with " + venueId + " not found.");
        }
        return venueRepository.deleteVenueById(venueId);
    }

    @Override
    public int updateVenueById(VenueRequest venueRequest, Integer venueId) {
        Venue venue = venueRepository.getVenueById(venueId);
        if(venue == null) {
            throw new NotFoundException("Venue with " + venueId + " not found.");
        }
        return venueRepository.updateVenueById(venueRequest, venueId);
    }
}
