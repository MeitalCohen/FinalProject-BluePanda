package services;

import entities.Event;
import exceptions.BusinessException;
import interfaces.business.IEventManager;
import interfaces.repository.IEventRepository;
import interfaces.repository.IUserRepository;
import managers.EventManager;
import services.requests.GetEventsRequest;
import services.responses.GetEventsResponse;

import java.util.Vector;

public class GetEventsService implements IService<GetEventsRequest, GetEventsResponse> {
    private IEventManager eventManager;

    public GetEventsService(IEventRepository eventRepository, IUserRepository userRepository)
    {
        this.eventManager = new EventManager(eventRepository, userRepository);
    }
    @Override
    public void validate(GetEventsRequest getEventsRequest) throws BusinessException {

    }

    @Override
    public GetEventsResponse execute(GetEventsRequest getEventsRequest) throws BusinessException {
        Vector<Event> events = this.eventManager.getEvents();
        return new GetEventsResponse(events);
    }

    @Override
    public GetEventsResponse rejectResponseBuilder(BusinessException businessException) {
        return new GetEventsResponse(businessException);
    }
}
