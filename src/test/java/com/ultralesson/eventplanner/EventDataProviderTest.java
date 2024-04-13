package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Venue;
import com.ultralesson.eventplanner.service.EventPlanner;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

 public class EventDataProviderTest {
        @DataProvider(name = "eventDataProvider")
        public Object[][] eventData() {
            return new Object[][]{
                    // Valid event details
                    {1,"Birthday Party","Sakshi Birthday Party", new Venue(1, "Hotel Aroma","Nimzari Naka, Shirpur",100)},
                    {2,"Freshers Party","QK Freshers Party", new Venue(1, "Cafe corner","Dadar, Mumbai",30)},
                    // Edge cases
                    {3, "", "Empty Name Event", new Venue(2, "Empty Venue","Empty City",10)},
                    {4, "Event with null description", null, new Venue(3, "Venue with null location", null, 50)},
                    // Invalid or incomplete data sets
                    {5, null, "Null Name Event", new Venue(4, "Null Venue","Null City",20)},
                    {6, "Incomplete Venue Event", "Event with incomplete venue", new Venue(5, "", "", -1)},
                    {7, "Event with null venue", "Null Venue Event", null}
            };
        }

     /*@Test(dataProvider = "eventDataProvider")
     public void createEventTest(int id, String name, String description, Venue venue, boolean expectSuccess, String expectedErrorMessage) {
         EventPlanner eventPlanner = new EventPlanner();
         try {
             Event event = new Event(id, name, description, venue);
             eventPlanner.addEvent(event);

             if (!expectSuccess) {
                 Assert.fail("Event creation should have failed but succeeded.");
             }
         } catch (IllegalArgumentException e) {
             if (expectSuccess) {
                 Assert.fail("Expected event creation to succeed, but it failed with error: " + e.getMessage());
             } else {
                 Assert.assertEquals(e.getMessage(), expectedErrorMessage, "Expected error message not received.");
             }
         }
     }*/
     @Test(dataProvider = "eventDataProvider")
     public void createEventTest(int id, String name, String description, Venue venue) {
         EventPlanner eventPlanner=new EventPlanner();
         try {
             Event event = new Event(id, name, description, venue);
             eventPlanner.addEvent(event);
             // Assert event creation success
             Assert.assertTrue(eventPlanner.getEvents().contains(event));

             // Additional assertions for event properties
             Assert.assertEquals(event.getId(), id);
             Assert.assertEquals(event.getName(), name);
             Assert.assertEquals(event.getDescription(), description);
             Assert.assertEquals(event.getVenue(), venue);
         } catch (IllegalArgumentException e) {
             // Assert error handling
             if (name == null || name.isEmpty() || venue == null || venue.getName().isEmpty()) {
                 Assert.assertNull(name); // Null or empty name expected to throw IllegalArgumentException
                 Assert.assertNull(venue); // Null or empty venue name expected to throw IllegalArgumentException
             } else {
                 Assert.fail("Event creation failed with valid data.");
             }
         }
     }
     
    }