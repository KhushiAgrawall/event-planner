package com.ultralesson.eventplanner;
import com.ultralesson.eventplanner.model.Attendee;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AttendeeTest {
    private Attendee attendee;
    //attendee = new Attendee(1, "khushi", "invalid email");
    @BeforeMethod
    public void setup(){
        attendee = new Attendee(1, "Khushi", "agrawal@123");

    }
    @Test(groups = {"creation", "attendeeCreation"}, priority = 1)
    public void testAttendeeCreation(){
    Attendee attendee = new Attendee(1, "Khushi", "agrawal@123");
        Assert.assertEquals(attendee.getName(), "Khushi");
        Assert.assertEquals(attendee.getEmail(), "agrawal@123");
        Assert.assertEquals(attendee.getId(), 1);
    }
    @Test
    public void testAttendeeProperties(){
        Assert.assertEquals(attendee.getId(), 1, "Attendee ID doesn't match");
        Assert.assertEquals(attendee.getName(), "Khushi", "Attendee Name doesn't match");
        Assert.assertEquals(attendee.getEmail(), "agrawal@123", "Attendee Email doesn't match");
    }
   /* @Test
    public void testAttendeeEmailValidation(){

        attendee=new Attendee(1, "khushi", "valid_email");
    }*/
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionForInvalidEmail(){
        attendee=new Attendee(1, "new attendee", "google.com");
    }
    @AfterMethod
    public void tearDown(){
        // it will reset the attendee instance to null before executing the next test.
        attendee = null;
    }



}
