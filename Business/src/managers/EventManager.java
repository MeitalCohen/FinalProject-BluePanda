package managers;

import entities.Event;
import entities.User;
import entities.extension.UserExtension;
import enums.UserStatus;
import exceptions.*;
import interfaces.business.IEventManager;
import interfaces.repository.IEventRepository;
import interfaces.repository.IUserRepository;

import java.util.Date;

public class EventManager implements IEventManager {

    private IEventRepository _eventRepository;
    private IUserRepository _userRepository;

    public EventManager(IEventRepository eventRepository, IUserRepository userRepository)
    {
        _eventRepository = eventRepository;
        _userRepository = userRepository;
    }

    @Override
    public Event scheduleEvent(Event event) throws BusinessException {
        if (event == null)
            return null;

        if (event.getScheduled().before(new Date(System.currentTimeMillis())))
            throw new InvalidEventException();

        event.setCanceled(false);
        return _eventRepository.insert(event);
    }

    @Override
    public Event cancelEvent(Event event, String userID) throws BusinessException {
        if (event == null)
            return null;

        User user = this._userRepository.fetch(userID);
        if (user == null)
            throw new UserNotFoundException();

        if (!UserExtension.isUserFitRole(user, UserStatus.Manager))
            throw new UserNotAuthorizeException();

        Event eventTemp = _eventRepository.fetch(event.getEventID());
        if (eventTemp == null)
            throw new InvalidEventException();

        eventTemp.setCanceled(true);
        return _eventRepository.update(eventTemp);
    }
}
