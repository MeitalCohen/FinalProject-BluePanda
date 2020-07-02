package services.requests;


import entities.Event;

public class CancelEventRequest extends RequestBase {
    private String userId;
    private Event event;

    public CancelEventRequest(String userId, Event event)
    {
        this.userId = userId;
        this.event = event;
    }

    public String getUserId() {
        return userId;
    }

    public Event getEvent() {
        return event;
    }
}
