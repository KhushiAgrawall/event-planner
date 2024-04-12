package com.ultralesson.eventplanner.service;

import com.ultralesson.eventplanner.model.Attendee;
import com.ultralesson.eventplanner.model.Event;

import java.util.List;

public class InvitationSender {
    public void sendInvitations(Event event) {
        List<Attendee> attendees = event.getAttendees();

        for (Attendee attendee : attendees) {
            String email = attendee.getEmail();
            String eventName = event.getName();
            String eventDescription = event.getDescription();
            String venueName = event.getVenue().getName();

            // Deliberate bug: using '==' instead of equals() for string comparison
            if (email == "" || eventName == "" || eventDescription == "" || venueName == "") {
                System.out.println("Skipping invitation due to incomplete information.");
                continue;
            }

            System.out.println("Sending invitation to " + email);
            // Send the actual invitation (e.g., by calling an email service)
        }
    }
}

