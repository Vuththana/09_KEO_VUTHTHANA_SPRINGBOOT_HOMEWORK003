package com.goros._9_keo_vuththana_springboot_homework003.repository;

import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Event;
import com.goros._9_keo_vuththana_springboot_homework003.model.request.EventRequest;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface EventRepository {
    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id", one = @One(select = "com.goros._9_keo_vuththana_springboot_homework003.repository.VenueRepository.getVenueById")),
            @Result(property = "attendee", column = "event_id", many = @Many(select = "com.goros._9_keo_vuththana_springboot_homework003.repository.EventAttendeeRepository.getAttendeeByEventId"))
    })
    @Select("""
    SELECT * FROM events LIMIT #{size} OFFSET #{offset}
    """)
    List<Event> getAllEvents(Integer offset, Integer size);

    @ResultMap("eventMapper")
    @Select("""
    SELECT * FROM events WHERE event_id = #{eventId}
    """)
    Event getEventById(Integer eventId);

    @ResultMap("eventMapper")
    @Select("""
    INSERT INTO events VALUES (default, #{eventName}, #{eventDate}, #{venueId}) RETURNING *
    """)
    Event saveEvent(EventRequest eventRequest);

    @Delete("""
    DELETE FROM events WHERE event_id = #{eventId}
    """)
    int deleteEventById(Integer eventId);

    @ResultMap("eventMapper")
    @Update("""
    UPDATE events SET event_name = #{req.eventName}, event_date = #{req.eventDate}, venue_id = #{req.venueId} WHERE event_id = #{eventId};
    """)
    int updateEventById(@Param("req") EventRequest eventRequest,Integer eventId);

}
