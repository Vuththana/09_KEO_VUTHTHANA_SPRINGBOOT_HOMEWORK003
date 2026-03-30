package com.goros._9_keo_vuththana_springboot_homework003.repository;

import com.goros._9_keo_vuththana_springboot_homework003.model.entity.Attendee;
import com.goros._9_keo_vuththana_springboot_homework003.model.request.AttendeeRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column =  "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })
    @Select("""
    SELECT * FROM attendees LIMIT #{size} OFFSET #{offset}
    """)
    List<Attendee> getAllAttendees(Integer offset, Integer size);

    @ResultMap("attendeeMapper")
    @Select("""
    SELECT * FROM attendees WHERE attendee_id = #{attendeeId}
    """)
    Attendee getAttendeeById(@Param("attendeeId") Integer attendeeId);

    @ResultMap("attendeeMapper")
    @Select("""
    INSERT INTO attendees VALUES (default, #{attendeeName}, #{email}) RETURNING *
    """) Attendee saveAttendee(AttendeeRequest attendeeRequest);

    @ResultMap("attendeeMapper")
    @Delete("""
    DELETE FROM attendees WHERE attendee_id = #{attendeeId}
    """)
    int deleteAttendeeById(@Param("attendeeId") Integer attendeeId);

    @ResultMap("attendeeMapper")
    @Update("""
    UPDATE attendees SET attendee_name = #{attendeeName}, email = #{email} WHERE attendee_id #{attendeeId}
    """)
    int updateAttendeeById(@Param("req") AttendeeRequest attendeeRequest, @Param("attendeeId") Integer attendeeId);

    @Select("""
    SELECT COUNT(*) FROM attendees WHERE email = #{email}
    """)
    int countByEmail(String email);
}