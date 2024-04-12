package com.ultralesson.eventplanner.service;

import com.ultralesson.eventplanner.model.Attendee;
import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Schedule;
import com.ultralesson.eventplanner.model.Venue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleFinder {
    private EventPlanner eventPlanner;

    public ScheduleFinder(EventPlanner eventPlanner) {
        this.eventPlanner = eventPlanner;
    }

    public List<Schedule> findSchedulesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Schedule> foundSchedules = new ArrayList<>();
        for (Schedule schedule : eventPlanner.getSchedules()) {
            if (schedule.getStartTime().isAfter(startDate) && schedule.getEndTime().isBefore(endDate)) {
                foundSchedules.add(schedule);
            }
        }
        return foundSchedules;
    }

    public List<Schedule> findSchedulesToday() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59);

        return findSchedulesByDateRange(startOfDay, endOfDay);
    }

    public List<Schedule> findSchedulesByVenue(Venue venue) {
        List<Schedule> foundSchedules = new ArrayList<>();
        for (Schedule schedule : eventPlanner.getSchedules()) {
            if (schedule.getVenue().equals(venue)) {
                foundSchedules.add(schedule);
            }
        }
        return foundSchedules;
    }

    public List<Schedule> findSchedulesByEvent(Event event) {
        List<Schedule> foundSchedules = new ArrayList<>();
        for (Schedule schedule : eventPlanner.getSchedules()) {
            if (schedule.getEvent().equals(event)) {
                foundSchedules.add(schedule);
            }
        }
        return foundSchedules;
    }

    public List<Schedule> findSchedulesByAttendee(Attendee attendee) {
        List<Schedule> foundSchedules = new ArrayList<>();
        for (Schedule schedule : eventPlanner.getSchedules()) {
            Event event = schedule.getEvent();
            if (event.getAttendees().contains(attendee)) {
                foundSchedules.add(schedule);
            }
        }
        return foundSchedules;
    }

    public List<Schedule> findSchedulesByEventName(String eventName) throws IllegalArgumentException {
        if (eventName == null || eventName.isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be null or empty");
        }

        List<Schedule> foundSchedules = new ArrayList<>();
        for (Schedule schedule : eventPlanner.getSchedules()) {
            Event event = schedule.getEvent();
            if (event.getName().equalsIgnoreCase(eventName)) {
                foundSchedules.add(schedule);
            }
        }
        return foundSchedules;
    }


}

