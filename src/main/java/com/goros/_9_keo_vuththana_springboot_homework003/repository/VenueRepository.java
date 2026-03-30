package com.goros._9_keo_vuththana_springboot_homework003.repository;

import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Venue;
import com.goros._9_keo_vuththana_springboot_homework003.model.request.VenueRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name")
    })
    @Select("""
    SELECT * FROM venues LIMIT #{size} OFFSET #{offset}
    """)
    List<Venue> getAllVenues(Integer offset, Integer size);

    @ResultMap("venueMapper")
    @Select("""
    SELECT * FROM venues WHERE venue_id = #{venueId}
    """)
    Venue getVenueById(Integer venueId);

    @ResultMap("venueMapper")
    @Select("""
    INSERT INTO venues VALUES (default, #{req.venueName}, #{req.location}) RETURNING *
    """)
    Venue saveVenue(@Param("req") VenueRequest venueRequest);

    @ResultMap("venueMapper")
    @Delete("""
    DELETE FROM venues WHERE venue_id = #{venueId};
    """)
    int deleteVenueById(Integer venueId);

    @ResultMap("venueMapper")
    @Update("""
    UPDATE venues SET venue_id = #{req.venueId}, venueName = #{req.venueName}, location = #{req.location} WHERE venue_id = #{venueId};
    """)
    int updateVenueById(@Param("req") VenueRequest venueRequest, Integer venueId);
}