package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Attendee;
import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Venue;
import com.ultralesson.eventplanner.service.EventPlanner;
import com.ultralesson.eventplanner.service.InvitationSender;
import org.testng.annotations.Test;
import org.mockito.Mockito;

public class InvitationSenderTest {
    @Test
    public void testSendingInvitationsToAttendees() {
        EventPlanner eventPlanner = new EventPlanner();
        InvitationSender invitationSender = new InvitationSender();
        Venue venue = new Venue(1, "Conference Hall", "123 Main Street", 100);
        Event event = new Event(1, "Annual Meeting", "Company's annual meet-up", venue);
        Attendee attendee1 = new Attendee(1, "Alice Johnson", "alice.johnson@example.com");
        Attendee attendee2 = new Attendee(2, "Bob Smith", "bob.smith@example.com");

        event.addAttendee(attendee1);
        event.addAttendee(attendee2);
        eventPlanner.addEvent(event);

        // Initialize a spy or mock to simulate the actual invitation sending service
        // Spy/Stub the sendInvitations method to avoid actual email sending

        // Call the method under test
        invitationSender.sendInvitations(event);

        // Assertions to validate that the invitations were sent
        // For example, check if your spy or mock recorded the calls to send emails
        // Assert.assertTrue(<spy_or_mock>.invoked("sendEmailMethod"), "Email sending method should be called for each attendee.");
    }
    @Test(expectedExceptions = IllegalArgumentException.class, description = "Should throw exception when attempting to send invitations to an event with no attendees")
    public void testSendingInvitationsToEventWithNoAttendees() {
        // Create instances of EventPlanner, InvitationSender, Venue, and Event
        EventPlanner eventPlanner = new EventPlanner();
        InvitationSender invitationSender = new InvitationSender();
        Venue venue = new Venue(1, "Conference Hall", "123 Main St", 100);
        Event event = new Event(1, "Annual Meeting", "Company's annual meet-up", venue);

        // Add the event to the event planner
        eventPlanner.addEvent(event);

        // Call the method under test
        invitationSender.sendInvitations(event); // This line should throw IllegalArgumentException
    }


}
