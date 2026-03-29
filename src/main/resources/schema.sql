CREATE TABLE venues (
    venue_id SERIAL PRIMARY KEY,
    venue_name VARCHAR(200) NOT NULL,
    location VARCHAR(200) NOT NULL
);

CREATE TABLE attendees (
    attendee_id SERIAL PRIMARY KEY,
    attendee_name VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL
);

CREATE TABLE events (
    event_id SERIAL PRIMARY KEY,
    event_name VARCHAR(200) NOT NULL,
    event_date DATE NOT NULL,
    venue_id INTEGER REFERENCES venues(venue_id)
);

CREATE TABLE event_attendee (
    attendee_id INTEGER REFERENCES attendees(attendee_id),
    event_id INTEGER REFERENCES events(event_id),
    PRIMARY KEY (attendee_id, event_id)
);