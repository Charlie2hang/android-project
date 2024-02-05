package com.example.myapplication.Participant.Registration;

import com.example.myapplication.Club.Event.EventStatus;
import com.example.myapplication.admin.EventType.EventLevels;
import com.example.myapplication.admin.EventType.EventTypes;

import java.io.Serializable;

public class Registration implements Serializable {
    private String eventName, clubName, fees, limit, date, participantName, eventTypes, eventLevels;

    RegistrationStatus registrationStatus;

    public Registration() {
        this.registrationStatus = RegistrationStatus.PENDING;
    }

    public Registration(String eventName, String clubName, String fees, String limit,
                        String date, String participantName, String eventTypes,
                        String eventLevels, RegistrationStatus registrationStatus) {
        this.eventName = eventName;
        this.clubName = clubName;
        this.fees = fees;
        this.limit = limit;
        this.date = date;
        this.participantName = participantName;
        this.eventTypes = eventTypes;
        this.eventLevels = eventLevels;
        this.registrationStatus = registrationStatus;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(String eventTypes) {
        this.eventTypes = eventTypes;
    }

    public String getEventLevels() {
        return eventLevels;
    }

    public void setEventLevels(String eventLevels) {
        this.eventLevels = eventLevels;
    }

    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
