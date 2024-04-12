package com.ultralesson.eventplanner.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Schedule {
    private int id;
    private Event event;
    private Venue venue;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Schedule(int id, Event event, Venue venue, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.event = event;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return id == schedule.id &&
                Objects.equals(event, schedule.event) &&
                Objects.equals(venue, schedule.venue) &&
                Objects.equals(startTime, schedule.startTime) &&
                Objects.equals(endTime, schedule.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event, venue, startTime, endTime);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", event=" + event +
                ", venue=" + venue +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

