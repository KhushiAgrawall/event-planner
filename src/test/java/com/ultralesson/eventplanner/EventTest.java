package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Venue;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EventTest {
    Event event;
    Venue venue;
    @BeforeClass
    public void setUpClass(){
        System.out.println("Setting up the resources for event class");
    }

    @Test(groups = {"creation"})
    public void testEventCreation(){
          venue = new Venue(1, "Heavenly Palace", "Near Central Park", 1000);
         event = new Event(1,"convocation ceremony", "Degree Completion", venue);
        Assert.assertEquals(event.getId(), 1);
        Assert.assertEquals(event.getName(), "convocation ceremony");
        Assert.assertEquals(event.getDescription(), "Degree Completion");
        Assert.assertEquals(event.getVenue(), venue);
        Assert.assertNotNull(event, "event creation got failed; instance of the event is null");
    }
    @Test
    public void testEventProperties(){
        Assert.assertEquals(event.getId(), 1, "Event Id does not match");
        Assert.assertEquals(event.getName(), "convocation ceremony", "Event Name does not match");
        Assert.assertEquals(event.getDescription(), "Degree Completion", "Event Address does not match");
        Assert.assertEquals(event.getVenue(), venue, "Event Venue does not match");
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForNullEventName() {
        Venue dummyVenue = new Venue(1, "Venue Test", "Test Address", 100);

        Event testEvent = new Event(1, null, "Test Event", dummyVenue);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForMissingEventDescription(){
        Venue venue = new Venue(1, "Heavenly Palace", "Near Central Park", 1000);
        new Event(1, "Tech Conference", null, venue); // Expect IllegalArgumentException due to null description
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForMissingEventVenue(){
        new Event(1, "Tech Conference", "A conference about technology", null); // Expect IllegalArgumentException due to null venue
    }

    /*@Test(timeOut = 500)
    public void testEventCreationWithTimeout() throws InterruptedException {
        Thread.sleep(600);
        Venue venue1= new Venue(1, "Test Venue", "123 Test Street", 100);
        Event event2 = new Event(1, "Test Event", "This is a test event", venue1);
    }*/
    @AfterClass
    public void tearDownClass(){
        System.out.println("Cleaning up resources for event class...");

    }
}
