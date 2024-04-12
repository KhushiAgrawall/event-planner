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
    public void testScheduleCreation() {
        EventPlanner eventPlanner = new EventPlanner();
        Venue venue = new Venue(1, "Venue Test 1", "Intellej street", 100);
        Event event = new Event(1, "Event Test 1", "Event Test Description", venue);
        LocalDateTime startTime = LocalDateTime.now().plusDays(1);
        LocalDateTime endTime = startTime.plusHours(3);

        eventPlanner.scheduleEvent(event, venue, startTime, endTime);
        Schedule createdSchedule = eventPlanner.getSchedules().get(0);

        Assert.assertNotNull(createdSchedule, "Schedule should be created");
        Assert.assertEquals(createdSchedule.getEvent(), event, "Scheduled event should match the event created");
        Assert.assertEquals(createdSchedule.getVenue(), venue, "Scheduled venue should match the venue provided");
        Assert.assertEquals(createdSchedule.getStartTime(), startTime, "Schedule should have the correct start time");
        Assert.assertEquals(createdSchedule.getEndTime(), endTime, "Schedule should have the correct end time");
    }
    /*@Test(expectedExceptions = IllegalArgumentException.class)
    public void testSchedulingWithPastDate() {
        EventPlanner eventPlanner = new EventPlanner();
        Venue venue = new Venue(1, "Venue Test 2", "Intellej street", 100);
        Event event = new Event(1, "Event Test 2", "Event Test Description", venue);
        LocalDateTime startTime = LocalDateTime.now().minusDays(1); // Past date
        LocalDateTime endTime = startTime.plusHours(2);

        eventPlanner.scheduleEvent(event, venue, startTime, endTime);
    }*/
}
