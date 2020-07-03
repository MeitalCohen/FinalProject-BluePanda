package interfaces.business;

import entities.Event;
import exceptions.BusinessException;
import exceptions.UserNotFoundException;

import java.util.Vector;

public interface IEventManager {
    Event scheduleEvent(Event event) throws BusinessException;

    Event cancelEvent(Event event, String userID) throws UserNotFoundException, BusinessException;

    Vector<Event> getEvents();

}
