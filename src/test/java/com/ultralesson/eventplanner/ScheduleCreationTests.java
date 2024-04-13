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
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testScheduleCreationWithPastStartTime(){
        EventPlanner eventPlanner = new EventPlanner();
        Venue venue = new Venue(2, "Main Auditorium", "123 testing street", 200);
        Event event = new Event(3, "Music Concert", "Annual music gala", venue);
        LocalDateTime startTime = LocalDateTime.now().minusDays(1); // Scheduling in the past
        LocalDateTime endTime = startTime.plusHours(3);

        // Action: Attempt to schedule the event with an invalid start time
        eventPlanner.scheduleEvent(event, venue, startTime, endTime);

        // The test should expect an IllegalArgumentException due to the past start time.
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testScheduleCreationWithOverlappingTimes(){
        EventPlanner eventPlanner = new EventPlanner();
        Venue venue = new Venue(1, "Conference Hall", "123 Testing street", 300);
        Event event = new Event(1, "Technical Meeting", "A meetup for tech enthusiasts", venue);
        LocalDateTime startTime = LocalDateTime.now().plusDays(1);
        LocalDateTime endTime = startTime.plusHours(2);

        eventPlanner.scheduleEvent(event, venue, startTime, endTime);

        // Attempting to schedule another event at the same venue with overlapping times
        Event secondEvent = new Event(2, "Another Meeting", "Another meetup", venue);
        LocalDateTime overlappingStartTime = startTime.plusMinutes(30);
        LocalDateTime overlappingEndTime = overlappingStartTime.plusHours(1);

        eventPlanner.scheduleEvent(secondEvent, venue, overlappingStartTime, overlappingEndTime);
        // Expected to throw IllegalArgumentException due to time overlap.
    }

}
