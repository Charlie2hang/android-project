package com.example.myapplication.Club.Event;

import com.example.myapplication.admin.EventType.EventLevels;

import com.example.myapplication.admin.EventType.EventTypes;

import java.io.Serializable;

public class Event implements Serializable {
    private String ID, clubName, fees, limit, date;
    EventTypes eventTypes;
    EventLevels eventLevels;
    EventStatus eventStatus;

    public Event() {
        this.eventStatus = EventStatus.PENDING;
    }

    public Event(String ID, String clubName, String fees, String limit, EventTypes eventTypes, EventLevels eventLevels, EventStatus eventStatus, String date) {
        this.ID = ID;
        this.clubName = clubName;
        this.fees = fees;
        this.limit = limit;
        this.eventTypes = eventTypes;
        this.eventLevels = eventLevels;
        this.eventStatus = EventStatus.PENDING;
        this.date =date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public EventStatus getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public EventTypes getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(EventTypes eventTypes) {
        this.eventTypes = eventTypes;
    }

    public EventLevels getEventLevels() {
        return eventLevels;
    }

    public void setEventLevels(EventLevels eventLevels) {
        this.eventLevels = eventLevels;
    }
}
