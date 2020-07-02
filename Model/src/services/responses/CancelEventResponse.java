package services.responses;

import entities.Event;
import exceptions.BusinessException;

public class CancelEventResponse extends ResponseBase{
    private Event event;

    public CancelEventResponse(Event event)
    {
        this.buildResponse();
        this.event = event;
    }

    public CancelEventResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.event = null;
    }

    public Event getEvent() {
        return event;
    }
}
