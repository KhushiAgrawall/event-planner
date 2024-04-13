package com.ultralesson.eventplanner;
import com.ultralesson.eventplanner.model.Event;
import com.ultralesson.eventplanner.model.Venue;
import com.ultralesson.eventplanner.service.EventPlanner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VenueTest {
    private Venue venue;
    @BeforeMethod
    public void setup(){

        venue = new Venue(1, "Heavenly Palace", "Near Central Park", 1000);
    }
    @Test(groups ={"creation", "VenueCreation"}, priority = 2)
    public void testVenueCreation(){
        Venue venue = new Venue(1, "Heavenly Palace", "Near Central Park", 1000);
        Assert.assertEquals(venue.getId(), 1);
        Assert.assertEquals(venue.getName(), "Heavenly Palace");
        Assert.assertEquals(venue.getAddress(), "Near Central Park");
        Assert.assertEquals(venue.getCapacity(), 1000);
    }
    /*@Test
    public void testVenueCreation1(){
        Assert.assertNotNull(venue, "Venue instance should not null");
    }*/
    @Test
    public void testVenueProperties(){
        Assert.assertEquals(venue.getId(), 1, "Venue Id does not match");
        Assert.assertEquals(venue.getName(), "Heavenly Palace", "Venue Name does not match");
        Assert.assertEquals(venue.getAddress(), "Near Central Park", "Venue Address does not match");
        Assert.assertEquals(venue.getCapacity(), 1000 , "Venue Capacity does not match");
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForNegativeCapacity() {
        Venue venue = new Venue(1, "Test Venue 1", "Test Address 1", -10);
    }
    @Test(description = "Test case for assigning venue to events and verifying")
    public void testVenuetoEvent(){
        Venue venue;
        Event event;
        event=new Event(1,"farewell","Party for new seniors",null);
        venue=new Venue(1,"Hotel aroma","Ville Parle, shirpur",70);
        event.setVenue(venue);
        Assert.assertEquals(event.getVenue(), venue, "Venue should be assigned correctly");
    }

    @Test(description = "Add venue to event-planner")
    public void testAddVenue(){
        EventPlanner ep=new EventPlanner();
        Venue venue;
        Event event;
        venue=new Venue(1,"Hotel aroma","Ville Parle, shirpur",70);
        ep.addVenue(venue);
        Assert.assertTrue(ep.getVenues().contains(venue));
    }

    @Test(description = "update venue details")
    public void testUpdateVenue(){
        EventPlanner ep=new EventPlanner();
        Venue venue1=new Venue(1, "Cafe Corner","Marine Lines",80);
        ep.addVenue(venue1);
//        System.out.println(ep.getVenues().get(0));
        venue1.setAddress("Colaba, Mumbai");
        ep.addVenue(venue1);
//        System.out.println(ep.getVenues().get(0));
        Assert.assertEquals(ep.getVenues().get(0),venue1);
    }

    @Test(description = "remove venue")
    public void testRemoveVenue(){
        EventPlanner eventPlanner=new EventPlanner();
        Venue venue=new Venue(1,"Taj hotel", "Gateway of India",300);
        eventPlanner.addVenue(venue);
        eventPlanner.removeVenue(1);
//        Assert.assertNull(eventPlanner); // returns the instance of event planner
        Assert.assertFalse(eventPlanner.getVenues().contains(venue));
//        System.out.println(eventPlanner.getVenues());
    }
    @AfterMethod
    public void tearDown(){
        venue=null;
    }

}
