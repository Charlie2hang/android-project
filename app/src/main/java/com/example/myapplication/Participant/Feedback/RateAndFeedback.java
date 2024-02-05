package com.example.myapplication.Participant.Feedback;

public class RateAndFeedback {
    private String clubName, participantName, eventName, eventDate, eventFees, eventType, eventLevel, feedback;
    private String rate = "5";

    public RateAndFeedback() {
        this.rate = "5";
    }

    public RateAndFeedback(String clubName, String participantName, String eventName,
                           String eventDate, String eventFees, String eventType,
                           String eventLevel, String feedback, String rate) {
        this.clubName = clubName;
        this.participantName = participantName;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventFees = eventFees;
        this.eventType = eventType;
        this.eventLevel = eventLevel;
        this.feedback = feedback;
        this.rate = rate;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventFees() {
        return eventFees;
    }

    public void setEventFees(String eventFees) {
        this.eventFees = eventFees;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
