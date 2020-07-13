package entities;

import java.util.Date;
import java.util.UUID;

public class Event extends Entity {
    private String eventID;
    private String eventTitle;
    private String librarianID;
    private Date scheduled;
    private String authorName;
    private boolean isCanceled;

    public Event()
    {}

    public Event(String eventTitle, String librarianID, Date scheduled, String authorName, boolean isCanceled) {
        this.eventID = UUID.randomUUID().toString();
        this.eventTitle = eventTitle;
        this.librarianID = librarianID;
        this.scheduled = scheduled;
        this.authorName = authorName;
        this.isCanceled = isCanceled;
    }

    public Event(String eventID, String eventTitle, String librarianID, Date scheduled, String authorName, boolean isCanceled) {
        this.eventID = eventID;
        this.eventTitle = eventTitle;
        this.librarianID = librarianID;
        this.scheduled = scheduled;
        this.authorName = authorName;
        this.isCanceled = isCanceled;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventID=" + eventID +
                ", eventTitle='" + eventTitle + '\'' +
                ", librarianID=" + librarianID +
                ", scheduled=" + scheduled +
                ", authorName='" + authorName + '\'' +
                ", isCanceled=" + isCanceled +
                '}';
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getLibrarianID() {
        return librarianID;
    }

    public void setLibrarianID(String librarianID) {
        this.librarianID = librarianID;
    }

    public Date getScheduled() {
        return scheduled;
    }

    public void setScheduled(Date scheduled) {
        this.scheduled = scheduled;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }
}
