package interfaces.repository;

import entities.Event;

import java.util.Date;
import java.util.Vector;

public interface IEventRepository {

    public Event insert(Event event);

    public Event update (Event event);

    public Event fetch(int eventID);

    public Vector<Event> search(String eventTitle, int librarianID, Date scheduled, String authorName, boolean isCanceled);

}
