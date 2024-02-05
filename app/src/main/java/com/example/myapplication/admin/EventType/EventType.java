package com.example.myapplication.admin.EventType;

import java.io.Serializable;

public class EventType implements Serializable {
    private String eventName;
    private EventTypes eventTypes;
    private EventTypeStatus eventTypeStatus;

    public EventType() {
        this.eventTypeStatus = EventTypeStatus.PENDING;
    }

    public EventType(String eventName, EventTypes eventTypes, EventTypeStatus eventTypeStatus) {
        this.eventName = eventName;
        this.eventTypes = eventTypes;
        this.eventTypeStatus = EventTypeStatus.PENDING;
    }

    public String getEventName() {
        return eventName;
    }
    public EventTypes getEventTypes() {
        return eventTypes;
    }
    public EventTypeStatus getEventTypeStatus() {
        return eventTypeStatus;
    }
    public void setEventTypes(EventTypes eventTypes) {
        this.eventTypes = eventTypes;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public void setEventTypeStatus(EventTypeStatus eventTypeStatus) {
        this.eventTypeStatus = eventTypeStatus;
    }

}
