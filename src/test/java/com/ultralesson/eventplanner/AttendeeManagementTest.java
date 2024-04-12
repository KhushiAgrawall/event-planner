package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Attendee;
import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Venue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AttendeeManagementTest {
    @Test
    public void testAddingAttendeeToEvent()
    {
        Event event = new Event(1, "Adding new Event", "Connect with Testers", new Venue(1, "Conference Hall A", "123 Business Rd.", 150));
        Attendee newAttendee = new Attendee(2, "John Dsouza", "John@gmail.com");
        int initialAttendeeCount = event.getAttendees().size();
        event.addAttendee(newAttendee);
        Assert.assertTrue(event.getAttendees().contains(newAttendee), "The new attendee should be added to the event.");
        Assert.assertEquals(event.getAttendees().size(), initialAttendeeCount + 1, "Attendee count should be increased by one.");
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddingInvalidAttendee() {
        Event event = new Event(1, "Adding new Event", "Connect with Testers", new Venue(1, "Conference Hall A", "123 Business Rd.", 150));
        Attendee invalidAttendee = new Attendee(3, "Invalid User", "invalidemail"); // Invalid email format
        event.addAttendee(invalidAttendee);
    }
    

}
