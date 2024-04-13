package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Schedule;
import com.ultralesson.eventplanner.model.Venue;
import com.ultralesson.eventplanner.service.EventPlanner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

public class ScheduleCreationTests {
    @Test
    public void testValidScheduleCreation() {
        EventPlanner eventPlanner = new EventPlanner();
        int initialScheduleCount = eventPlanner.getSchedules().size();
        Venue venue = new Venue(1, "Conference Hall", "123 Testing street", 300);
        Event event = new Event(1, "Technical  Meeting", "A meetup for tech enthusiasts", venue);
        LocalDateTime startTime = LocalDateTime.now().plusDays(1);
        LocalDateTime endTime = startTime.plusHours(2);        // Action: Schedule the event
        eventPlanner.scheduleEvent(event, venue, startTime, endTime);

        int newScheduleCount = eventPlanner.getSchedules().size();     //Verify schedule is correctly created and stored
        Schedule createdSchedule = eventPlanner.getSchedules().get(initialScheduleCount);

        Assert.assertEquals(newScheduleCount, initialScheduleCount + 1, "New schedule should be added.");
        Assert.assertEquals(createdSchedule.getEvent(), event, "The event in the schedule should match the created event.");
        Assert.assertEquals(createdSchedule.getVenue(), venue, "The venue in the schedule should match the specified venue.");
        Assert.assertEquals(createdSchedule.getStartTime(), startTime, "The start time should match what was scheduled.");
        Assert.assertEquals(createdSchedule.getEndTime(), endTime, "The end time should match what was scheduled.");
    }
    @Test(expectedExceptions = IllegalArgumentException.class, description = "Should throw exception when attempting to schedule an event with a past start time")
    public void testScheduleCreationWithPastStartTime() {
        // Create an EventPlanner instance
        EventPlanner eventPlanner = new EventPlanner();

        // Define a venue
        Venue venue = new Venue(1, "Conference Hall", "123 Testing street", 300);

        // Create an event with a past start time
        Event event = new Event(1, "Technical Meeting", "A meetup for tech enthusiasts", venue);
        LocalDateTime startTime = LocalDateTime.now().minusDays(1); // Scheduling in the past
        LocalDateTime endTime = startTime.plusHours(2);

        // Attempt to schedule the event with a past start time
        eventPlanner.scheduleEvent(event, venue, startTime, endTime);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, description = "Should throw exception when attempting to schedule events with overlapping times")
    public void testScheduleCreationWithOverlappingTimes() {
        // Create an EventPlanner instance
        EventPlanner eventPlanner = new EventPlanner();

        // Define a venue
        Venue venue = new Venue(1, "Conference Hall", "123 Testing street", 300);

        // Schedule the first event
        Event event1 = new Event(1, "Technical Meeting", "A meetup for tech enthusiasts", venue);
        LocalDateTime startTime1 = LocalDateTime.now().plusDays(1);
        LocalDateTime endTime1 = startTime1.plusHours(2);
        eventPlanner.scheduleEvent(event1, venue, startTime1, endTime1);

        // Attempt to schedule another event with overlapping times
        Event event2 = new Event(2, "Another Meeting", "Another meetup", venue);
        LocalDateTime overlappingStartTime = startTime1.plusMinutes(30);
        LocalDateTime overlappingEndTime = overlappingStartTime.plusHours(1);

        // Attempt to schedule the second event with overlapping times
        eventPlanner.scheduleEvent(event2, venue, overlappingStartTime, overlappingEndTime);
    }


}
