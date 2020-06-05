package interfaces.repository;

import entities.Event;

import java.util.Date;
import java.util.Vector;

public interface IEventRepository extends IRepository<Event>{

    Event update (Event event);

    Event fetch(int eventID);

    Vector<Event> search(String eventTitle, int librarianID, Date scheduled, String authorName, boolean isCanceled);

}