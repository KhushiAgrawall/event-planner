package com.ultralesson.eventplanner;
import com.ultralesson.eventplanner.model.Venue;
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
    @Test
    public void testVenueCreation(){
        //Venue venue = new Venue(1, "Heavenly Palace", "Near Central Park", 1000);
        Assert.assertEquals(venue.getId(), 1);
        Assert.assertEquals(venue.getName(), "Heavenly Palace");
        Assert.assertEquals(venue.getAddress(), "Near Central Park");
        Assert.assertEquals(venue.getCapacity(), 1000);
    }
    @Test(groups = {"creation"})
    public void testVenueCreation1(){
        Assert.assertNotNull(venue, "Venue instance should not null");
    }
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
    @AfterMethod
    public void tearDown(){
        venue=null;
    }

}
