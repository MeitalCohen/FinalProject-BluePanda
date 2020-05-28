package interfaces.business;

import entities.Event;
import exceptions.BusinessException;
import exceptions.UserNotFoundException;

public interface IEventManager {
    Event scheduleEvent(Event event) throws BusinessException;

    Event cancelEvent(Event event, int userID) throws UserNotFoundException, BusinessException;
}
