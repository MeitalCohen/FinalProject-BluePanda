package services.requests;

import entities.Event;

public class ScheduleEventRequest extends RequestBase{
    private Event event;

    public ScheduleEventRequest(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
