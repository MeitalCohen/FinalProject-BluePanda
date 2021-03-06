import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;

import com.mindfusion.common.DateTime;
import com.mindfusion.drawing.SolidBrush;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;
import entities.Event;
import entities.User;
import enums.ResponseStatus;
import serviceHost.ServiceCommand;
import services.requests.GetEventsRequest;
import services.responses.GetEventsResponse;

public class EventsPage
{
    private ServiceCommand sc;
    private Calendar calendar;
    private User user;
    private JFrame frame;
    private static final long serialVersionUID = 1L;
    public static final Color Red = new Color(0xFF, 0x63, 0x47);
    public static final Color Green = new Color(0x00, 0xFF, 0x7F);

    public EventsPage(IUpdateFrameCommand command, User user)
    {
        this.user = user;
        sc = ServiceCommand.getInstance();
    }

    public JFrame eventsFrame()
    {
        frame = new JFrame("Library Events");

        calendar = new Calendar();
        calendar.setTheme(ThemeType.Light);

        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

        JPanel upPnl = new JPanel(new BorderLayout());
        final JLabel titleLabel = new JLabel("<html><h2>Library Events</h2></html>");
        upPnl.add(titleLabel);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        frame.add(upPnl, BorderLayout.NORTH);
        frame.add(calendar, BorderLayout.CENTER);
        frame.add(btnPnl, BorderLayout.SOUTH);

        // Initialize the date file
        onWindowOpened();

        return frame;
    }

    private void onWindowOpened()
    {
        GetEventsRequest request = new GetEventsRequest();
        GetEventsResponse response = sc.execute(request);
        if (response.getStatus() != ResponseStatus.OK.errorCode()) {
            JOptionPane.showMessageDialog(null, response.getErrorMessage()); //Display Message
        } else {
            Vector<Event> events = response.getEvents();
            Appointment item;
            DateTime dt;
            Style styleCanceled = new Style();
            styleCanceled.setBrush(new SolidBrush(Red));
            Style styleNotCanceled = new Style();
            styleNotCanceled.setBrush(new SolidBrush(Green));

            for (Event e : events)
            {
                item = new Appointment();
                dt = new DateTime(e.getScheduled());
                item.setStartTime(dt);
                item.setEndTime(dt);
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                String formattedDate=dateFormat.format(e.getScheduled());
                item.setHeaderText(e.getEventTitle() + "/" + e.getAuthorName() + "/" + formattedDate);
                if(e.isCanceled())
                    item.setStyle(styleCanceled);
                else
                    item.setStyle(styleNotCanceled);
                calendar.getSchedule().getItems().add(item);
            }
        }
    }


}
