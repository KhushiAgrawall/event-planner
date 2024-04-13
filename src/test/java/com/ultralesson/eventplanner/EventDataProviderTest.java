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

        @Test(dataProvider = "eventDataProvider")
        public void createEventTest(int id, String name, String description, Venue venue, boolean expectSuccess, String expectedErrorMessage) {
            EventPlanner eventPlanner=new EventPlanner();
            try {
                Event event = new Event(id, name, description, venue);
                eventPlanner.addEvent(event);

                if (expectSuccess) {
                    // Assert event creation success
                    Assert.assertTrue(eventPlanner.getEvents().contains(event), "Event should be created successfully.");
                } else {
                    // If creation was expected to fail, fail the test
                    Assert.fail("Event creation succeeded unexpectedly.");
                }

                // Additional assertions for event properties
                Assert.assertEquals(event.getId(), id, "Event ID should match.");
                Assert.assertEquals(event.getName(), name, "Event name should match.");
                Assert.assertEquals(event.getDescription(), description, "Event description should match.");
                Assert.assertEquals(event.getVenue(), venue, "Event venue should match.");

            } catch (IllegalArgumentException e) {
                // Assert error handling
                if (expectedErrorMessage != null) {
                    Assert.assertEquals(e.getMessage(), expectedErrorMessage, "Unexpected error message.");
                } else {
                    Assert.fail("Unexpected error occurred: " + e.getMessage());
                }
            }
        }


    }