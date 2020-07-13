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

public class ManageEventsPage extends JFrame implements IFinishedCommand
{
    private ServiceCommand sc;
    private Calendar calendar;
    private Date selectedDate;
    private User user;
    private IUpdateFrameCommand menuCommand;
    private static final long serialVersionUID = 1L;
    public static final Color Red = new Color(0xFF, 0x63, 0x47);
    public static final Color Green = new Color(0x00, 0xFF, 0x7F);

    public ManageEventsPage(IUpdateFrameCommand command, User user)
    {
        this.user = user;
        this.menuCommand = command;
        this.selectedDate = new Date(System.currentTimeMillis());
        sc = ServiceCommand.getInstance();
        initPage();
    }

    public JFrame manageEventsFrame()
    {
        return this;
    }

    public void initPage()
    {
        calendar = new Calendar();
        calendar.setTheme(ThemeType.Light);

        JPanel btnPnl = new JPanel(new BorderLayout());
        JPanel bottombtnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton addEvent = new JButton("Add Event");
        addEvent.setEnabled(true);

        bottombtnPnl.add(addEvent);

        calendar.addCalendarListener(new CalendarAdapter(){
            public void dateClick(ResourceDateEvent var1) {
                selectedDate = var1.getDate().toJavaCalendar().getTime();
            }
        });

        addEvent.addActionListener(new ActionListener() {  //Perform action
            public void actionPerformed(ActionEvent e) {
                new AddEvent(ManageEventsPage.this, user, selectedDate).addEvent();
            }
        });

        btnPnl.add(bottombtnPnl, BorderLayout.CENTER);

        JPanel upPnl = new JPanel(new BorderLayout());
        final JLabel titleLabel = new JLabel("<html><h2>Mange Events</h2></html>");
        upPnl.add(titleLabel);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(upPnl, BorderLayout.NORTH);
        add(calendar, BorderLayout.CENTER);
        add(btnPnl, BorderLayout.SOUTH);

        // Initialize the date file
        onWindowOpened();
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

    @Override
    public void finishedCommand() {
        //libraryBooksPanel();
        initPage();
        this.menuCommand.updateFrame(this);
    }

}
