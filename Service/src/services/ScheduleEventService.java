package services;

import entities.Event;
import exceptions.BusinessException;
import exceptions.InvalidRequestException;
import interfaces.business.IEventManager;
import interfaces.repository.IEventRepository;
import interfaces.repository.IUserRepository;
import managers.EventManager;
import services.requests.ScheduleEventRequest;
import services.responses.ScheduleEventResponse;

public class ScheduleEventService implements IService<ScheduleEventRequest, ScheduleEventResponse> {
    private IEventManager eventManager;

    public ScheduleEventService(IEventRepository eventRepository, IUserRepository userRepository)
    {
        this.eventManager = new EventManager(eventRepository, userRepository);
    }

    @Override
    public void validate(ScheduleEventRequest scheduleEventRequest) throws BusinessException {
        if (scheduleEventRequest.getEvent() == null)
            throw new InvalidRequestException("ScheduleEventRequest");
    }

    @Override
    public ScheduleEventResponse execute(ScheduleEventRequest scheduleEventRequest) throws BusinessException {
        Event event = eventManager.scheduleEvent(scheduleEventRequest.getEvent());
        return new ScheduleEventResponse(event);
    }

    @Override
    public ScheduleEventResponse rejectResponseBuilder(BusinessException businessException) {
        return new ScheduleEventResponse(businessException);
    }
}
