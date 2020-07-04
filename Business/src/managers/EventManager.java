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
import java.util.Vector;

public class EventManager implements IEventManager {

    private IEventRepository eventRepository;
    private IUserRepository userRepository;

    public EventManager(IEventRepository eventRepository, IUserRepository userRepository)
    {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Event scheduleEvent(Event event) throws BusinessException {
        if (event == null)
            return null;

        if (event.getScheduled().before(new Date(System.currentTimeMillis())))
            throw new InvalidEventException();

        event.setCanceled(false);
        return eventRepository.insert(event);
    }

    @Override
    public Event cancelEvent(Event event, String userID) throws BusinessException {
        if (event == null)
            return null;

        User user = this.userRepository.fetch(userID);
        if (user == null)
            throw new UserNotFoundException();

        if (!UserExtension.isUserFitRole(user, UserStatus.Manager))
            throw new UserNotAuthorizeException();

        Event eventTemp = eventRepository.fetch(event.getEventID());
        if (eventTemp == null)
            throw new InvalidEventException();

        eventTemp.setCanceled(true);
        return eventRepository.update(eventTemp);
    }

    public Vector<Event> getEvents()
    {
        return this.eventRepository.getEvents();
    }
}
