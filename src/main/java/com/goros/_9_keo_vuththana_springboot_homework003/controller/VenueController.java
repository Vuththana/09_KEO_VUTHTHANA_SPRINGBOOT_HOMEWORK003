package com.goros._9_keo_vuththana_springboot_homework003.controller;

import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Venue;
import com.goros._9_keo_vuththana_springboot_homework003.model.request.VenueRequest;
import com.goros._9_keo_vuththana_springboot_homework003.model.response.ApiResponse;
import com.goros._9_keo_vuththana_springboot_homework003.model.response.ApiResponseVoid;
import com.goros._9_keo_vuththana_springboot_homework003.service.VenueService;
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
@RequestMapping("api/v1/venues/")
@Validated
public class VenueController {
    private final VenueService venueService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(@Positive(message= "Page must be positive") @RequestParam(defaultValue = "1") Integer page, @Positive(message= "Size must be positive") @RequestParam(defaultValue = "10") Integer size) {
        List<Venue> venues = venueService.getAllVenues(page, size);
        ApiResponse<List<Venue>> response = ResponseUtil.success( HttpStatus.OK,"Venues successfully fetched", venues);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@Positive(message= "Venue ID must be positive") @PathVariable("venue-id") Integer venueId) {
        Venue venue = venueService.getVenueById(venueId);
        ApiResponse<Venue> response = ResponseUtil.success(HttpStatus.OK, "Venue successfully fetched", venue);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> saveVenue(@Valid @RequestBody VenueRequest venueRequest) {
        Venue venue = venueService.saveVenue(venueRequest);
        ApiResponse<Venue> response = ResponseUtil.success(HttpStatus.CREATED,"Venue successfully created", venue);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("{venue-id}")
    public ResponseEntity<ApiResponseVoid> deleteVenueById(@Positive(message= "Venue ID must be positive") @PathVariable("venue-id") Integer venueId) {
        venueService.deleteVenueById(venueId);
        ApiResponseVoid response = ResponseUtil.successVoid(HttpStatus.OK ,"Venue successfully deleted");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("{venue-id}")
    public ResponseEntity<ApiResponseVoid> updateVenueById(@Valid @RequestBody VenueRequest venueRequest, @Positive(message= "Venue ID must be positive") @PathVariable("venue-id") Integer venueId) {
        venueService.updateVenueById(venueRequest, venueId);
        ApiResponseVoid response = ResponseUtil.successVoid(HttpStatus.OK, "Venue successfully updated");
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
