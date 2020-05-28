package entities;

import java.util.Date;

public class Event extends Entity {
    private int eventID;
    private String eventTitle;
    private int librarianID;
    private Date scheduled;
    private String authorName;
    private boolean isCanceled;

    public Event()
    {}

    public Event(String eventTitle, int librarianID, Date scheduled, String authorName, boolean isCanceled) {
        this.eventID = Entity.id;
        this.eventTitle = eventTitle;
        this.librarianID = librarianID;
        this.scheduled = scheduled;
        this.authorName = authorName;
        this.isCanceled = isCanceled;
        Entity.id++;
    }

    public Event(int eventID, String eventTitle, int librarianID, Date scheduled, String authorName, boolean isCanceled) {
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

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public int getLibrarianID() {
        return librarianID;
    }

    public void setLibrarianID(int librarianID) {
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
