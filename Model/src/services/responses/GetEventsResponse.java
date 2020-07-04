package services.responses;

import entities.Event;
import exceptions.BusinessException;

import java.util.Vector;

public class GetEventsResponse extends ResponseBase {
    private Vector<Event> events;

    public GetEventsResponse(Vector<Event> events) {
        this.events = events;
        if (events == null)
            this.rejectResponse("General Error");
        else
            this.buildResponse();
    }

    public GetEventsResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.events = null;
    }

    public Vector<Event> getEvents(){return events;}
}
