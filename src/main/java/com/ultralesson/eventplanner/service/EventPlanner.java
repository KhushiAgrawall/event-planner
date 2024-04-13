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
        if (startTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start time cannot be in the past.");
        }
        if (startTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Start time cannot be in the past.");
        }
        // Check for overlapping schedules
        for (Schedule existingSchedule : schedules) {
            LocalDateTime existingStartTime = existingSchedule.getStartTime();
            LocalDateTime existingEndTime = existingSchedule.getEndTime();

            // Check if the new event's start time falls within the existing schedule
            if ((startTime.isAfter(existingStartTime) || startTime.isEqual(existingStartTime)) && startTime.isBefore(existingEndTime)) {
                throw new IllegalArgumentException("Overlapping schedule detected.");
            }

            // Check if the new event's end time falls within the existing schedule
            if ((endTime.isAfter(existingStartTime)) && (endTime.isBefore(existingEndTime) || endTime.isEqual(existingEndTime))) {
                throw new IllegalArgumentException("Overlapping schedule detected.");
            }

            // Check if the existing schedule falls within the new event's time frame
            if ((existingStartTime.isAfter(startTime) || existingStartTime.isEqual(startTime)) && existingStartTime.isBefore(endTime)) {
                throw new IllegalArgumentException("Overlapping schedule detected.");
            }
        }
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
    public void removeVenue(int venueId) {
        venues.removeIf(venue -> venue.getId() == venueId);
        schedules.removeIf(schedule -> schedule.getVenue().getId() == venueId);
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

