package repository;

import interfaces.repository.IEventRepository;
import entities.Event;

import java.util.Date;
import java.util.Vector;

public class EventRepository extends RepositoryBase<Event> implements IEventRepository {

    private Vector<Event> events;

    public EventRepository()
    {
        events = this.loadData();
    }

    public Event insert(Event event) {
        if (events == null)
            this.events = new Vector<>();

        Event eventResult = events.stream().filter(evnt ->
                evnt.getEventID().equals(event.getEventID())).findFirst().orElse(null);

        if (eventResult != null)
            return null;

        boolean result = events.add(event);
        if (result)
        {
            this.saveData(events);
            return event;
        }
        else{
            return null;
        }
    }

    public Event update (Event event)
    {
        if (events == null || events.isEmpty())
            return null;

        try {

            Event eventTemp = events.stream().filter(usr ->
                    usr.getEventID().equals(event.getEventID())).findFirst().orElse(null);

            if (eventTemp == null)
                return null;

            events.remove(eventTemp);

            boolean result = events.add(eventTemp);

            if (result) {
                this.saveData(events);
                return eventTemp;
            } else {
                return null;
            }
        }
        catch (ArrayIndexOutOfBoundsException exception)
        {
            return null;
        }
    }

    public Event fetch(String eventID)
    {
        if (events == null || events.isEmpty())
            return null;

        return events.stream().filter(usr ->
            usr.getEventID().equals(eventID)).findFirst().orElse(null);
    }

    public Vector<Event> getEvents()
    {
        return events;
    }

    public Vector<Event> search(String eventTitle, int librarianID, Date scheduled, String authorName, boolean isCanceled)
    {
        Vector<Event> eventsResult = events;
        if (eventTitle != null  && !eventTitle.isEmpty())
        {
            // eventsResult.forEach(event -> {
            //   event.getEventTitle() == eventTitle
            //});
        }
        if (librarianID != -1)
        {
            //TODO: Filter
        }
        if (scheduled != null) {

        }
        if (authorName != null && !authorName.isEmpty()) {


        }

        return events;

    }

}
