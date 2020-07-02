package services.responses;

import entities.Event;
import exceptions.BusinessException;

public class ScheduleEventResponse extends ResponseBase{
    private Event event;

    public ScheduleEventResponse(Event event){
        if (event == null)
            this.rejectResponse("GeneralError");
        else
            this.buildResponse();

        this.event = event;
    }

    public ScheduleEventResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.event = null;
    }

    public Event getEvent() {
        return event;
    }
}
