package services;

import entities.Event;
import exceptions.BusinessException;
import exceptions.InvalidRequestException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IEventManager;
import interfaces.repository.IEventRepository;
import interfaces.repository.IUserRepository;
import managers.AuthenticationValidator;
import managers.EventManager;
import services.requests.CancelEventRequest;
import services.responses.CancelEventResponse;

public class CancelEventService implements IService<CancelEventRequest,CancelEventResponse> {
    private IEventManager eventManager;
    private IAuthenticationValidator authenticationValidator;

    public CancelEventService(IEventRepository eventRepository, IUserRepository userRepository)
    {
        this.authenticationValidator = new AuthenticationValidator(userRepository);
        this.eventManager = new EventManager(eventRepository, userRepository);
    }


    @Override
    public void validate(CancelEventRequest cancelEventRequest) throws BusinessException{
        this.authenticationValidator.validateUserId(cancelEventRequest.getUserId());

        if (cancelEventRequest.getEvent() == null)
            throw new InvalidRequestException("CancelEventRequest");

    }

    @Override
    public CancelEventResponse execute(CancelEventRequest cancelEventRequest) throws BusinessException {
        Event canceledEvent = eventManager.cancelEvent(cancelEventRequest.getEvent(),cancelEventRequest.getUserId());
        return new CancelEventResponse(canceledEvent);
    }

    @Override
    public CancelEventResponse rejectResponseBuilder(BusinessException businessException) {
        return new CancelEventResponse(businessException);
    }
}
