package com.goros._9_keo_vuththana_springboot_homework003.repository;

import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Attendee;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface EventAttendeeRepository {
    @Insert("""
    INSERT INTO event_attendee(event_id, attendee_id)
    VALUES (#{eventId}, #{attendeeId})
    """)
    void insertAttendeeIntoEvent(Integer eventId, Integer attendeeId);


    @Results(id = "eventAttendeeResultMap", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    @Select("""
    SELECT a.attendee_id, a.attendee_name, a.email
    FROM attendees a
    INNER JOIN event_attendee ea ON a.attendee_id = ea.attendee_id
    WHERE ea.event_id = #{eventId}
    """)
    List<Attendee> getAttendeeByEventId(@Param("eventId") Integer eventId);

    @Delete("DELETE FROM event_attendee WHERE event_id = #{eventId}")
    void deleteAttendeesByEventId(@Param("eventId") Integer eventId);
}
