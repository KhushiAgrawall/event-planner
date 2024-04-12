package com.ultralesson.eventplanner.service;

import com.ultralesson.eventplanner.model.Attendee;
import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Schedule;
import com.ultralesson.eventplanner.model.Venue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventPlanner {
    private List<Event> events;
    private List<Venue> venues;
    private List<Attendee> attendees;
    private List<Schedule> schedules;

    public EventPlanner() {
        this.events = new ArrayList<>();
        this.venues = new ArrayList<>();
        this.attendees = new ArrayList<>();
        this.schedules = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addVenue(Venue venue) {
        venues.add(venue);
    }

    public void addAttendee(Attendee attendee) {
        attendees.add(attendee);
    }

    public void scheduleEvent(Event event, Venue venue, LocalDateTime startTime, LocalDateTime endTime) {
        int scheduleId = schedules.size() + 1;
        Schedule schedule = new Schedule(scheduleId, event, venue, startTime, endTime);
        schedules.add(schedule);
    }

    public void updateEvent(Event updatedEvent) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == updatedEvent.getId()) {
                events.set(i, updatedEvent);
                break;
            }
        }
    }

    public void cancelEvent(int eventId) {
        events.removeIf(event -> event.getId() == eventId);
        schedules.removeIf(schedule -> schedule.getEvent().getId() == eventId);
    }

    public void deleteEvent(int eventId) {
        cancelEvent(eventId);
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }
}

